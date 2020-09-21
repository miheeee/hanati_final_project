package kr.ac.kopo.notify.service;

import kr.ac.kopo.account.vo.AccountVO;

public interface NotifyService {

	/*모임통장 및 모임원 변경 문자 알림 서비스*/
	
	//모임통장 종료 예정
	public void scheduledTerminate(AccountVO accountVO);
	
	/*입출금 알림*/
	//입출금 알림
	public void transferNoti(int transactionNo); 
}
