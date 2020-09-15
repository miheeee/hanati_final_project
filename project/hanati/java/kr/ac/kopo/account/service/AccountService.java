package kr.ac.kopo.account.service;

import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;

public interface AccountService {

	/**
	 * 비밀번호 일치 여부 확인
	 */
	public AccountVO checkMatchPassword(AccountVO accountVO); 
	
	/**
	 * 보유하는 모든 입출금 계좌 조회
	 */
	public List<AccountVO> selectAccountList(MemberVO memberVO);
	
	
}
