package kr.ac.kopo.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
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
	
}
