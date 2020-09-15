package kr.ac.kopo.scheduledEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.scheduledEnd.vo.ScheduledEndVO;
import kr.ac.kopo.scheduledEnd.dao.ScheduledEndDAO;

@Service
public class ScheduledEndServiceImpl implements ScheduledEndService{

	@Autowired
	private ScheduledEndDAO scheduledEndDAO;
	
	@Autowired
	private ScheduledEndVO scheduledEndVO;
	
	@Override	//모임통장 종료 내역에 해당 계좌번호의 목록이 있는지 조회
	public ScheduledEndVO selectIfExist(GatheringVO gatheringVO) {
		
		scheduledEndVO = scheduledEndDAO.selectIfExist(gatheringVO);
		return scheduledEndVO;
	}

	@Override	//모임통장 사용 종료 신청
	public void terminate(GatheringVO gatheringVO) {
		scheduledEndDAO.insertScheduledEnd(gatheringVO);
	}
	
	@Override	//모임통장 사용 종료 취소
	public void terminateCancel(GatheringVO gatheringVO) {
		scheduledEndDAO.deleteScheduledEnd(gatheringVO);
	}
	
}
