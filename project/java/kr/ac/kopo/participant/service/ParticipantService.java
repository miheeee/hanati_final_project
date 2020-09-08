package kr.ac.kopo.participant.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.vo.ParticipantVO;

public interface ParticipantService {

	public Map<AccountVO, List<ParticipantVO>> selectParticipants(MemberVO loginVO); 

	//초대 수락폼(가입)
	public AccountVO join(AccountVO accountVO);
	
	//초대 수락프로세스
	public void joinProcess(ParticipantVO participantVO);
	
	//멤버 끊기
	public void dropout(ParticipantVO participantVO);
	
}
