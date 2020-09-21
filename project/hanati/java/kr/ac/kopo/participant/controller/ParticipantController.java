package kr.ac.kopo.participant.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.service.ParticipantService;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;

@Controller
public class ParticipantController {

	@Autowired
	private ParticipantService participantService;

	@Autowired
	private ScheduledDepositDateVO scheduledDepositDateVO;

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
}
