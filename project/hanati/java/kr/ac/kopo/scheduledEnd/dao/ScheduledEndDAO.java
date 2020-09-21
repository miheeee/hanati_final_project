package kr.ac.kopo.scheduledEnd.dao;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.scheduledEnd.vo.ScheduledEndVO;

public interface ScheduledEndDAO {

	/**
	 * 모임통장 종료 내역에 해당 계좌번호의 목록이 있는지 조회
	 */
	public ScheduledEndVO selectIfExist(GatheringVO gatheringVO);

	/**
	 * 모임통장 사용 종료 신청
	 */
	public void insertScheduledEnd(GatheringVO gatheringVO);
	
	/**
	 * 모임통장 사용 종료 취소
	 */
	public void deleteScheduledEnd(GatheringVO gatheringVO);
	
	/**
	 * 오늘 기준 이전 du_date를 가진 모임통장 모두 종료 예정 내역 삭제
	 */
	public void deleteSchedule();
}
