package kr.ac.kopo.dues.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.dues.vo.DuesVO;
import kr.ac.kopo.participant.vo.ParticipantVO;

@Repository
public class DuesDAOImpl implements DuesDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	 //계좌번호로 모임통장의 회비 입금 내역 가져오기
	@Override
	public List<DuesVO> selectByAccountNo(AccountVO accountVO){
		
		List<DuesVO> duesList = sqlSession.selectList("dues.dao.DuesDAO.selectByAccountNo", accountVO);
		return duesList;
	}

	//계좌번호와 모임원 이름으로 해당 모임원의 회비 입금 내역 가져오기
	@Override
	public List<DuesVO> selectDuesByMember(ParticipantVO participantVO) {
		List<DuesVO> duesList = sqlSession.selectList("dues.dao.DuesDAO.selectDuesByMember", participantVO);
		return duesList;
	}
	
	
}
