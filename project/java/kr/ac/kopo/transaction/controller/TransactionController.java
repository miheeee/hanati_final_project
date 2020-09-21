package kr.ac.kopo.transaction.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.AccountService;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.code.vo.CodeVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.transaction.service.TransactionService;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Controller
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	@Autowired
	AccountService accountService;
	
	//이체 폼
	@GetMapping("/transaction/transfer")
	public ModelAndView transferForm(HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		
		//은행 목록 가져오기
		List<CodeVO> codeList = transactionService.transferForm();
		
		//내가 가진 모든 입출금 계좌 조회
		List<AccountVO> accountList = accountService.selectAllAccount(loginVO);
	
		ModelAndView mav = new ModelAndView("transaction/transferForm");
		mav.addObject("codeList", codeList);
		mav.addObject("accountList", accountList);
		
		return mav;
	}
	
	//이체
	@PostMapping("/transaction/transfer")
	public String transfer(TransactionVO transactionVO) {
		
		transactionService.transfer(transactionVO);
		
		return "transaction/transfer";
	}
	
	//거래내역(임의로 계좌 하나에 대한 거래내역 띄워주기)
	@GetMapping("/transaction/list")
	public ModelAndView transactionList(HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		List<AccountVO> accountList = accountService.manage(loginVO);

		List<TransactionVO> transactionList = transactionService.selectTranList(accountList.get(0));
		
		ModelAndView mav = new ModelAndView("transaction/list");
		mav.addObject("accountList", accountList);
		mav.addObject("transactionList", transactionList);
		
		return mav;
	}

	
	//거래내역(사용자가 선택한 계좌에 대한 거레내역 띄워주기
	//@ResponseBody
	@PostMapping("/transaction/list")
	public String NewTransactionList(AccountVO accountVO, Model model) {
		
		List<TransactionVO> transactionList = transactionService.selectTranList(accountVO);

		model.addAttribute("transactionList", transactionList);
		
		return "transaction/listAjax";
	}
	
	
	
	
	
	
	
	
	/*
	//거래내역(사용자가 선택한 계좌에 대한 거레내역 띄워주기
	@PostMapping("/transaction/list")
	public List<TransactionVO> NewTransactionList(AccountVO accountVO) {
		
		List<TransactionVO> transactionList = transactionService.selectTranList(accountVO);
		
		return transactionList;
	}
	*/
	
	/*
	//거래내역(사용자가 선택한 계좌에 대한 거레내역 띄워주기
	@PostMapping("/transaction/list")
	public ModelAndView NewTransactionList(AccountVO accountVO) {
		
		List<TransactionVO> transactionList = transactionService.selectTranList(accountVO);
		
		ModelAndView mav = new ModelAndView("transaction/listAjax");
		mav.addObject("transactionList", transactionList);
		
		return mav;
	}
	*/
}
