package kr.ac.kopo.transaction.dao;

import java.util.List;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

public interface TransactionDAO {

	/**
	 * 거래번호 미리 얻어오기
	 */
	public int selectTransactionNo();
	
	/**
	 * 거래내역 내역 찍기
	 */
	public void insertDetails(TransactionVO transactionVO) ;

	/**
	 * 계좌번호로 거래내역 조회하기
	 */
	public List<TransactionVO> selectBySafeAccountNo(GatheringVO gatheringVO);
	
	/**
	 * 안심계좌번호로 회비 입금 내역 조회
	 */
	public List<TransactionVO> selectDepositedDuesList(GatheringVO gatheringVO);

	/**
	 * 계좌번호와 기간으로  회비 입금 내역 가져오기(월 단위)
	 */
	public List<TransactionVO> selectDuesByMonthly(TransactionVO transactionVO);
	
	/**
	 * 계좌번호와 기간으로  회비 입금 내역 가져오기(연 단위)
	 */
	public List<TransactionVO> selectDuesByYearly(TransactionVO transactionVO);
	
	/**
	 * 모임원별 회비 입금 내역 조회
	 */
	public List<TransactionVO> selectDuesByMember(ParticipantVO participantVO);
	
	/**
	 * 거래번호로 거래내역 조회하기
	 */
	public TransactionVO selectByTranNo(int no);	
}
