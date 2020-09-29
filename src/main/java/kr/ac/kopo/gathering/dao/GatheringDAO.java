package kr.ac.kopo.gathering.dao;

import java.util.List;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

public interface GatheringDAO {

	/**
	 * gatheringVO 데이터 삽입
	 */
	public void insertGathering(GatheringVO gatheringVO);
	
	//모임통장 정보 조회
	public GatheringVO selectByAccountNo(String accountNo);
	
	/**
	 * 아이디로 참여하고 있는 모든 모임통장 정보 조회
	 */
	public List<GatheringVO> selectParticipatingGatheringList(MemberVO memberVO); 

	/**
	 * 안심 계좌번호로 모임통장 정보 조회 
	 */
	public GatheringVO selectBySafeAccountNo(GatheringVO gatheringVO); 

	/**
	 * 모임주로 있는 모임통장 정보만 조회
	 */
	public List<GatheringVO> selectOwnedGatheringById(MemberVO memberVO); 
	
	/**
	 * 오늘이 종료일인 모임통장의 정보 알아오기(별칭, 안심계좌번호)
	 */
	public List<GatheringVO> accountTodayTerminateDate();

	/**
	 * 오늘이 종료일인 모임통장을 테이블에서 삭제 
	 */
	public void deleteGathering();

	/**
	 * 오늘 회비 입금일인 모임통장의 정보 알아오기(별칭, 안심계좌번호)
	 */
	public List<GatheringVO> accountTodayDepositDate();
	
	/**
	 * 출금
	 */
	public void withdraw(TransactionVO transactionVO);
	
	/**
	 * 입금
	 */
	public void deposit(TransactionVO transactionVO);
}
