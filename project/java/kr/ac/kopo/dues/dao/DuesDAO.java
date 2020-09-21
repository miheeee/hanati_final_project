package kr.ac.kopo.dues.dao;

import java.util.List;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.dues.vo.DuesVO;
import kr.ac.kopo.participant.vo.ParticipantVO;

public interface DuesDAO {

	/**
	 * 계좌번호로 모임통장의 회비 입금 내역 가져오기
	 */
	public List<DuesVO> selectByAccountNo(AccountVO accountVO);

	/**
	 * 계좌번호와 모임원 이름으로 해당 모임원의 회비 입금 내역 가져오기
	 */
	public List<DuesVO> selectDuesByMember(ParticipantVO participantVO);
}
