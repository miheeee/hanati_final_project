package kr.ac.kopo.account.dao;

import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.vo.ParticipantVO;

public interface AccountDAO {

	/**
	 * 모임통장으로 전환
	 * @param accountNo
	 */
//	public void switchType(String accountNo);
	
	/*
	 * 전체계좌 조회
	 * @param MemberVO
	 */
	public List<AccountVO> selectAll(MemberVO memberVO);
	
	/*
	 * 모임통장 신청(전환)
	 */
	public void apply(AccountVO accountVO);
	
	/*
	 * 모임원 목록에 모임주 추가
	 */
	
	/**
	 * 모임통장 관리(조회)
	 */
	public List<AccountVO> selectGathering(MemberVO memberVO);
	
	/**
	 * 모임주로 있는 모임통장만 조회
	 */
	public List<AccountVO> selectOwnedGathering(MemberVO memberVO);
	
	/*
	 * 모임통장 사용 종료 신청
	 */
	public void terminate(AccountVO accountVO);
	
	/**
	 * 모임통장이 진짜로 종료되어 입출금통장으로 바뀌고 관련 정보는 없어짐
	 */
	public void realTerminate(AccountVO accountVO);
	
	/**
	 * 모임통장 사용 종료 취소
	 */
	public void terminateCancel(AccountVO accountVO);
	
	/**
	 * 모임주로 있으면서 사용중인 (종료 신청상태가 아닌) 모임통장만 조회
	 */
	public List<AccountVO> selectOwnedUsingGathering(MemberVO memberVO);
	
	/**
	 * 계좌번호로 통장 정보 조회
	 */
	public AccountVO selectByAccountNo(AccountVO accountVO);

	/**
	 * 안심계좌번호로 통장 정보 조회
	 */
	public AccountVO selectBySafeAccountNo(AccountVO accountVO);
	
	
}
