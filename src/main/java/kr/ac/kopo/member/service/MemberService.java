package kr.ac.kopo.member.service;

import kr.ac.kopo.member.vo.MemberVO;

public interface MemberService {

	/**
	 * 로그인
	 */
	MemberVO login(MemberVO member);
}
