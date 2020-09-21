package kr.ac.kopo.member.dao;

import java.util.List;

import kr.ac.kopo.member.vo.MemberVO;

public interface MemberDAO {

	/**
	 * 로그인 (아이디, 비밀번호 일치 여부)
	 */
	MemberVO login(MemberVO member);
	
	/**
	 * 아이디로 회원 정보 조회
	 */
	public MemberVO selectById(MemberVO memberVO);
	
	/**
	 * 오늘 종료 예정인 모임통장의 모임원 id에 맞는 전화번호 알아오기
	 */
	public List<MemberVO> memberTodayTerminateDate();

	/**
	 * 오늘 입금일인 모임원 id에 맞는 전화번호 알아오기
	 */
	public List<MemberVO> memberTodayDepositDate();
}
