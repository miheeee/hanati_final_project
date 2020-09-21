package kr.ac.kopo.participant.service;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.authentication.vo.AuthenticationVO;
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

	/**
	 * 모임원 초대 링크에 보낸 인증번호 DB에 저장
	 */
	public void saveAuthenticationNum(AuthenticationVO authenticationVO);

	/**
	 * 가입 프로세스(모임원에 추가 및 모임통장 정보 가져오기)
	 */
	public GatheringVO joinProcess(ParticipantVO participantVO);

	/**
	 * 멤버 삭제
	 */
	public void deleteParticipant(ParticipantVO participantVO);
}
