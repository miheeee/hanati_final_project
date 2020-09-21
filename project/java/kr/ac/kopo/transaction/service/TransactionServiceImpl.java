package kr.ac.kopo.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.code.vo.CodeVO;
import kr.ac.kopo.transaction.dao.TransactionDAO;
import kr.ac.kopo.transaction.vo.TransactionVO;
import kr.ac.kopo.notify.service.NotifyService;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionDAO transactionDAO;
	
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	AccountVO fromAccount;
	
	@Autowired
	AccountVO toAccount;
	
	@Autowired
	TransactionVO toTransaction;
	
	@Autowired
	NotifyService notifyService;
	
//	@Transactional
	@Override
	public void transfer(TransactionVO fromTransaction) {
		//출금은 공통
		transactionDAO.withdraw(fromTransaction);
		
		//거래내역 삽입을 위해 출금 계좌 정보 알아오기(소유주이름, 잔액)
		fromAccount.setAccountNo(fromTransaction.getAccountNo());
		fromAccount = accountDAO.selectByAccountNo(fromAccount);
		
		if("".equals(fromTransaction.getIndication())) {
			fromTransaction.setIndication(fromAccount.getHolder());
			toTransaction.setIndication(fromAccount.getHolder());
		}else {
			toTransaction.setIndication(fromTransaction.getIndication());
		}
		
		fromTransaction.setBalance(fromAccount.getBalance());
		fromTransaction.setClassification("402");
		
		// i) 타행 이체면 출금만 수행
		if(!fromTransaction.getCounterpartBank().equals("081")) {
	
			//출금 거래 내역 삽입
			fromTransaction.setCounterpart("타행 이체");
			int transactionNo = transactionDAO.selectTransactionNo();
			fromTransaction.setTransactionNo(transactionNo);
			transactionDAO.insertDetails(fromTransaction);
			//출금 알림
			notifyService.transferNoti(transactionNo);
		}

		// ii) 하나은행으로 이체면 출금에 더해 입금까지 모두 수행
		if(fromTransaction.getCounterpartBank().equals("081")) {
			
			//입금 수행
			toTransaction.setAccountNo(fromTransaction.getCounterpartAccountNo());
			toTransaction.setAmount(fromTransaction.getAmount());
			transactionDAO.deposit(toTransaction);
			
			//입금 거래내역 삽입을 위해 입금 계좌 정보 알아오기(소유주이름, 잔액)
			toAccount.setAccountNo(toTransaction.getAccountNo());
			toAccount = accountDAO.selectByAccountNo(toAccount);
			
			//출금 거래 내역 삽입
			fromTransaction.setCounterpart(toAccount.getHolder());
			int transactionNo = transactionDAO.selectTransactionNo();
			fromTransaction.setTransactionNo(transactionNo);
			
			transactionDAO.insertDetails(fromTransaction);
			//출금 알림
			notifyService.transferNoti(transactionNo);
		
			//입금 거래 내역 삽입
			toTransaction.setBalance(toAccount.getBalance());
			toTransaction.setClassification("401");
			toTransaction.setCounterpart(fromAccount.getHolder());
			toTransaction.setCounterpartAccountNo(fromTransaction.getAccountNo());
			toTransaction.setCounterpartBank("081");
			
			int transactionNo2 = transactionDAO.selectTransactionNo();
			toTransaction.setTransactionNo(transactionNo2);
			
			transactionDAO.insertDetails(toTransaction);
			//입금 알림
			notifyService.transferNoti(transactionNo2);
		}	
	}

	@Override
	public List<CodeVO> transferForm() {
		List<CodeVO> codeList = transactionDAO.selectBankCode();
		return codeList;
	}

	@Override
	public List<TransactionVO> selectTranList(AccountVO accountVO) {
		List<TransactionVO> transactionList = transactionDAO.selectTranList(accountVO);
		return transactionList;
	}


	
	
}
