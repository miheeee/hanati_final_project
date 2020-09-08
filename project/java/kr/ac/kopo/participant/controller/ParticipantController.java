package kr.ac.kopo.participant.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.AccountService;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.service.ParticipantService;
import kr.ac.kopo.participant.vo.ParticipantVO;
import oracle.jdbc.proxy.annotation.Post;

@Controller
public class ParticipantController {

	@Autowired
	private ParticipantService participantService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountVO accountVO;

	
	//모임원 불러오기(속해있는 모든 모임통장에 대해) 
	@GetMapping("/participant")
	public ModelAndView manage(HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		Map<AccountVO, List<ParticipantVO>> map = participantService.selectParticipants(loginVO);
		
		ModelAndView mav = new ModelAndView("participant/manage");
		mav.addObject("accPartliMap", map);

//		for(AccountVO account : map.keySet()) {
//			System.out.println(account);
//			for(ParticipantVO p : map.get(account)) {
//				System.out.println(p);
//			}
//		}
			
		return mav;
	}
	
	//모임원으로 초대하기
	@GetMapping("/participant/invite")
	public ModelAndView invite(HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		List<AccountVO> accountList = accountService.selectOwnedGathering(loginVO);
		
		ModelAndView mav = new ModelAndView("participant/invite");
		mav.addObject("accountList", accountList);
		System.out.println(accountList.get(0).getHolder());
		mav.addObject("holder", accountList.get(0).getHolder());
		
		return mav;
	}
	
	//카카오링크 받고 접속하여 초대 수락하기(가입)
	@GetMapping("/participant/invite/{safeAccountNo}")
	public ModelAndView join(@PathVariable("safeAccountNo") String safeAccountNo) {
		
		accountVO.setSafeAccountNo(safeAccountNo);
		AccountVO account = participantService.join(accountVO);
		
		System.out.println(account);
		
		ModelAndView mav = new ModelAndView("participant/join");
		mav.addObject("accountVO", account);
		
		return mav;
	}
	
	//초대 수락하기 과정
	@PostMapping("/participant/join")
	public ModelAndView joinProcess(ParticipantVO participantVO, HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		
		//participantVO.setId(loginVO.getId());
		participantVO.setId("hong");
		participantVO.setType("302");

		participantService.joinProcess(participantVO);

		ModelAndView mav = new ModelAndView("participant/join");

		return mav;
	}
	
	//초대 수락하기 과정
	//@ResponseBody
//	@PostMapping("/participant/join")
//	public void joinProcess(ParticipantVO participantVO, HttpSession session) {
//		
//		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
//		
//		//participantVO.setId(loginVO.getId());
//		participantVO.setId("hong");
//		participantVO.setType("302");
//
//		participantService.joinProcess(participantVO);
//	}
	
	//멤버 끊기
	@PostMapping("/participant/dropout")
	public String dropout(ParticipantVO participantVO) {
		
		participantService.dropout(participantVO);
		
		return "redirect:/participant";
	}
}
