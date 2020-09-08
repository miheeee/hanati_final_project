package kr.ac.kopo.participant.dao;

import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.vo.ParticipantVO;

public interface ParticipantDAO {
	
	/**
	 * 모임원 추가(모임주, 모임원 둘 다 해당)
	 * @param participantVO
	 */
	public void insert(ParticipantVO participantVO);
	
	/**
	 * 멤버 끊기 (모임계좌의 모임원 정보 삭제)
	 */
	public void delete(ParticipantVO participantVO);

	/**
	 * 모임원 조회(속해있는 모든 모임통장에 대해)
	 */
	public List<ParticipantVO> selectParticipants(MemberVO loginVO);
	
	/**
	 * 계좌번호로 모임통장 알림 정보 가져오기
	 */
	public ParticipantVO selecNotiByAccNo(ParticipantVO participantVO);
	
	/**
	 * 모임통장 및 모임원 변동 알림 설정 변경
	 */
	public void accountChange(ParticipantVO participantVO);
	
	/**
	 * 입출금 알림 설정 변경
	 */
	public void transferNotiChange(ParticipantVO participantVO);

	/**
	 * 회비 입금일 정기 알림 설정 변경
	 */
	public void depositeDateNotiChange(ParticipantVO participantVO);

	/**
	 * 모임통장 및 모임원 변동 알림을 받으려는 모임원들의 전화번호 얻어오기
	 */ 
	public List<MemberVO> selectAccChangeY(ParticipantVO participantVO);
	
	/**
	 * 입출금 알림을 받으려는 모임원들의 전화번호 얻어오기
	 */
	public List<MemberVO> selectTransferY(ParticipantVO participantVO);
	
	/**
	 * 계좌번호로 모임통장에 속해있는 모임원 조회 
	 */
	public List<ParticipantVO> selecByAccountNo(AccountVO accountVO);
}
