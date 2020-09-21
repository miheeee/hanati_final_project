package kr.ac.kopo.scheduledDepositDate.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;

@Repository
public class ScheduledDepositDateDAOImpl implements ScheduledDepositDateDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override	//회비 입금일 정기 알림 설정 변경 : 설정 => 회비 입금일 안내 예정 내역에 추가
	public void insert(ScheduledDepositDateVO scheduledDepositDateVO) {
		sqlSession.insert("scheduledDepositDate.dao.ScheduledDepositDateDAO.insertNotifyDepositDate", scheduledDepositDateVO);
	}

	@Override	//회비 입금일 정기 알림 설정 변경 : 해지 => 회비 입금일 안내 예정 내역에서 삭제
	public void delete(ScheduledDepositDateVO scheduledDepositDateVO) {
		sqlSession.delete("scheduledDepositDate.dao.ScheduledDepositDateDAO.deleteNotifyDepositDate", scheduledDepositDateVO);
	}

	@Override	//오늘 회비 입금 알림을 받을 계좌번호, id 쌍으로 정보 가져오기
	public List<ScheduledDepositDateVO> selectTodayDepositDate() {
		List<ScheduledDepositDateVO> SDDList = sqlSession.selectList("scheduledDepositDate.dao.ScheduledDepositDateDAO.selectTodayDepositDate");
		return SDDList;
	}

	
}
