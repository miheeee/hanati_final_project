package kr.ac.kopo.dues.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map.Entry;

import kr.ac.kopo.account.service.AccountService;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.dues.service.DuesService;
import kr.ac.kopo.dues.vo.DuesVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.service.ParticipantService;
import kr.ac.kopo.participant.vo.ParticipantVO;

@Controller
public class DuesController {

	@Autowired
	private DuesService duesService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ParticipantService participantService;
	
	@Autowired
	private DuesVO duesVO;
	
	@GetMapping("/dues/status")
	public ModelAndView status(HttpSession session) {
		
		//참여하고 있는 모임통장 목록 가져오기
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		List<AccountVO> accountList = accountService.manage(loginVO);
		
		//회비 관리를 위한  계좌번호로 모임원 목록, 회비 입금 내역 가져오기
		Map<String, Object> map = duesService.dueStatus(accountList.get(0));
		
		ModelAndView mav = new ModelAndView("dues/duesStatus");
		mav.addObject("accountList", accountList);
		mav.addObject("participantList", map.get("participantList"));
		mav.addObject("duesList", map.get("duesList"));
		
		//가져온 회비 입금 내역에서 기간 추출
		Set<String> monthSet = new HashSet<String>();
		for(DuesVO dues : (List<DuesVO>)map.get("duesList")) {
			monthSet.add(dues.getTime().substring(0,4) + "년 " + 
							dues.getTime().substring(5,7) + "월");
			monthSet.add(dues.getTime().substring(0,4) + "년 ");
		}
		//최근순대로 정렬
		List<String> monthList = new ArrayList<>(monthSet);		//먼저 set => list로 변환
		Collections.sort(monthList, Collections.reverseOrder());
		mav.addObject("monthList", monthList);
		
		return mav;
	}
	
	@PostMapping("/dues/member")
	public String duesByMember(ParticipantVO participantVO, Model model) {
		
		//모임원별 개인 회비 입금 내역
		Map<Set<String>, List<DuesVO>> map = duesService.duesByMember(participantVO);
		
        for(Entry<Set<String>, List<DuesVO>> elem : map.entrySet()){
        	model.addAttribute("monthSet", elem.getKey());
        	model.addAttribute("duesList", elem.getValue());
        }

		return "/dues/memberAjax";
	}
	
	
	@PostMapping("/dues/period")
	public String duesByPeriod(DuesVO duesVO, @RequestParam("period") String time) {
		
		//모임원별 개인 회비 입금 내역
		Map<Set<String>, List<DuesVO>> map = duesService.duesByMember(participantVO);
		
        for(Entry<Set<String>, List<DuesVO>> elem : map.entrySet()){
        	model.addAttribute("monthSet", elem.getKey());
        	model.addAttribute("duesList", elem.getValue());
        }

		return "/dues/memberAjax";
	}
	
}
