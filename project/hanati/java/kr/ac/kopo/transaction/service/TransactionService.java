package kr.ac.kopo.transaction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Service
public interface TransactionService {

	/**
	 * 이체
	 */
	public void transfer(TransactionVO transactionVO);
	
	/**
	 * 안심계좌번호로 거래내역 조회
	 */
	public List<TransactionVO> selectTransBySafeAccountNo(GatheringVO gatheringVO);

	/**
	 * 안심계좌번호로 회비 입금 내역 조회
	 */
	public List<TransactionVO> selectDepositedDuesList(GatheringVO gatheringVO);

	/**
	 * 기간별 회비 입금 내역 조회
	 */
	public List<TransactionVO> selectDuesByPeriod(TransactionVO transactionVO);
	
	/**
	 * 모임원별 회비 입금 내역 조회
	 */
	public List<TransactionVO> selectDuesByMember(ParticipantVO participantVO);
}
