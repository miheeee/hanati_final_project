package kr.ac.kopo.participant.service;

import java.util.List;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

public interface ParticipantService {

	/**
	 * safeAccountNo로 모임원 정보 조회
	 */
	public List<ParticipantVO> selectParticipantsBySafeAccountNo(GatheringVO gatheringVO);
	
	/**
	 * 계좌번호로 모임원 정보 조회 
	 */
	public List<ParticipantVO> selectByAccountNo(TransactionVO transactionVO);
	
	/**
	 * 모임 참여자의 알림 설정들 가져오기
	 */
	public ParticipantVO selectAllNotifySettings(GatheringVO gatheringVO);
	
	/**
	 * 모임통장 및 모임원 변경 알림 설정 변경
	 */
	public void ChangeAccountChangeNotify(ParticipantVO participantVO);
	
	/**
	 * 입출금 알림 설정 변경
	 */
	public void transferNotiChange(ParticipantVO participantVO);
	
	/**
	 * 회비 입금일 정기 알림 설정 변경 : 설정
	 */
	public void depositeDateNotiChangeY(ScheduledDepositDateVO scheduledDepositDateVO);
	
	/**
	 * 회비 입금일 정기 알림 설정 변경 : 해지
	 */
	public void depositeDateNotiChangeN(ScheduledDepositDateVO scheduledDepositDateVO);
}
