package kr.ac.kopo.participant.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;	
	
	@Autowired
	private ParticipantVO participantVO;
	
	@Override	//모임원(모임주 포함) 추가
	public void insertParicipant(ParticipantVO participantVO) {
		
		sqlSession.insert("participant.dao.ParticipantDAO.insertParicipant", participantVO);
	}
	
	@Override	//safeAccountNo로 모임원 정보 조회
	public List<ParticipantVO> selectBySafeAccountNo(GatheringVO gatheringVO) {
		List<ParticipantVO> participantList = sqlSession.selectList("participant.dao.ParticipantDAO.selectBySafeAccountNo", gatheringVO);
		return participantList;
	}
	
	//쓸 지 안 쓸지 잘 모르겠음
	@Override	//입금 내역을 거래 내역에 추가하기 전, 입금 계좌가 모임계좌이며 입금한 사람이 해당 계좌의 모임원인지 여부 판단
	public ParticipantVO selectIfDepositDues(TransactionVO transactionVO) {
		participantVO = sqlSession.selectOne("participant.dao.ParticipantDAO.selectIfDepositDues", transactionVO);
		return participantVO;
	}
	
	@Override	//계좌번호로 모임원 정보 조회 
	public List<ParticipantVO> selectByAccountNo(TransactionVO transactionVO) {
		List<ParticipantVO> participantList = sqlSession.selectList("participant.dao.ParticipantDAO.selectByAccountNo", transactionVO);
		return participantList;
	}

	@Override	//모임 참여자의 알림 설정들 가져오기
	public ParticipantVO selectAllNotifySettings(GatheringVO gatheringVO) {
		participantVO = sqlSession.selectOne("participant.dao.ParticipantDAO.selectAllNotifySettings", gatheringVO);
		return participantVO;
	}

	@Override	//모임통장 및 모임원 변경 알림 설정 변경
	public void ChangeAccountChangeNotify(ParticipantVO participantVO) {
		sqlSession.update("participant.dao.ParticipantDAO.ChangeAccountChangeNotify", participantVO);
	}
	
	@Override	//입출금 알림 설정 변경
	public void transferNotiChange(ParticipantVO participantVO) {

		sqlSession.update("participant.dao.ParticipantDAO.transferNotiChange", participantVO);
		
	}

	@Override	//회비 입금일 정기 알림 설정 변경
	public void depositeDateNotiChange(ParticipantVO participantVO) {
		sqlSession.update("participant.dao.ParticipantDAO.depositeDateNotiChange", participantVO);
		
	}

	@Override	//오늘 회비 입금일인 모임통장의 알림을 허락하는 모임원 정보 알아오기(계좌번호, id)
	public List<ParticipantVO> participantTerminateDate() {
		List<ParticipantVO> participantList = sqlSession.selectList("participant.dao.ParticipantDAO.participantTerminateDate");	
		return participantList;
	}

	@Override	//종료하려는 모임계좌의 모임원 모두 삭제
	public void deleteAllParticipant() {
		sqlSession.delete("participant.dao.ParticipantDAO.deleteAllParticipant");	
	}
	
	@Override	//모임통장 및 모임원 변동 알림을 받으려는 모임원들의 전화번호 얻어오기
	public List<MemberVO> selectAccChangeY(ParticipantVO participantVO) {
		
		List<MemberVO> memberList = sqlSession.selectList("participant.dao.ParticipantDAO.selectAccChangeY", participantVO);
		
		return memberList;
	}
	
	
	@Override	//입출금 알림을 받으려는 모임원들의 전화번호 얻어오기
	public List<MemberVO> selectTransferY(ParticipantVO participantVO) {

		List<MemberVO> memberList = sqlSession.selectList("participant.dao.ParticipantDAO.selectTransferY", participantVO);
		
		return memberList;
	}

	@Override	//멤버 삭제
	public void deleteParticipant(ParticipantVO participantVO) {
		sqlSession.selectList("participant.dao.ParticipantDAO.deleteParticipant", participantVO);	
	}	
	
	
}

