package kr.ac.kopo.scheduledEnd.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.scheduledEnd.vo.ScheduledEndVO;

@Repository
public class ScheduledEndDAOImpl implements ScheduledEndDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;	
	
	@Autowired
	private ScheduledEndVO scheduledEndVO;
	
	@Override	//모임통장 종료 내역에 해당 계좌번호의 목록이 있는지 조회
	public ScheduledEndVO selectIfExist(GatheringVO gatheringVO) {
		
		scheduledEndVO = sqlSession.selectOne("scheduledEnd.dao.ScheduledEndDAO.selectIfExist", gatheringVO);
		return scheduledEndVO;
	}

	@Override	//모임통장 사용 종료 신청
	public void insertScheduledEnd(GatheringVO gatheringVO) {
		sqlSession.insert("scheduledEnd.dao.ScheduledEndDAO.insertScheduledEnd", gatheringVO);
	}

	@Override	//모임통장 사용 종료 취소
	public void deleteScheduledEnd(GatheringVO gatheringVO) {
		sqlSession.delete("scheduledEnd.dao.ScheduledEndDAO.deleteScheduledEnd", gatheringVO);
	}

	@Override	//오늘 기준 이전 end_date를 가진 모임통장 모두 종료 예정 내역 삭제 
	public void deleteSchedule() {
		sqlSession.delete("scheduledEnd.dao.ScheduledEndDAO.deleteSchedule");
	}

}
