package kr.ac.kopo.scheduledEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.scheduledEnd.vo.ScheduledEndVO;

public interface ScheduledEndService {

	/**
	 * 모임통장 종료 내역에 해당 계좌번호의 목록이 있는지 조회
	 */
	public ScheduledEndVO selectIfExist(GatheringVO gatheringVO);	
	
	/**
	 * 모임통장 사용 종료 신청
	 */
	public void terminate(GatheringVO gatheringVO);
	
	/**
	 * 모임통장 사용 종료 취소
	 */
	public void terminateCancel(GatheringVO gatheringVO);
	
}
