package kr.ac.kopo.account.service;

import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.authentication.vo.AuthenticationVO;
import kr.ac.kopo.gathering.vo.GatheringVO;
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
	
	/**
	 * 안심계좌번호로 통장 소유주 이름 가져오기
	 */
	public AccountVO selectBySafeAccountNo(GatheringVO gatheringVO);
}
