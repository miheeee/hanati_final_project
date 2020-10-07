package kr.ac.kopo.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.View;
//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.gathering.service.GatheringService;
import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;
import oracle.jdbc.proxy.annotation.Post;

@SessionAttributes("loginVO")	//loginVO라는 이름으로 저장된 변수는 request가 아닌 session에 저장하라. 베열이나 여러 종류가 들어올 수 있음
@Controller
public class MemberController {

	@Autowired
	private MemberService memberService; 
	
	@Autowired
	private GatheringService gatheringService;
	
	//로그인 창 띄우기
	@GetMapping("/login")
	public String loginForm() {
		return "/login/login";
	}
	
	//로그인 정보 확인
	@PostMapping("/login")
	public ModelAndView login(/* @Valid */ MemberVO member, HttpSession session /*, BindingResult result */) {
		
		ModelAndView mav = new ModelAndView();
		
		
//		if(result.hasErrors()) {
//			mav.setViewName("login/login"); 
//			return mav;
//		}
		
		MemberVO loginVO = memberService.login(member);
		
		//로그인 실패
		if(loginVO == null) {
			mav.setViewName("redirect:/login");
		}else {
			//로그인 성공
			String dest = (String)session.getAttribute("dest");
			if(dest == null) {
				mav.setViewName("redirect:/");
			}else {
				if(dest.substring(dest.length()-3).equals("jpg") || dest.substring(dest.length()-3).equals("ico")) {			//??처음 로그인할 때 dest가 왜 이런지 모르겠음
					mav.setViewName("redirect:/");
				}else {
					if(dest.equals("/gathering/apply")) {
						mav.setViewName("redirect:/");
					}else {
						mav.setViewName("redirect:" + dest);						
					}
				}
			}
			mav.addObject("loginVO", loginVO);
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(SessionStatus status) {
		
		status.setComplete();		//세션에 저장되어 있는 거 다 지워라
		System.out.println(status.isComplete());
	
		return "redirect:/";
	}
	
	

}
