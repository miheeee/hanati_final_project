package kr.ac.kopo.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.vo.MemberVO;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private AccountVO account;
	
	@Override	//비밀번호 일치 여부 확인
	public AccountVO checkMatchPassword(AccountVO accountVO) {
		account = accountDAO.checkMatchPassword(accountVO); 
		return account;
	}

	@Override	//보유하는 모든 입출금 계좌 조회
	public List<AccountVO> selectAccountList(MemberVO memberVO) {
		List<AccountVO> accountList = accountDAO.selectAccountList(memberVO);
		return accountList;
	}

	@Override	//안심계좌번호로 통장 소유주 이름 가져오기
	public AccountVO selectBySafeAccountNo(GatheringVO gatheringVO) {
		account = accountDAO.selectBySafeAccountNo(gatheringVO); 
		return account;
	}

	
}
