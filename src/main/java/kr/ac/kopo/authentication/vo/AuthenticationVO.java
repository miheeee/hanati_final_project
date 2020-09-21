package kr.ac.kopo.authentication.vo;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationVO {

	private String safeAccountNo;
	private String authenticationNum;

	public String getSafeAccountNo() {
		return safeAccountNo;
	}
	public void setSafeAccountNo(String safeAccountNo) {
		this.safeAccountNo = safeAccountNo;
	}
	public String getAuthenticationNum() {
		return authenticationNum;
	}
	public void setAuthenticationNum(String authenticationNum) {
		this.authenticationNum = authenticationNum;
	}
	@Override
	public String toString() {
		return "AuthenticationVO [safeAccountNo=" + safeAccountNo + ", authenticationNum=" + authenticationNum + "]";
	}


}
