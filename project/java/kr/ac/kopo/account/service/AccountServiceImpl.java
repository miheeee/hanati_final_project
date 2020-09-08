package kr.ac.kopo.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.dao.ParticipantDAO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.scheduler.Scheduler;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private ParticipantDAO participantDAO;
	
	@Autowired 
	private ParticipantVO participantVO;
	
	@Autowired 
	private Scheduler scheduler;
	
	@Autowired
	private AccountVO accountVO;
	
	@Override
	public List<AccountVO> selectAllAccount(MemberVO loginVO) {
		List<AccountVO> accountList = accountDAO.selectAll(loginVO);
		return accountList;
	}

	@Transactional
	@Override
	public AccountVO apply(AccountVO accountVO) {
		//안심계좌번호 & 통장 type update
		long randomNumber = Math.round(Math.random() * 10000000);
		
		String safeAccountNo = "7979" + String.valueOf(randomNumber);
		
		accountVO.setSafeAccountNo(safeAccountNo);
		accountDAO.apply(accountVO);
		
		//모임원 목록에 추가 (모임주가 추가됨)
		participantVO.setAccountNo(accountVO.getAccountNo());
		participantVO.setId(accountVO.getId());
		participantVO.setType("301");
		participantDAO.insert(participantVO);
		
		return accountVO;
	}
	
	@Override
	public List<AccountVO> manage(MemberVO loginVO) {
		
		List<AccountVO> accountList = accountDAO.selectGathering(loginVO);
		
		return accountList;
	}

	@Override
	public List<AccountVO> selectOwnedGathering(MemberVO loginVO) {
		
		List<AccountVO> accountList = accountDAO.selectOwnedGathering(loginVO);
		
		return accountList;
	}

	@Transactional
	@Override
	public void terminate(AccountVO account) {
		accountDAO.terminate(account);							//account테이블에서 status = '202'로 update
		scheduler.insertSchedule(account);						//scheduled_termination(모임통장 종료 예정 내역)에 추가
		
		//accountDAO.realTerminate(account);						//account테이블에서 type = '102', status = '201', safe_account_no = '',alias = ''로 update ?? 3일 후여야 되는데....
																//=>이러지 말고 ScheduledTermination목록에 없다면 수행해야함
		//participantVO.setAccountNo(account.getAccountNo());		
		//participantDAO.delete(participantVO);					//participant테이블에서 특정 계좌번호에 해당하는 모든 모임원 삭제
	}

	@Override
	public void terminateCancel(AccountVO account) {
		accountDAO.terminateCancel(account);
		scheduler.deleteScheduleByAccountNo(account);
	}

	//모임통장 설정(디테일)
	@Override
	public ParticipantVO AccountSetting(String safeAccountNo, MemberVO loginVO) {
			
			
			//1)원래 모임통장 번호 알아오기
			accountVO.setSafeAccountNo(safeAccountNo);
			AccountVO account = accountDAO.selectBySafeAccountNo(accountVO);
			
			//2)알림 설정 모임 여부 알아오기
			participantVO.setAccountNo(account.getAccountNo());
			participantVO.setId(loginVO.getId());
			ParticipantVO participant = participantDAO.selecNotiByAccNo(participantVO);
			
		return participant;
	}

	//모임통장 및 모임원 변동 알림 변경
	@Override
	public void accountChange(ParticipantVO participantVO) {
		participantDAO.accountChange(participantVO);
	}

	@Override
	public void transferNotiChange(ParticipantVO participantVO) {
		participantDAO.transferNotiChange(participantVO);
		
	}

	@Override
	public void depositeDateNotiChange(ParticipantVO participantVO) {
		participantDAO.depositeDateNotiChange(participantVO);
	}
	
	
//	@Override
//	public void switchAccountType(String accountNo) {
//		accountDAO.switchType(accountNo);
//	}
	
	

}
