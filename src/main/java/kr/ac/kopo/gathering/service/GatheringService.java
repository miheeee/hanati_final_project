package kr.ac.kopo.gathering.service;

import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.vo.MemberVO;

public interface GatheringService {

	/**
	 * 모임통장 신청 - 폼(모임통장 제외한 일반 입출금 통장만 조회)
	 */
	public List<AccountVO> selectAccountExceptGathering(MemberVO loginVO);
	
	/**
	 * 안심계좌번호 생성 & 모임통장 테이블인 gathering에 데이터 삽입  & 참여자 목록에 모임주 추가
	 */
	public GatheringVO insertGathering(GatheringVO gatheringVO);
	
	/**
	 * 아이디로 참여하고 있는 모든 모임통장 정보 조회
	 */
	public List<GatheringVO> selectParticipatingGatheringList(MemberVO memberVO);

	/**
	 * 안심 계좌번호로 모임통장 정보 조회 
	 */
	public GatheringVO selectGatheringBySafeAccountNo(GatheringVO gatheringVO);

	/**
	 * 모임주로 있는 모임통장 정보만 조회
	 */
	public List<GatheringVO> selectOwnedGatheringById(MemberVO memberVO); 
	
	/**
	 * 계좌번호로 모임통장 정보 조회
	 */
	public GatheringVO selectByAccountNo(String accountNo);
}
