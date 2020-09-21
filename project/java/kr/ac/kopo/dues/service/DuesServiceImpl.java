package kr.ac.kopo.dues.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.dues.dao.DuesDAO;
import kr.ac.kopo.dues.vo.DuesVO;
import kr.ac.kopo.participant.dao.ParticipantDAO;
import kr.ac.kopo.participant.service.ParticipantService;
import kr.ac.kopo.participant.vo.ParticipantVO;

@Service
public class DuesServiceImpl implements DuesService{

	@Autowired
	private DuesDAO duesDAO;
	
	@Autowired
	private ParticipantDAO participantDAO;
	
	@Override		//회비 관리를 위한  계좌번호로 모임원 목록, 회비 입금 내역 가져오기
	public Map<String, Object> dueStatus(AccountVO accountVO) {

//		//
//		accountDAO.selectGathering(loginVO);
		//모임통장의 모임원 목록 가져오기
		List<ParticipantVO> participantList = participantDAO.selecByAccountNo(accountVO);
		
		//모임통장의 회비 입금 내역 가져오기
		List<DuesVO> duesList = duesDAO.selectByAccountNo(accountVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("participantList", participantList);
		map.put("duesList", duesList);
		
		return map;
	}


	//멤버별 회비입금 조회, 계좌번호와 모임원 이름으로 해당 모임원의 회비 입금 내역 가져오기
	@Override
	public Map<Set<String>, List<DuesVO>> duesByMember(ParticipantVO participantVO){
		List<DuesVO> duesList = duesDAO.selectDuesByMember(participantVO);
//		Map<String, List<DuesVO>> map = new HashMap<String, List<DuesVO>>();
//		List<DuesVO> list = new ArrayList<DuesVO>();
//		for(int i = 0; i <= duesList.size(); i++) {
//			String month = (duesList.get(i).getTime()).substring(0, 8);
//			if(!map.containsKey(month)) {	
//				list = new ArrayList<DuesVO>();
//			}
//			list.add(duesList.get(i));
//			map.put(month, list);
//		}
		Set<String> monthSet = new HashSet<String>();
		for(DuesVO dues : duesList) {
			monthSet.add(dues.getTime().substring(0,7));
		}
		Map<Set<String>, List<DuesVO>> map = new HashMap<Set<String>, List<DuesVO>>();
		map.put(monthSet, duesList);
	return map;
	}
	
}
