package kr.ac.kopo.account.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Repository
public class AccountDAOImpl implements AccountDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;	
	
	@Autowired
	private AccountVO account;
	
	@Override	//모임통장 제외한 일반 입출금 통장만 전체 조회 - 모임통장 신청폼
	public List<AccountVO> selectExceptGathering(MemberVO memberVO) {
		List<AccountVO> accountList = sqlSession.selectList("account.dao.AccountDAO.selectExceptGathering", memberVO);
		return accountList;
	}

	@Override	//계좌번호로 입출금 통장 정보 알아오기
	public AccountVO selectByAccountNo(AccountVO accountVO) {
		AccountVO account = sqlSession.selectOne("account.dao.AccountDAO.selectByAccountNo", accountVO);
		return account;
	}

	@Override	//비밀번호 일치 여부 확인
	public AccountVO checkMatchPassword(AccountVO accountVO) {
		account = sqlSession.selectOne("account.dao.AccountDAO.selectMatchPassword", accountVO);
		return account;
	}

	@Override	//보유하는 모든 입출금 계좌 조회
	public List<AccountVO> selectAccountList(MemberVO memberVO) {
		List<AccountVO> accountList = sqlSession.selectList("account.dao.AccountDAO.selectAccountList", memberVO);
		return accountList;
	}

	@Override	//출금
	public void withdraw(TransactionVO transactionVO) {
		sqlSession.update("account.dao.AccountDAO.withdraw", transactionVO);	
	}

	@Override	//입금
	public void deposit(TransactionVO transactionVO) {
		sqlSession.update("account.dao.AccountDAO.deposit", transactionVO);
		
	}	

	
}
