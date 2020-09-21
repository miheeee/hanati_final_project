package kr.ac.kopo.participant.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.vo.ParticipantVO;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//모임원 추가(모임주, 모임원 둘 다 해당)
	@Override
	public void insert(ParticipantVO participantVO) {
		
		sqlSession.insert("participant.dao.ParticipantDAO.insert", participantVO);
	
	}

	//멤버 끊기
	@Override
	public void delete(ParticipantVO participantVO) {
		
		sqlSession.delete("participant.dao.ParticipantDAO.delete", participantVO);
		
	}
	
	@Override
	public List<ParticipantVO> selectParticipants(MemberVO loginVO) {
		List<ParticipantVO> participantList = sqlSession.selectList("participant.dao.ParticipantDAO.selectParticipants", loginVO);
		return participantList;
	}

	@Override
	public ParticipantVO selecNotiByAccNo(ParticipantVO participantVO) {
		ParticipantVO participant = sqlSession.selectOne("participant.dao.ParticipantDAO.selecNotiByAccNo", participantVO);
		return participant;
	}

	@Override
	public void accountChange(ParticipantVO participantVO) {
		
		sqlSession.update("participant.dao.ParticipantDAO.accountChange", participantVO);
	}

	@Override
	public void transferNotiChange(ParticipantVO participantVO) {

		sqlSession.update("participant.dao.ParticipantDAO.transferNotiChange", participantVO);
		
	}

	@Override
	public void depositeDateNotiChange(ParticipantVO participantVO) {
		
		sqlSession.update("participant.dao.ParticipantDAO.depositeDateNotiChange", participantVO);
		
	}

	//모임통장 및 모임원 변동 알림을 받으려는 모임원들의 전화번호 얻어오기
	@Override
	public List<MemberVO> selectAccChangeY(ParticipantVO participantVO) {
		
		List<MemberVO> memberList = sqlSession.selectList("participant.dao.ParticipantDAO.selectAccChangeY", participantVO);
		
		return memberList;
	}

	//입출금 알림을 받으려는 모임원들의 전화번호 얻어오기
	@Override
	public List<MemberVO> selectTransferY(ParticipantVO participantVO) {

		List<MemberVO> memberList = sqlSession.selectList("participant.dao.ParticipantDAO.selectTransferY", participantVO);
		
		return memberList;
	}
	
	//계좌번호로 모임통장에 속해있는 모임원 조회
	@Override
	public List<ParticipantVO> selecByAccountNo(AccountVO accountVO) {
		
		List<ParticipantVO> participantList = sqlSession.selectList("participant.dao.ParticipantDAO.selecByAccountNo", accountVO);
		return participantList;
	}



	
	
	
}
