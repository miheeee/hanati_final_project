package kr.ac.kopo.transaction.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.AccountService;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.code.service.CodeService;
import kr.ac.kopo.code.vo.CodeVO;
import kr.ac.kopo.gathering.service.GatheringService;
import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.service.ParticipantService;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.transaction.service.TransactionService;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private GatheringService gatheringService;
	
	@Autowired
	private ParticipantService participantService;
	
	@Autowired
	private GatheringVO gatheringVO;
	
	//이체폼
	//i)모임주가 모임통장에서 이체할 때
	@GetMapping("/transaction/transfer/{safeAccountNo}")
	public ModelAndView transferFormWithdrawFromGathering(HttpSession session, @PathVariable String safeAccountNo, HttpServletRequest request) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");

		//모임통장을 제외한 일반 입출금 계좌 조회
		List<AccountVO> accountList = gatheringService.selectAccountExceptGathering(loginVO);
	
		//모임주로 있는 모임통장 정보만 조회
		List<GatheringVO> gatheringList = gatheringService.selectOwnedGatheringById(loginVO);

		//은행 목록 가져오기
		List<CodeVO> bankList = codeService.selectBankCode();
		
		ModelAndView mav = new ModelAndView("transaction/transferForm");
		mav.addObject("accountList", accountList);
		mav.addObject("gatheringList", gatheringList);
		mav.addObject("bankList", bankList);
		mav.addObject("safeAccountNo", safeAccountNo);
		
		return mav;
	}
	
	//이체폼
	//ii)네비게이션바의 이체 버튼을 누를 때
	@GetMapping("/transaction/transfer")
	public ModelAndView transferForm(HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");

		//모임통장을 제외한 일반 입출금 계좌 조회
		List<AccountVO> accountList = gatheringService.selectAccountExceptGathering(loginVO);
	
		//모임주로 있는 모임통장 정보만 조회
		List<GatheringVO> gatheringList = gatheringService.selectOwnedGatheringById(loginVO);
		
		//은행 목록 가져오기
		List<CodeVO> bankList = codeService.selectBankCode();
		
		ModelAndView mav = new ModelAndView("transaction/transferForm");
		mav.addObject("accountList", accountList);
		mav.addObject("gatheringList", gatheringList);
		mav.addObject("bankList", bankList);
		
		return mav;
	}
	
	
	//이체폼
	//iii)회비 입금을 할 때
	@GetMapping("/transaction/transfer/dues/{safeAccountNo}")
	public ModelAndView transferFormForDues(HttpSession session, @PathVariable String safeAccountNo, HttpServletRequest request) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");

		//모임통장을 제외한 일반 입출금 계좌 조회
		List<AccountVO> accountList = gatheringService.selectAccountExceptGathering(loginVO);
	
		//모임주로 있는 모임통장 정보만 조회
		List<GatheringVO> gatheringList = gatheringService.selectOwnedGatheringById(loginVO);
		
		//은행 목록 가져오기
		List<CodeVO> bankList = codeService.selectBankCode();
		
		ModelAndView mav = new ModelAndView("transaction/transferForm");
		mav.addObject("accountList", accountList);
		mav.addObject("gatheringList", gatheringList);
		mav.addObject("bankList", bankList);
		mav.addObject("safeAccountNo", safeAccountNo);
		
		return mav;
	}
	

	//이체
	@PostMapping("/transaction/transfer")
	public String transfer(TransactionVO transactionVO, Model model) {

		transactionService.transfer(transactionVO);
		gatheringVO = gatheringService.selectByAccountNo(transactionVO.getAccountNo());
		model.addAttribute("gatheringVO", gatheringVO);
		
		return "transaction/transfer";
	}
	
	//기간별 회비 입금 내역 조회
	@PostMapping("/dues/period")
	public String selectDuesByPeriod(TransactionVO transactionVO, Model model) {

		//기간별 회비 입금 내역 조회
		List<TransactionVO> duesList =  transactionService.selectDuesByPeriod(transactionVO);
		
		//계좌번호로 모임원 목록 조회
		List<ParticipantVO> participantList = participantService.selectByAccountNo(transactionVO);		
		
		model.addAttribute("duesList", duesList);
		model.addAttribute("participantList", participantList);
		return "/dues/periodAjax";
	}
	
	//멤버별 회비 입금 내역 조회
	@PostMapping("/dues/member")
	public String duesByMember(ParticipantVO participantVO, Model model) {
		
		//모임원별 개인 회비 입금 내역
		List<TransactionVO> duesList = transactionService.selectDuesByMember(participantVO);

		//가져온 회비 입금 내역에서 기간 추출
		Set<String> monthSet = new HashSet<String>();
		for(TransactionVO dues : duesList) {
			monthSet.add(dues.getTime().substring(0,4) + "년" + 
							dues.getTime().substring(5,7) + "월");	
		}
		
		Set<String> yearSet = new HashSet<String>();
		for(TransactionVO dues : duesList) {
			yearSet.add(dues.getTime().substring(0,4) + "년");
		}

		//최근순대로 정렬
		List<String> monthList = new ArrayList<>(monthSet);		//먼저 set => list로 변환
		Collections.sort(monthList, Collections.reverseOrder());
		
		List<String> yearList = new ArrayList<>(yearSet);		
		Collections.sort(yearList, Collections.reverseOrder());
		
		model.addAttribute("duesList", duesList);
		model.addAttribute("monthList", monthList);
		model.addAttribute("yearList", yearList);
	
		return "/dues/memberAjax";
	}
	
	//모임통장 상세정보 - 거래내역 탭
	@PostMapping("/transaction/list")
	public String transactionList(GatheringVO gatheringVO, HttpSession session, Model model) {
		
		//safeAccountNo로 거래 내역 조회	(참고로 거래내역은 무조건 실제 계좌번호만 DB에 찍히게 하자)
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		gatheringVO.setId(loginVO.getId());		//알림은 각자 설정하는 거기 때문에 모임통장 소유주만 있는 gathering테이블의 id칼럼과는 성격이 다르지만, vo객체를 따로 만드는 게 번거로워서 그냥 담아 보냈다.

		List<TransactionVO> transactionList = transactionService.selectTransBySafeAccountNo(gatheringVO);
		
		model.addAttribute("transactionList", transactionList);
		model.addAttribute("gathering", gatheringVO);
		return "/transaction/listAjax";
	}	
	
	
	//모임통장 상세정보 - 회비내역 탭
	@PostMapping("/dues/list")
	public String duesList(GatheringVO gatheringVO, HttpSession session, Model model) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		gatheringVO.setId(loginVO.getId());
		
		//safeAccountNo로 모임원 정보 조회
		List<ParticipantVO> participantList = participantService.selectParticipantsBySafeAccountNo(gatheringVO);
		
		//safeAccountNo로 회비 입금 내역 조회
		List<TransactionVO> duesList = transactionService.selectDepositedDuesList(gatheringVO);
		
		//처음 띄워주는 기간별 입금내역 보여주기 위해
		//1)가져온 회비 입금 내역에서 기간 추출
		Set<String> monthSet = new HashSet<String>();
		for(TransactionVO dues : duesList) {
			String m = (dues.getTime().substring(5,6).equals("0"))? dues.getTime().substring(6,7) : dues.getTime().substring(5,7);
			monthSet.add(dues.getTime().substring(0,4) + "년 " + 
							 m + "월");
			monthSet.add(dues.getTime().substring(0,4) + "년");
		}
		//2)최근순대로 정렬
		List<String> monthList = new ArrayList<String>(monthSet);		//먼저 set => list로 변환
		Collections.sort(monthList, Collections.reverseOrder());
		
		//처음 띄워주는 멤버벌 입금내역 보여주기 위해
		//1)가져온 회비 입금 내역에서 기간 추출
		Set<String> yearSet = new HashSet<String>();
		for(TransactionVO dues : duesList) {
			yearSet.add(dues.getTime().substring(0,4) + "년");
		}	
		
		//2)최근순대로 정렬
		List<String> yearList = new ArrayList<String>(yearSet);		//먼저 set => list로 변환
		Collections.sort(yearList, Collections.reverseOrder());

		//현재 날짜(년 월)
		String date = new SimpleDateFormat("yyyy.MM").format(new Date());
		
		model.addAttribute("duesList", duesList);
		model.addAttribute("participantList", participantList);
		model.addAttribute("monthList", monthList);
		model.addAttribute("yearList", yearList);
		model.addAttribute("date", date);
		model.addAttribute("gathering", gatheringVO);
		
		return "/dues/listAjax";
	}	
	
}
