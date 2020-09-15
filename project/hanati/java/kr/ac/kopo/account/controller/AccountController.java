package kr.ac.kopo.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.glassfish.external.statistics.annotations.Reset;

import kr.ac.kopo.account.service.AccountService;
import kr.ac.kopo.account.vo.AccountVO;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountVO account;
	
	@ResponseBody
	@PostMapping("/account/checkPassword")
	public String checkMatchPassword(AccountVO accountVO) {
		
		//입력받은 계좌번호와 비밀번호에 맞는 계좌가 있는지 select해서 가져옴
		account = accountService.checkMatchPassword(accountVO);
		
		//*AccountVO객체를 바로 넘기면 안됨. null인지 아닌지 판별할 수 없음
		if(account == null) {
			return "no";
		}else {
			return "yes";
		}
	}
	
	/*
	 * @PostMapping("/account/checkPassword") public ModelAndView
	 * checkMatchPassword(AccountVO accountVO) {
	 * 
	 * //입력받은 계좌번호와 비밀번호에 맞는 계좌가 있는지 select해서 가져옴 account =
	 * accountService.checkMatchPassword(accountVO);
	 * 
	 * ModelAndView mav = new ModelAndView("gatherinig/apply");
	 * mav.addObject("account", account); return mav; }
	 */
	
}
