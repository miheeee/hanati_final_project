package kr.ac.kopo.scheduler;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;

public interface Scheduler {

	/**
	 * 모임통장 종료 예정 내역에 추가
	 */
	public void insertSchedule(AccountVO accountVO);
	
	/**
	 * 특정 계좌번호를 가진 종료 예정 내역 삭제
	 */
	public void deleteScheduleByAccountNo(AccountVO accountVO);
	
	/**
	 * 모임통장 종료 예정 내역에 오늘 포함 이전 날짜가 있는 내역이 있음 삭제  
	 */
	public void deleteSchedule();
	
	/**
	 * 회비 입금일 안내 예정 내역에 추가
	 */
	public void insertNotifyDepositDate(ScheduledDepositDateVO scheduledDepositDateVO);
	
	/**
	 * 회비입금일이 오늘인 계좌의 알림 허용 모임원에게 문자 보내가
	 */
	public void notifyDepositDate();
}
