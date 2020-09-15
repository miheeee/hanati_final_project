package kr.ac.kopo.gathering.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.gathering.dao.GatheringDAO;
import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.dao.ParticipantDAO;
import kr.ac.kopo.participant.vo.ParticipantVO;

@Service
public class GatheringServiceImpl implements GatheringService{

	@Autowired
	private GatheringDAO gatheringDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private ParticipantDAO participantDAO;
	
	@Autowired
	private GatheringVO gatheringVO;
	
	@Autowired
	private AccountVO accountVO;
	
	@Autowired
	private MemberVO memberVO;
	
	@Autowired
	private ParticipantVO participantVO;
	
	//모임통장 신청폼(모임통장 제외한 일반 입출금 통장만 조회)
	@Override	
	public List<AccountVO> selectAccountExceptGathering(MemberVO loginVO){
		
		List<AccountVO> accountList = accountDAO.selectExceptGathering(loginVO);
		return accountList;
	}

	
	//모임통장 신청프로세스(모임통장 테이블인 gathering에 데이터 삽입  & 참여자 목록에 모임주 추가)
	@Transactional
	@Override	
	public GatheringVO insertGathering(GatheringVO gatheringVO) {
		
		// 1)
		//안심계좌번호 생성
		Random r = new Random();
		int randomNumber = r.nextInt(9000000) + 1000000;		//long randomNumber = Math.round(Math.random() * 10000000) <= 이 방법은 소수점 첫째자리가 0일 때 7자리가 아닌 6자리가 나옴
		String safeAccountNo = "7979" + String.valueOf(randomNumber);
		gatheringVO.setSafeAccountNo(safeAccountNo);	
		
		//계좌번호로 모임통장으로 전환할 입출금 통장의 잔액 알아와서 세팅
		accountVO.setAccountNo(gatheringVO.getAccountNo());
		accountVO = accountDAO.selectByAccountNo(accountVO);	//accountVO객체 재활용
		gatheringVO.setBalance(accountVO.getBalance());	
		
		//모임통장 테이블에 데이터 삽입
		gatheringDAO.insertGathering(gatheringVO);
		
		// 2)
		//아이디로 이름 조회
		memberVO.setId(gatheringVO.getId());
		memberVO = memberDAO.selectById(memberVO);
		
		//모임원 목록에 모임주 추가 
		participantVO.setAccountNo(gatheringVO.getAccountNo());
		participantVO.setId(gatheringVO.getId());
		participantVO.setType("301");
		participantVO.setName(memberVO.getName());
		participantDAO.insertParicipant(participantVO);
		
		//신청 완료 화면에 정보 보여주기 위해 insert해주려는 데이터 그대로 되돌려주기
		return gatheringVO;
	}


	//아이디로 참여하고 있는 모든 모임통장 정보 조회
	@Override
	public List<GatheringVO> selectParticipatingGatheringList(MemberVO memberVO) {
		
		List<GatheringVO> gatheringList = gatheringDAO.selectParticipatingGatheringList(memberVO);
		return gatheringList;
	}

	//안심 계좌번호로 모임통장 정보 조회 
	@Override
	public GatheringVO selectGatheringBySafeAccountNo(GatheringVO gatheringVO) {
		
		gatheringVO = gatheringDAO.selectBySafeAccountNo(gatheringVO);
		return gatheringVO;
	}
	
	//모임주로 있는 모임통장 정보만 조회
	@Override
	public List<GatheringVO> selectOwnedGatheringById(MemberVO memberVO) {
		
		List<GatheringVO> gatheringList = gatheringDAO.selectOwnedGatheringById(memberVO);
		return gatheringList;
	}
	

	
	
	
	
	
	
	
}
