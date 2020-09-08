package kr.ac.kopo.transaction.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.code.vo.CodeVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Repository
public class TransactionDAOImpl implements TransactionDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void withdraw(TransactionVO transactionVO) {
		sqlSession.update("transaction.dao.TransactionDAO.withdraw", transactionVO);
	}

	@Override
	public void deposit(TransactionVO transactionVO) {
		sqlSession.update("transaction.dao.TransactionDAO.deposit", transactionVO);
	}

	@Override
	public List<CodeVO> selectBankCode() {
		List<CodeVO> codeList = sqlSession.selectList("transaction.dao.TransactionDAO.selectBankCode");
		return codeList;
	}

	@Override
	public void insertDetails(TransactionVO transactionVO) {
	
		sqlSession.insert("transaction.dao.TransactionDAO.insertDetails", transactionVO);
		
	}

	@Override
	public List<TransactionVO> selectTranList(AccountVO accountVO) {
		List<TransactionVO> transactionList = sqlSession.selectList("transaction.dao.TransactionDAO.selectTranList", accountVO);
		return transactionList;
	}

	//거래번호 미리 알아오기
	@Override
	public int selectTransactionNo() {
		int transactionNo = sqlSession.selectOne("transaction.dao.TransactionDAO.selectTransactionNo");
		return transactionNo;
	}

	//거래번호로 거래내역 조회하기
	@Override
	public TransactionVO selectByTranNo(int transactionNo) {
		
		TransactionVO transactionVO = sqlSession.selectOne("transaction.dao.TransactionDAO.selectByTranNo", transactionNo);
		
		return transactionVO;
	}

	
	
}
