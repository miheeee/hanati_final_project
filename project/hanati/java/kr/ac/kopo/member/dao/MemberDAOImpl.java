package kr.ac.kopo.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override	//로그인 (아이디, 비밀번호 일치 여부)
	public MemberVO login(MemberVO member) {
		
		MemberVO loginVO = sqlSession.selectOne("member.dao.MemberDAO.login", member);
		return loginVO;
	}

	//아이디로 회원 정보 조회
	@Override
	public MemberVO selectById(MemberVO memberVO) {

		MemberVO member = sqlSession.selectOne("member.dao.MemberDAO.selectById", memberVO);
		return member;
	}

	@Override	//오늘 종료 예정인 모임통장의 모임원 id에 맞는 전화번호 알아오기
	public List<MemberVO> memberTodayTerminateDate() {
		List<MemberVO> memberList = sqlSession.selectList("member.dao.MemberDAO.memberTodayTerminateDate");	
		return memberList;
	}

	@Override	//
	public List<MemberVO> memberTodayDepositDate() {
		List<MemberVO> memberList = sqlSession.selectList("member.dao.MemberDAO.memberTodayDepositDate");	
		return memberList;
	}
	
	
	
}
