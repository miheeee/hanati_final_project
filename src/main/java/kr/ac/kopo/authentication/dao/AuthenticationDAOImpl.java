package kr.ac.kopo.authentication.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.authentication.vo.AuthenticationVO;

@Repository
public class AuthenticationDAOImpl implements AuthenticationDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override	//모임원 초대 링크에 보낸 인증번호 DB에 저장
	public void saveAuthenticationNum(AuthenticationVO authenticationVO) {
		sqlSession.insert("authentication.dao.AuthenticationDAO.insert", authenticationVO);
	}

	@Override	//안심계좌번호와 인증번호가 존재하는지 확인 
	public AuthenticationVO selectIfExist(AuthenticationVO authenticationVO) {
		AuthenticationVO authentication = sqlSession.selectOne("authentication.dao.AuthenticationDAO.selectIfExist", authenticationVO);
		return authentication;
	}

	@Override	//인증번호 만료하기(삭제)
	public void delete(AuthenticationVO authenticationVO) {
		sqlSession.delete("authentication.dao.AuthenticationDAO.delete", authenticationVO);
	}
	
	
	
}
