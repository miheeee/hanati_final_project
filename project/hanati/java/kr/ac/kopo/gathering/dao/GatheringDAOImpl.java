package kr.ac.kopo.gathering.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.vo.MemberVO;

@Repository
public class GatheringDAOImpl implements GatheringDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private GatheringVO gatheringVO;
	
	// 모임원 추가(gatheringVO 데이터 삽입)
	@Override	
	public void insertGathering(GatheringVO gatheringVO) {
	
		sqlSession.insert("gathering.dao.GatheringDAO.insertGathering", gatheringVO);
	}

	//모임통장 정보 조회
	@Override
	public GatheringVO selectByAccountNo(String accountNo) {
		GatheringVO gatheringVO = sqlSession.selectOne("gathering.dao.GatheringDAO.selectByAccountNo", accountNo);
		return gatheringVO;
	}

	//아이디로 참여하고 있는 모든 모임통장 정보 조회
	@Override
	public List<GatheringVO> selectParticipatingGatheringList(MemberVO memberVO) {
		List<GatheringVO> gatheringList = sqlSession.selectList("gathering.dao.GatheringDAO.selectParticipatingGatheringList", memberVO);
		return gatheringList;
	}

	//안심 계좌번호로 모임통장 정보 조회 
	@Override
	public GatheringVO selectBySafeAccountNo(GatheringVO gatheringVO) {
		gatheringVO = sqlSession.selectOne("gathering.dao.GatheringDAO.selectBySafeAccountNo", gatheringVO);
		return gatheringVO;
	}
	
	//모임주로 있는 모임통장 정보만 조회
	@Override
	public List<GatheringVO> selectOwnedGatheringById(MemberVO memberVO) {
		List<GatheringVO> gatheringList = sqlSession.selectList("gathering.dao.GatheringDAO.selectOwnedGatheringById", memberVO);
		return gatheringList;
	}

	@Override	//오늘이 종료일인 모임통장의 정보 알아오기(별칭, 안심계좌번호)
	public List<GatheringVO> accountTodayTerminateDate() {
		
		List<GatheringVO> gatheringList = sqlSession.selectList("gathering.dao.GatheringDAO.accountTodayTerminateDate");
		
		return gatheringList;
	}

	@Override	//오늘이 종료일인 모임통장을 테이블에서 삭제 
	public void deleteGathering() {
		sqlSession.delete("gathering.dao.GatheringDAO.deleteGathering");
	}

	@Override	//오늘 회비 입금일인 모임통장의 정보 알아오기(별칭, 안심계좌번호)
	public List<GatheringVO> accountTodayDepositDate() {
		
		List<GatheringVO> gatheringList = sqlSession.selectList("gathering.dao.GatheringDAO.accountTodayDepositDate");
		
		return gatheringList;
	}

	
	
}
