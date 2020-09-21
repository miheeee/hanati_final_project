package kr.ac.kopo.authentication.service;

import kr.ac.kopo.authentication.vo.AuthenticationVO;

public interface AuthenticationService {

	/**
	 * 안심계좌번호와 인증번호가 존재하는지 확인 
	 */
	public AuthenticationVO selectIfExist(AuthenticationVO authenticationVO);

	/**
	 * 인증번호 만료하기(삭제)
	 */
	public void expireAuthenticationNum(AuthenticationVO authenticationVO);
}
