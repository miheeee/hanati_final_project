package kr.ac.kopo.transaction.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Repository
public class TransactionDAOImpl implements TransactionDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Override	//거래번호 미리 알아오기
	public int selectTransactionNo() {
		int transactionNo = sqlSession.selectOne("transaction.dao.TransactionDAO.selectTransactionNo");
		return transactionNo;
	}
	
	@Override	//거래 내역 찍기
	public void insertDetails(TransactionVO transactionVO) {
	
		sqlSession.insert("transaction.dao.TransactionDAO.insertDetails", transactionVO);
	}

	@Override	//안심계좌번호로 거래 내역 조회
	public List<TransactionVO> selectBySafeAccountNo(GatheringVO gatheringVO) {
		List<TransactionVO> transactionList = sqlSession.selectList("transaction.dao.TransactionDAO.selectBySafeAccountNo", gatheringVO);
		return transactionList;
	}

	@Override	//안심계좌번호로 회비 입금 내역 조회
	public List<TransactionVO> selectDepositedDuesList(GatheringVO gatheringVO) {
		List<TransactionVO> transactionList = sqlSession.selectList("transaction.dao.TransactionDAO.selectDepositedDuesList", gatheringVO);
		return transactionList;
	}

	@Override	//계좌번호와 기간으로  회비 입금 내역 가져오기(월 단위)
	public List<TransactionVO> selectDuesByMonthly(TransactionVO transactionVO) {
		List<TransactionVO> transactionList = sqlSession.selectList("transaction.dao.TransactionDAO.selectDuesByMonthly", transactionVO);
		return transactionList;
	}

	@Override	//계좌번호와 기간으로  회비 입금 내역 가져오기(연 단위)
	public List<TransactionVO> selectDuesByYearly(TransactionVO transactionVO) {
		List<TransactionVO> transactionList = sqlSession.selectList("transaction.dao.TransactionDAO.selectDuesByYearly", transactionVO);
		return transactionList;
	}

	@Override
	public List<TransactionVO> selectDuesByMember(ParticipantVO participantVO) {
		List<TransactionVO> transactionList = sqlSession.selectList("transaction.dao.TransactionDAO.selectDuesByMember", participantVO);
		return transactionList;
	}

	@Override	//거래번호로 거래내역 조회하기
	public TransactionVO selectByTranNo(int transactionNo) {
		
		TransactionVO transactionVO = sqlSession.selectOne("transaction.dao.TransactionDAO.selectByTranNo", transactionNo);
		
		return transactionVO;
	}	
}

