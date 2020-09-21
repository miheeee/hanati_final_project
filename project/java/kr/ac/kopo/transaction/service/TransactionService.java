package kr.ac.kopo.transaction.service;

import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.code.vo.CodeVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

public interface TransactionService {

	public void transfer(TransactionVO transactionVO);
	
	public List<CodeVO> transferForm();
	
	public List<TransactionVO> selectTranList(AccountVO accountVO);
}
