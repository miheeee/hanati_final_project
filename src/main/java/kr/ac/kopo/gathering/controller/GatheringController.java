package kr.ac.kopo.gathering.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.code.service.CodeService;
import kr.ac.kopo.code.vo.CodeVO;
import kr.ac.kopo.gathering.dao.GatheringDAO;
import kr.ac.kopo.gathering.service.GatheringService;
import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.notify.service.NotifyService;
import kr.ac.kopo.participant.service.ParticipantService;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.scheduledEnd.service.ScheduledEndService;
import kr.ac.kopo.scheduledEnd.vo.ScheduledEndVO;
import kr.ac.kopo.transaction.service.TransactionService;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Controller
public class GatheringController {

	@Autowired
	private GatheringService gatheringService;

	@Autowired
	private ParticipantService participantService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private ScheduledEndService scheduledEndService;
	
	@Autowired
	private NotifyService notifyService;
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private GatheringVO gatheringVO;
	
	@Autowired
	private ParticipantVO participantVO;
	
	@Autowired
	private GatheringDAO gatheringDAO;
	
	@Autowired
	private ScheduledEndVO scheduledEndVO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberVO memberVO;
	
	
	//모임통장 신청 - 폼
	@GetMapping("/gathering/apply")	
	public ModelAndView applyForm(HttpSession session) { 
		
		//아이디 세팅
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		
		//모임통장을 제외한 입출금 통장 리스트 가져오기
		List<AccountVO> accountList = gatheringService.selectAccountExceptGathering(loginVO); 
		
		//태그 목록 가져오기
		List<CodeVO> tagList = codeService.selectTagCode(); 
		
		ModelAndView mav = new ModelAndView("gathering/applyForm");	
		mav.addObject("accountList", accountList);
		mav.addObject("tagList", tagList);
		
		return mav;
	}
	
	//모임통장 신청 - 프로세스
	@ResponseBody
	@PostMapping("/gathering/apply")
	public String apply(GatheringVO gatheringVO, HttpServletRequest request, HttpSession session) {
	
		//폼데이터에서 입력한 태그 값들 가져오기
		String[] tags = request.getParameterValues("tag[]");
		if(tags.length == 2) {
			if(Integer.parseInt(tags[0]) < Integer.parseInt(tags[1])) {
				gatheringVO.setTagS(Integer.parseInt(tags[0]));
				gatheringVO.setTagL(Integer.parseInt(tags[1]));
			}else {
				gatheringVO.setTagS(Integer.parseInt(tags[1]));
				gatheringVO.setTagL(Integer.parseInt(tags[0]));
			}
		}else {
			gatheringVO.setTagS(Integer.parseInt(tags[0]));
		}
		//아이디 세팅
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		gatheringVO.setId(loginVO.getId());
		
		//계좌 정보 보내기
		gatheringVO = gatheringService.insertGathering(gatheringVO);
		
//		ModelAndView mav = new ModelAndView("gathering/applyComplete");
//		mav.addObject("gatheringVO", gatheringVO);
//		return mav;
		return "/gathering/apply";
	}
	
	//모임통장 완료 페이지 띄우기
	@GetMapping("/gathering/apply/{accountNo}")
	public String applyTo(HttpSession session, @PathVariable("accountNo") String accountNo, Model model) {
			
		//모임통장 정보 가져오기
		GatheringVO gatheringVO = gatheringDAO.selectByAccountNo(accountNo);
		
		//모임주 이름 가져오기
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		memberVO = memberDAO.selectById(loginVO);
		
		model.addAttribute("gatheringVO", gatheringVO);
		model.addAttribute("memberVO", memberVO);
		return "gathering/applyComplete";
	}
	
	@GetMapping("/main")
	public ModelAndView mainAfterLogin(HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");

		//id로 현재 참여하고 있는 모든 모임통장 리스트 조회
		List<GatheringVO> gatheringList = gatheringService.selectParticipatingGatheringList(loginVO);
		
		ModelAndView mav = new ModelAndView("main");
		if(gatheringList.size() != 0) {
			mav.addObject("gatheringList", gatheringList);
		}
		return mav;
	}
	
	//모임 계좌에 대한 상세 정보 페이지
//	@GetMapping("/gathering/detail/{safeAccountNo}")
//	public ModelAndView DetailGathering(@PathVariable("safeAccountNo") String safeAccountNo, HttpSession session) {
	@PostMapping("/gathering/detail")
	public ModelAndView DetailGathering(GatheringVO gatheringVO, HttpSession session) {
		 
//		gatheringVO.setSafeAccountNo(safeAccountNo);
		
		//safeAccountNo로 모임통장 정보 조회
		GatheringVO gathering = gatheringService.selectGatheringBySafeAccountNo(gatheringVO);
		
		//safeAccountNo로 모임원 정보 조회
		List<ParticipantVO> participantList = participantService.selectParticipantsBySafeAccountNo(gatheringVO);
		
		//safeAccountNo로 거래 내역 조회	(참고로 거래내역은 무조건 실제 계좌번호만 찍히게 하자)
		List<TransactionVO> transactionList = transactionService.selectTransBySafeAccountNo(gatheringVO);
		
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
		
		//알림 설정 상태 보여주기
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		gatheringVO.setId(loginVO.getId());		//알림은 각자 설정하는 거기 때문에 모임통장 소유주만 있는 gathering테이블의 id칼럼과는 성격이 다르지만, vo객체를 따로 만드는 게 번거로워서 그냥 담아 보냈다.
		participantVO = participantService.selectAllNotifySettings(gatheringVO);
		
		//모임통장 상태(사용/종료 예정) 가져오기
//		for(ParticipantVO participantVO:participantList) {
//			//모임통장의 소유주일 때만 가져오기
//			if(participantVO.getId().equals(loginVO.getId()) && participantVO.getType().equals("301")) {
				scheduledEndVO = scheduledEndService.selectIfExist(gathering);			
//			}
//		}
	
		ModelAndView mav = new ModelAndView("gathering/detail");
		mav.addObject("gathering", gathering);
		mav.addObject("participantList", participantList);
		mav.addObject("transactionList", transactionList);
		mav.addObject("duesList", duesList);
		mav.addObject("monthList", monthList);
		mav.addObject("yearList", yearList);
		mav.addObject("date", date);
		mav.addObject("participantVO", participantVO);
		mav.addObject("scheduledEndVO", scheduledEndVO);
		
		return mav;
	}
	
	//모임통장 사용 종료 신청
	@ResponseBody
	@PostMapping("/gathering/terminate")
	public void terminate(GatheringVO gatheringVO) {
		
		//사용 종료 예정 목록 테이블에 데이터 추가
		scheduledEndService.terminate(gatheringVO);
		//종료 예정 알림 보내기
		notifyService.scheduledTerminate(gatheringVO);
	}
	
	//모임통장 사용 종료 취소
	@ResponseBody
	@PostMapping("/gathering/terminateCancel")
	public void terminateCancel(GatheringVO gatheringVO) {
		
		scheduledEndService.terminateCancel(gatheringVO);
	}
	
	//모임통장 상태 reload
	@ResponseBody
	@PostMapping("/gathering/gatheringStatus")
	public void gatheringStatus(GatheringVO gatheringVO) {
		
		scheduledEndVO = scheduledEndService.selectIfExist(gatheringVO);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("scheduledEndVO", scheduledEndVO);
//		return scheduledEndVO;
	}
}
