package kr.ac.kopo.account.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.AccountService;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;


public class AccountController2 {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/account/apply")
	public String applyForm(Model model, HttpSession session) { 
		
//		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
//		List<AccountVO> accountList = accountService.selectAllAccount(loginVO); 
		  
//		ModelAndView mav = new ModelAndView("account/apply");
//		mav.addObject("accountList", accountList);
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		referenceData(model, loginVO);
		
		return "/account/apply";
	}
	
	private void referenceData(Model model, MemberVO member) {

		List<AccountVO> accountList = accountService.selectAllAccount(member); 
		List<String> accountNos = new ArrayList<String>();
		for(AccountVO account : accountList) {
			accountNos.add(account.getAccountNo());
		}
		model.addAttribute("accountNos", accountNos);
	}
	
	
	
}
