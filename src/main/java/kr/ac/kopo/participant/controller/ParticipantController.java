package kr.ac.kopo.participant.controller;

import java.util.List;

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

import kr.ac.kopo.account.service.AccountService;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.authentication.service.AuthenticationService;
import kr.ac.kopo.authentication.vo.AuthenticationVO;
import kr.ac.kopo.gathering.service.GatheringService;
import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.service.ParticipantService;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;
import oracle.jdbc.proxy.annotation.Post;

@Controller
public class ParticipantController {

	@Autowired
	private ParticipantService participantService;

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private GatheringService gatheringService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ScheduledDepositDateVO scheduledDepositDateVO;

	@Autowired
	private AuthenticationVO authenticationVO;
	
	@Autowired
	private GatheringVO gatheringVO;
	
	@Autowired
	private AccountVO account;
	
	// 모임통장 및 모임원 변경 알림 설정 변경
	@ResponseBody
	@PostMapping("/participant/notify/accountChange")
	public void accountChange(ParticipantVO participantVO, HttpSession session) {

		MemberVO loginVO = (MemberVO) session.getAttribute("loginVO");
		participantVO.setId(loginVO.getId());

		participantService.ChangeAccountChangeNotify(participantVO);
	}
	
	 //입출금 알림 설정 변경
	 @ResponseBody
	 
	 @PostMapping("/participant/notify/transferNotiChange") public void
	 transferNotiChange(ParticipantVO participantVO, HttpSession session) {
	 
	 MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
	 participantVO.setId(loginVO.getId());
	  
	 participantService.transferNotiChange(participantVO); }


	// 회비 입금일 정기 알림 설정 변경 : 설정
	@ResponseBody
	@PostMapping("/participant/notify/depositDateNotiChangeY")
	public void depositeDateNotiChangeY(ParticipantVO participantVO, @RequestParam("regularDay") String regularDay,
			HttpSession session) {

		MemberVO loginVO = (MemberVO) session.getAttribute("loginVO");

		// 알림 설정을 'Y'로 업데이트하고 회비 입금일 알림 내역에 추가
		scheduledDepositDateVO.setAccountNo(participantVO.getAccountNo());
		scheduledDepositDateVO.setRegularDay(Integer.parseInt(regularDay));
		scheduledDepositDateVO.setId(loginVO.getId());

		participantService.depositeDateNotiChangeY(scheduledDepositDateVO);

	}

	// 회비 입금일 정기 알림 설정 변경 : 해지
	@ResponseBody
	@PostMapping("/participant/notify/depositDateNotiChangeN")
	public void depositeDateNotiChangeN(ParticipantVO participantVO, HttpSession session) {

		MemberVO loginVO = (MemberVO) session.getAttribute("loginVO");

		// 알림 설정을 'Y'로 업데이트하고 회비 입금일 알림 내역에 추가
		scheduledDepositDateVO.setAccountNo(participantVO.getAccountNo());
		scheduledDepositDateVO.setId(loginVO.getId());

		participantService.depositeDateNotiChangeN(scheduledDepositDateVO);

	}
	
	//모임원 초대 링크에 보낸 인증번호 DB에 저장
	@GetMapping("/participant/authentication/{code}")
	public String saveAuthenticationNum(@PathVariable("code") String code) {
		
		//code에서 처음 11글자는 안심계좌번호, 다음 4글자는 인증번호
		authenticationVO.setSafeAccountNo(code.substring(0, 11));
		authenticationVO.setAuthenticationNum(code.substring(11, 15));
		
		participantService.saveAuthenticationNum(authenticationVO);	
		
		return "redirect:/gathering/detail/" + code.substring(0, 11);
	}
	
	//가입 (카카오톡으로 보낸 링크로 접속)
	@GetMapping("/participant/invite/{code}")
	public ModelAndView join(@PathVariable("code") String code, HttpSession session, HttpServletRequest request) {
		
		if(code != null && code.length() == 15) {
			//db에서 인증번호 맞는지 대조
			authenticationVO.setSafeAccountNo(code.substring(0, 11));
			authenticationVO.setAuthenticationNum(code.substring(11, 15));
			AuthenticationVO authentication = authenticationService.selectIfExist(authenticationVO);
			//맞다면 모임통장 소유주 이름과 모임통장 정보를 가져오기
			if(authentication != null) {

				// 모임통장 정보를 가져오기
				gatheringVO.setSafeAccountNo(authenticationVO.getSafeAccountNo());
				GatheringVO gathering = gatheringService.selectGatheringBySafeAccountNo(gatheringVO);
				
				// 모임통장 소유주 이름 가져오기
				account = accountService.selectBySafeAccountNo(gatheringVO);
				
				//현재 로그인되어있는지 확인
				MemberVO loginVO = (MemberVO) session.getAttribute("loginVO");
				
				//얜 왜 로그인 인터셉터가 안 가져가지?
				  if(loginVO == null) {
				      
				      String uri = request.getRequestURI();
				      uri = uri.substring(request.getContextPath().length());	//요청된 uri
				  
				  String query = request.getQueryString();
				
				  if(query != null && query.length() != 0)
				     uri = uri + "?" + query;
				  session.setAttribute("dest", uri);
				  }
		          
				ModelAndView mav = new ModelAndView("participant/join");
				mav.addObject("gatheringVO", gathering);
				mav.addObject("accountVO", account);
				return mav;
			}
		}	
		ModelAndView mav = new ModelAndView("participant/error");
		return mav;
	}
	
	//가입 프로세스(모임원에 추가)
	@PostMapping("/participant/join")
	public String joinProcess(ParticipantVO participantVO, HttpSession session, Model model, HttpServletRequest request) {
		
		//모임원 추가 후 모임통장 정보 가져오기
		MemberVO loginVO = (MemberVO) session.getAttribute("loginVO");
		participantVO.setId(loginVO.getId());
		gatheringVO = participantService.joinProcess(participantVO);
		
		//인증번호 만료하기(삭제)
		String url = request.getHeader("referer");
		String code = url.split("/")[6];
		authenticationVO.setAuthenticationNum(code.substring(11));
		authenticationVO.setSafeAccountNo(code.substring(0, 11));
		authenticationService.expireAuthenticationNum(authenticationVO);
		
		model.addAttribute("gatheringVO", gatheringVO);
		
		return "/participant/joinCompleteAjax";
	}
	
	
	//멤버 내보내기
	@ResponseBody
	@PostMapping("/participant/expel")
	public void expel(ParticipantVO participantVO, String safeAccountNo) {
		
		participantService.deleteParticipant(participantVO);
//		gatheringVO = participantService.expel(participantVO);
//		String safeAccountNo = gatheringVO.getSafeAccountNo();
//		System.out.println("안녕");
//		return "redirect:/gathering/detail/" + safeAccountNo;
	}
	
//	//모임원 목록 조회
//	@PostMapping("/participant/select")
//	public String selectParticipants(GatheringVO gatheringVO, Model model) {
//		
//		System.out.println("들어오나?");
//		List<ParticipantVO> participantList = participantService.selectParticipantsBySafeAccountNo(gatheringVO);
//		model.addAttribute("participantList", participantList);
//		
//		return "/participant/participantListAjax";
//		
//	}

	
}