package kr.ac.kopo.participant.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.authentication.dao.AuthenticationDAO;
import kr.ac.kopo.authentication.vo.AuthenticationVO;
import kr.ac.kopo.gathering.dao.GatheringDAO;
import kr.ac.kopo.gathering.service.GatheringService;
import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.dao.ParticipantDAO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.scheduledDepositDate.dao.ScheduledDepositDateDAO;
import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Service
public class ParticipantServiceImpl implements ParticipantService{

	@Autowired
	private ParticipantDAO participantDAO;
	
	@Autowired
	private ParticipantVO participantVO;
	
	@Autowired
	private ScheduledDepositDateDAO scheduledDepositDateDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private GatheringDAO gatheringDAO;
	
	@Autowired
	private AuthenticationDAO authenticationDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private AccountVO accountVO;
	
	@Autowired
	private GatheringVO gatheringVO;
	
	@Autowired
	private MemberVO memberVO;
	
	//safeAccountNo로 모임원 정보 조회
	@Override
	public List<ParticipantVO> selectParticipantsBySafeAccountNo(GatheringVO gatheringVO) {
		List<ParticipantVO> participantList = participantDAO.selectBySafeAccountNo(gatheringVO);
		return participantList;
	}

	@Override	//계좌번호로 모임원 정보 조회 
	public List<ParticipantVO> selectByAccountNo(TransactionVO transactionVO) {
		List<ParticipantVO> participantList = participantDAO.selectByAccountNo(transactionVO);
		return participantList;
	}

	@Override	//모임 참여자의 알림 설정들 가져오기
	public ParticipantVO selectAllNotifySettings(GatheringVO gatheringVO) {
		participantVO = participantDAO.selectAllNotifySettings(gatheringVO);
		return participantVO;
	}

	@Override	//모임통장 및 모임원 변경 알림 설정 변경
	public void ChangeAccountChangeNotify(ParticipantVO participantVO) {
		participantDAO.ChangeAccountChangeNotify(participantVO);
	}
	
	@Override	//입출금 알림 설정 변경
	public void transferNotiChange(ParticipantVO participantVO) {
		participantDAO.transferNotiChange(participantVO);
		
	}

	@Override	//회비 입금일 정기 알림 설정 변경 : 설정
	public void depositeDateNotiChangeY(ScheduledDepositDateVO scheduledDepositDateVO) {
		
		//알림 설정을 'Y'로 업데이트
		participantVO.setAccountNo(scheduledDepositDateVO.getAccountNo());
		participantVO.setId(scheduledDepositDateVO.getId());
		participantVO.setDepositDate("Y");
		participantDAO.depositeDateNotiChange(participantVO);

		//회비 입금일 알림 내역에 추가
		scheduledDepositDateDAO.insert(scheduledDepositDateVO);
		
	}

	@Override
	public void depositeDateNotiChangeN(ScheduledDepositDateVO scheduledDepositDateVO) {
		
		//알림 설정을 'N'으로 업데이트
		participantVO.setAccountNo(scheduledDepositDateVO.getAccountNo());
		participantVO.setId(scheduledDepositDateVO.getId());
		participantVO.setDepositDate("N");
		participantDAO.depositeDateNotiChange(participantVO);
		
		//회비 입금일 알림 내역에서 삭제
		scheduledDepositDateDAO.delete(scheduledDepositDateVO);
	}

	@Override	//모임원 초대 링크에 보낸 인증번호 DB에 저장
	public void saveAuthenticationNum(AuthenticationVO authenticationVO) {
		authenticationDAO.saveAuthenticationNum(authenticationVO);
	}

	@Override	//가입 프로세스(모임원에 추가 및 모임통장 정보 가져오기)
	public GatheringVO joinProcess(ParticipantVO participantVO) {
		
		//이름 알아오기
		memberVO.setId(participantVO.getId());
		memberVO = memberDAO.selectById(memberVO);
		
		//멤버 추가
		participantVO.setType("302");
		participantVO.setName(memberVO.getName());
		participantDAO.insertParicipant(participantVO);
		
		//인증번호 지우기
		
		
		//모임통장 정보
		gatheringVO = gatheringDAO.selectByAccountNo(participantVO.getAccountNo());
		return gatheringVO;
	}

	@Override	//멤버 삭제
	public void deleteParticipant(ParticipantVO participantVO) {
		participantDAO.deleteParticipant(participantVO);
	}
	
	
}
