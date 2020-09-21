package kr.ac.kopo.account.service;

import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.vo.ParticipantVO;

public interface AccountService {

	//public void switchAccountType(String accountNo);
	public List<AccountVO> selectAllAccount(MemberVO loginVO);

	public AccountVO apply(AccountVO accountVO);
	
	public List<AccountVO> manage(MemberVO loginVO);

	public List<AccountVO> selectOwnedGathering(MemberVO loginVO);
	
	public void terminate(AccountVO account);
	
	public void terminateCancel(AccountVO account);
	
	//모임통장 설정(디테일)
	public ParticipantVO AccountSetting(String safeAccountNo, MemberVO loginVO);
	
	//모임통장 및 모임원 변경 알림 설정 변경
	public void accountChange(ParticipantVO participantVO);
	
	//입출금 알림 설정 변경
	public void transferNotiChange(ParticipantVO participantVO);
	
	//회비 입금일 정기 알림 설정 변경
	public void depositeDateNotiChange(ParticipantVO participantVO);
}
