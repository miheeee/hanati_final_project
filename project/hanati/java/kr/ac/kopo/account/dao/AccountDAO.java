package kr.ac.kopo.account.dao;

import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

public interface AccountDAO {

	/**
	 * 모임통장 제외한 일반 입출금 통장만 전체 조회 - 모임통장 신청폼
	 */
	public List<AccountVO> selectExceptGathering(MemberVO memberVO);
	
	/**
	 * 계좌번호로 입출금 통장 정보 알아오기
	 */
	public AccountVO selectByAccountNo(AccountVO accountVO);

	/**
	 * 비밀번호 일치 여부 확인
	 */
	public AccountVO checkMatchPassword(AccountVO accountVO);
	
	/**
	 * 보유하는 모든 입출금 계좌 조회
	 */
	public List<AccountVO> selectAccountList(MemberVO memberVO);
	
	/**
	 * 출금
	 */
	public void withdraw(TransactionVO transactionVO);
	
	/**
	 * 입금
	 */
	public void deposit(TransactionVO transactionVO);	
}
