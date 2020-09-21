package kr.ac.kopo.transaction.dao;

import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.code.vo.CodeVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

public interface TransactionDAO {

	/**
	 * 출금
	 * @param transactionVO
	 */
	public void withdraw(TransactionVO transactionVO);
	
	/**
	 * 입금
	 * @param transactionVO
	 */
	public void deposit(TransactionVO transactionVO);
	
	/**
	 * 은행 코드, 이름 조회
	 */
	public List<CodeVO> selectBankCode();
	
	/**
	 * 거래내역 내역 찍기
	 */
	public void insertDetails(TransactionVO transactionVO) ;
	
	/**
	 * 계좌번호로 거래내역 조회하기
	 */
	public List<TransactionVO> selectTranList(AccountVO accountVO);
	
	/**
	 * 거래번호 미리 얻어오기
	 */
	public int selectTransactionNo();
	
	/**
	 * 거래번호로 거래내역 조회하기
	 */
	public TransactionVO selectByTranNo(int no);
}
