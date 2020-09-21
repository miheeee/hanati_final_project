package kr.ac.kopo.participant.dao;

import java.util.List;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

public interface ParticipantDAO {

	/**
	 * 모임원(모임주 포함) 추가
	 */
	public void insertParicipant(ParticipantVO participantVO);

	/**
	 * safeAccountNo로 모임원 정보 조회
	 */
	public List<ParticipantVO> selectBySafeAccountNo(GatheringVO gatheringVO);
	
	/**	쓸 지 안 쓸 지 잘 모르겠음
	 * 입금 내역을 거래 내역에 추가하기 전, 입금 계좌가 모임계좌이며 입금한 사람이 해당 계좌의 모임원인지 여부 판단
	 */
	public ParticipantVO selectIfDepositDues(TransactionVO transactionVO);
	
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
	 * 회비 입금일 정기 알림 설정 변경
	 */
	public void depositeDateNotiChange(ParticipantVO participantVO);
	
	/**
	 * 오늘 종료 예정인 모임통장의 모임원 정보 알아오기(계좌번호, id)
	 */
	public List<ParticipantVO> participantTerminateDate();
	
	/**
	 * 종료하려는 모임계좌의 모임원 모두 삭제
	 */
	public void deleteAllParticipant();
	
	/**
	 * 모임통장 및 모임원 변동 알림을 받으려는 모임원들의 전화번호 얻어오기
	 */ 
	public List<MemberVO> selectAccChangeY(ParticipantVO participantVO);
	
	/**
	 * 입출금 알림을 받으려는 모임원들의 전화번호 얻어오기
	 */
	public List<MemberVO> selectTransferY(ParticipantVO participantVO);

	/**
	 * 멤버 삭제
	 */
	public void deleteParticipant(ParticipantVO participantVO);
}
