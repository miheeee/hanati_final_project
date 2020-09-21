package kr.ac.kopo.account.controller;

import java.util.List;

import javax.security.auth.login.AccountLockedException;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.AccountService;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.service.ParticipantService;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;
import kr.ac.kopo.scheduler.Scheduler;
import kr.ac.kopo.notify.service.NotifyService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ParticipantVO participantVO;
	
	@Autowired
	private NotifyService notifyService;
	
	@Autowired
	private Scheduler scheduler;
	
	@Autowired
	private ScheduledDepositDateVO scheduledDepositDateVO;
	
	//모임통장 신청 폼
	@GetMapping("/account/apply")
	public ModelAndView applyForm(HttpSession session) { 
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		List<AccountVO> accountList = accountService.selectAllAccount(loginVO); 
		
		ModelAndView mav = new ModelAndView("account/apply");
		mav.addObject("accountList", accountList);
		
		return mav;
	}
	
	//모임통장 신청 프로세스
	@PostMapping("/account/apply")
	public ModelAndView apply(AccountVO accountVO, HttpSession session) {
	
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		accountVO.setId(loginVO.getId());
		AccountVO gateringAccount = accountService.apply(accountVO);
		
		ModelAndView mav = new ModelAndView("account/applyComplete");
		mav.addObject("accountVO", gateringAccount);
		
		return mav;
	}
	
	//모임통장 관리(조회)
	@GetMapping("/account")
	public ModelAndView manage(HttpSession session) {
		
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		List<AccountVO> accountList = accountService.manage(loginVO);
		
//		for(AccountVO account : accountList) {
//			System.out.println(account);
//		}
		
		ModelAndView mav = new ModelAndView("account/manage");
		mav.addObject("accountList", accountList);
		
		return mav;
	}
	
	//모임통장 종료폼(모임주인 것만 조회해서 보여주기)
	@GetMapping("/account/terminate")
	public ModelAndView ownedGathering(HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		List<AccountVO> accountList = accountService.selectOwnedGathering(loginVO);
		
		ModelAndView mav = new ModelAndView("account/terminate");
		mav.addObject("accountList", accountList);
		return mav;
	}
	
	//모임통장 종료
	@PostMapping("/account/terminate")
	public String terminate(AccountVO account) {
		
		accountService.terminate(account);
		notifyService.scheduledTerminate(account);

		return "redirect:/account/terminate";
	}
	
	@PostMapping("/account/terminateCancel")
	public String terminateCancel(AccountVO account) {
		
		accountService.terminateCancel(account);

		return "redirect:/account/terminate";
	}
	
	//모임 설정(디테일)
	@GetMapping("/account/setting/{safeAccountNo}")
	public ModelAndView setting(@PathVariable("safeAccountNo") String safeAccountNo, HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		
		participantVO = accountService.AccountSetting(safeAccountNo, loginVO);
		
		ModelAndView mav = new ModelAndView("account/notify");
		mav.addObject("participantVO", participantVO);
		
		System.out.println(participantVO);
		
		return mav;
	}
	
	//모임통장 및 모임원 변경 알림 설정 변경
	@ResponseBody
	@PostMapping("/account/notify/accountChange")
	public void accountChange(ParticipantVO participantVO, HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		participantVO.setId(loginVO.getId());
		
		accountService.accountChange(participantVO);
	}
	
	//입출금 알림 설정 변경
	@ResponseBody
	@PostMapping("/account/notify/transferNotiChange")
	public void transferNotiChange(ParticipantVO participantVO, HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		participantVO.setId(loginVO.getId());
		
		accountService.transferNotiChange(participantVO);
	}
	
	//회비 입금일 정기 알림 설정 변경 : 설정
	@ResponseBody	
	@PostMapping("/account/notify/depositeDateNotiChangeY")
	public void depositeDateNotiChangeY(ParticipantVO participantVO, @RequestParam("dueDate") String dueDate, HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		participantVO.setId(loginVO.getId());
		
		accountService.depositeDateNotiChange(participantVO);
		
		//회비 입금일 알림 내역에 추가
		scheduledDepositDateVO.setAccountNo(participantVO.getAccountNo());
		scheduledDepositDateVO.setDueDate(Integer.parseInt(dueDate));
		scheduler.insertNotifyDepositDate(scheduledDepositDateVO);

	}
	
	//회비 입금일 정기 알림 설정 변경 : 해지
	@ResponseBody
	@PostMapping("/account/notify/depositeDateNotiChangeN")
	public void depositeDateNotiChangeN(ParticipantVO participantVO, HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		participantVO.setId(loginVO.getId());
		
		accountService.depositeDateNotiChange(participantVO);
	}
	

}