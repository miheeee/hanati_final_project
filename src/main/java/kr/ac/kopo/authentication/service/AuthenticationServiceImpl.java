package kr.ac.kopo.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.authentication.dao.AuthenticationDAO;
import kr.ac.kopo.authentication.vo.AuthenticationVO;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private AuthenticationDAO authenticationDAO;
	
	@Override	//안심계좌번호와 인증번호가 존재하는지 확인 
	public AuthenticationVO selectIfExist(AuthenticationVO authenticationVO) {
		AuthenticationVO authentication = authenticationDAO.selectIfExist(authenticationVO);
		return authentication;
	}

	@Override	//인증번호 만료하기(삭제)
	public void expireAuthenticationNum(AuthenticationVO authenticationVO) {
		authenticationDAO.delete(authenticationVO);
	}

}
