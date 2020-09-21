package kr.ac.kopo.member.dao;

import kr.ac.kopo.member.vo.MemberVO;

public interface MemberDAO {

	MemberVO login(MemberVO member);
	
	//아이디로 회원 정보 조회
	public MemberVO selectById(MemberVO memberVO);
}
