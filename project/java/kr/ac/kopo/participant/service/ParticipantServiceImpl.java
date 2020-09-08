package kr.ac.kopo.participant.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.SmsSender;
import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.dao.ParticipantDAO;
import kr.ac.kopo.participant.vo.ParticipantVO;

@Service
public class ParticipantServiceImpl implements ParticipantService{

	@Autowired
	ParticipantDAO participantDAO;
	
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired 
	AccountVO accountVO;
	
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	MemberVO memberVO;	
	
	@Autowired
	SmsSender smsSender;
	
	@Override
	public Map<AccountVO, List<ParticipantVO>> selectParticipants(MemberVO loginVO) {
		
		List<ParticipantVO> participantList = participantDAO.selectParticipants(loginVO);
		
		//데이터가 여기까진 가져옴
		
//		Set<String> accountNoSet = new HashSet<String>();
//		for(ParticipantVO participantVO : participantList) {
//			accountNoSet.add(participantVO.getAccountNo());
//		}
		
		List<AccountVO> accountList = accountDAO.selectGathering(loginVO);
		
		Map<AccountVO, List<ParticipantVO>> map = new HashMap<AccountVO, List<ParticipantVO>>();
		for(AccountVO account : accountList) {
			List<ParticipantVO> list = new ArrayList<ParticipantVO>();
			for(ParticipantVO participant : participantList) {
				if(account.getAccountNo().equals(participant.getAccountNo())) {
					list.add(participant);
				}
			}
			map.put(account, list);
		}
		
		return map;
	}

	@Override
	public AccountVO join(AccountVO accountVO) {
			AccountVO account = accountDAO.selectBySafeAccountNo(accountVO);
		return account;
	}

	//초대 수락하기 과정
	@Override
	public void joinProcess(ParticipantVO participantVO) {

		participantDAO.insert(participantVO);
		
		//--  알림  --//
		//계좌정보(계좌 별칭, 안심계좌번호)
		accountVO.setAccountNo(participantVO.getAccountNo());
		AccountVO account = accountDAO.selectByAccountNo(accountVO);
		//가입하려는 회원정보(이름)
		memberVO.setId(participantVO.getId());
		MemberVO member = memberDAO.selectById(memberVO);
		//알림을 받으려는 모임원(전화번호)
		List<MemberVO> memberList = participantDAO.selectAccChangeY(participantVO);
		//문자 내용 설정
		String msg = account.getAlias() + "(" + account.getSafeAccountNo() + ")"
						+ "에 새로운 멤버 " + member.getName() + "님이 들어왔습니다.";
		//문자 보내기				
		for(MemberVO memberVO : memberList) {
			smsSender.send(memberVO.getTel(), msg);
		}
		
	}

	//멤버 끊기
	@Override
	public void dropout(ParticipantVO participantVO) {
		
		participantDAO.delete(participantVO);
		
		//--  알림  --//
		//계좌정보(계좌 별칭, 안심계좌번호)
		accountVO.setAccountNo(participantVO.getAccountNo());
		AccountVO account = accountDAO.selectByAccountNo(accountVO);
		//가입하려는 회원정보(이름)
		memberVO.setId(participantVO.getId());
		MemberVO member = memberDAO.selectById(memberVO);
		//알림을 받으려는 모임원(전화번호)
		List<MemberVO> memberList = participantDAO.selectAccChangeY(participantVO);
		//문자 내용 설정
		String msg = account.getAlias() + "(" + account.getSafeAccountNo() + ")"
						+ "에서 " + member.getName() + "님이 나가셨습니다.";
		//문자 보내기				
		for(MemberVO memberVO : memberList) {
			smsSender.send(memberVO.getTel(), msg);
		}
	}


}
