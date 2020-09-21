package kr.ac.kopo.dues.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.dues.vo.DuesVO;
import kr.ac.kopo.participant.vo.ParticipantVO;

public interface DuesService {

	/**
	 * 회비 관리를 위한  계좌번호로 모임원 목록, 회비 입금 내역 가져오기
	 */
	public Map<String, Object> dueStatus(AccountVO accountVO);
	
	/**
	 * 멤버별 회비입금 조회, 계좌번호와 모임원 이름으로 해당 모임원의 회비 입금 내역 가져오기
	 */
	public Map<Set<String>, List<DuesVO>> duesByMember(ParticipantVO participantVO);
	
	
}
