package kr.ac.kopo.account.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.vo.ParticipantVO;

@Repository
public class AccountDAOImpl implements AccountDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<AccountVO> selectAll(MemberVO member) {
		
		List<AccountVO> accountList = sqlSession.selectList("account.dao.AccountDAO.selectAll", member);
		
		return accountList;
	}

	@Override
	public void apply(AccountVO accountVO) {
		
		sqlSession.update("account.dao.AccountDAO.updateToGathering", accountVO);
		
	}

	@Override
	public List<AccountVO> selectGathering(MemberVO memberVO) {

		List<AccountVO> accountList = sqlSession.selectList("account.dao.AccountDAO.selectGathering", memberVO);
		
		return accountList;
	}

	@Override
	public List<AccountVO> selectOwnedGathering(MemberVO memberVO) {
		
		List<AccountVO> accountList = sqlSession.selectList("account.dao.AccountDAO.selectOwnedGathering", memberVO);
		
		return accountList;
	}

	@Override
	public void terminate(AccountVO accountVO) {
		
		sqlSession.update("account.dao.AccountDAO.terminate", accountVO);
	}

	@Override
	public void realTerminate(AccountVO accountVO) {
		
		sqlSession.update("account.dao.AccountDAO.realTerminate", accountVO);
		
	}

	@Override
	public void terminateCancel(AccountVO accountVO) {
		
		sqlSession.update("account.dao.AccountDAO.terminateCancel", accountVO);
	}

	@Override
	public List<AccountVO> selectOwnedUsingGathering(MemberVO memberVO) {
		
		List<AccountVO> accountList = sqlSession.selectList("account.dao.AccountDAO.selectOwnedUsingGathering", memberVO);
		return accountList;
	}

	@Override
	public AccountVO selectByAccountNo(AccountVO accountVO) {
		
		AccountVO account = sqlSession.selectOne("account.dao.AccountDAO.selectByAccountNo", accountVO);
		
		return account;
	}

	@Override
	public AccountVO selectBySafeAccountNo(AccountVO accountVO) {
		
		AccountVO account = sqlSession.selectOne("account.dao.AccountDAO.selectBySafeAccountNo", accountVO);
		
		return account;
	}

	
	
//	@Override
//	public void switchType(String accountNo) {
//		sqlSession.update("account.dao.AccountDAO.switchType", accountNo);
//	}
	
}
