package kr.ac.kopo.scheduler;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;

public interface Scheduler {

	/**
	 * 모임통장 종료 예정 내역에 오늘 포함 이전 날짜가 있는 내역이 있음 삭제  
	 */
	public void deleteScheduledEnd();

	/**
	 * 회비입금일이 오늘인 계좌의 알림 허용 모임원에게 문자 보내가
	 */
	public void notifyScheduledDepositDate();
	
}
