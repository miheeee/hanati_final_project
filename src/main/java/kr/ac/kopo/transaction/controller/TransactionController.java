package kr.ac.kopo.transaction.controller;

import java.util.ArrayList;
import java.util.Collections;
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
	//메인 페이지에서 계좌에 있는 버튼을 누를 경우
	@GetMapping("/transaction/transfer/{safeAccountNo}")
	public ModelAndView transferForm(HttpSession session, @PathVariable String safeAccountNo, HttpServletRequest request) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");

		//모임통장을 제외한 일반 입출금 계좌 조회
		List<AccountVO> accountList = gatheringService.selectAccountExceptGathering(loginVO);
	
		//모임주로 있는 모임통장 정보만 조회
		List<GatheringVO> gatheringList = gatheringService.selectOwnedGatheringById(loginVO);
		
		ModelAndView mav = new ModelAndView("transaction/transferForm");
		mav.addObject("accountList", accountList);
		mav.addObject("gatheringList", gatheringList);
		mav.addObject("safeAccountNo", safeAccountNo);
		
//		String uri = request.getRequestURI();
//		uri = uri.substring(request.getContextPath().length());
		//이전 경로
		String url = request.getHeader("referer");
		System.out.println(url);
		String beforeAddr = url.split("/")[4];
		if(!beforeAddr.equals("detail")) {	
			//은행 목록 가져오기
			List<CodeVO> bankList = codeService.selectBankCode();
			mav.addObject("bankList", bankList);
		}
		mav.addObject("beforeAddr", beforeAddr);

		return mav;
	}
	
	
	//이체
	@PostMapping("/transaction/transfer")
	public String transfer(TransactionVO transactionVO, Model model) {
		
		System.out.println("컨트롤러:" + transactionVO);
		
		transactionService.transfer(transactionVO);
		gatheringVO = gatheringService.selectByAccountNo(transactionVO.getAccountNo());
		model.addAttribute("gatheringVO", gatheringVO);
		
		return "transaction/transfer";
	}
	
	//기간별 회비 입금 내역 조회
	@PostMapping("/dues/period")
	public String selectDuesByPeriod(TransactionVO transactionVO, Model model) {

		System.out.println("TransactionVO!!!!!!!!!!!");
		System.out.println(transactionVO);
		//기간별 회비 입금 내역 조회
		List<TransactionVO> duesList =  transactionService.selectDuesByPeriod(transactionVO);
		
		//계좌번호로 모임원 목록 조회
		List<ParticipantVO> participantList = participantService.selectByAccountNo(transactionVO);		
		
		model.addAttribute("duesList", duesList);
		model.addAttribute("participantList", participantList);
		return "/dues/periodAjax";
	}
	
	
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
}
