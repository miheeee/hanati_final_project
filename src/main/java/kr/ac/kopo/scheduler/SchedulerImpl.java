package kr.ac.kopo.scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.SmsSender;
import kr.ac.kopo.gathering.dao.GatheringDAO;
import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.dao.ParticipantDAO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;
import kr.ac.kopo.scheduledEnd.dao.ScheduledEndDAO;
import kr.ac.kopo.scheduledDepositDate.dao.ScheduledDepositDateDAO;

@Service
public class SchedulerImpl implements Scheduler{

	@Autowired
	private GatheringDAO gatheringDAO;
	
	@Autowired
	private ParticipantDAO participantDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private ScheduledEndDAO scheduledEndDAO;
	
	@Autowired
	private ScheduledDepositDateDAO scheduledDepositDateDAO;
	
	@Autowired
	private SmsSender smsSender;

	////모임통장 종료 예정 내역에 오늘 포함 이전 날짜가 있는 내역이 있음 삭제  
//	@Scheduled(cron="0 45 12 * * *")
	@Transactional
	@Override
	public void deleteScheduledEnd() {
		System.out.println("안녕");
	      try{  
	    	//알림 (필요한 정보 가져오기)
				//오늘 종료 예정인 모임통장의 정보 알아오기(별칭, 안심계좌번호)
	    	  	List<GatheringVO> gatheringList = gatheringDAO.accountTodayTerminateDate();	
				//오늘 회비 입금일인 모임통장의 알림을 허락하는 모임원 정보 알아오기(계좌번호, id)
				List<ParticipantVO> participantList = participantDAO.participantTerminateDate();	
				//해당 id에 맞는 전화번호 알아오기
				List<MemberVO> memberList = memberDAO.memberTodayTerminateDate();
	    	  
			//실질적 비즈니스 로직
	    	  	//모임통장 타입을 다시 입출금통장으로 전환하고 안심계좌번호, 별칭 지우기
				gatheringDAO.deleteGathering();	
	    	  	
	    	  	//종료하려는 모임계좌의 모임원 모두 삭제
				participantDAO.deleteAllParticipant();
	  			
	    	  	//오늘 기준 이전 du_date를 가진 모임통장 모두 종료 예정 내역 삭제
				scheduledEndDAO.deleteSchedule();
	    	  	
	    	 //알림 (문자 보내기)
		    	  List<Map<String, String>> totalList = new ArrayList<Map<String,String>>();  
	    		  for(ParticipantVO participant : participantList) {
	    			  Map<String, String> map = new HashMap<String, String>();
	    			  for(GatheringVO gathering : gatheringList) {
		    			  if(gathering.getAccountNo().equals(participant.getAccountNo())) {
		    				  map.put("accountNo", gathering.getAccountNo());
		    				  map.put("alias", gathering.getName());
		    				  map.put("safeAccountNo", gathering.getSafeAccountNo());
		    				  map.put("id", participant.getId());
		    			  }
	    			  }
	    			  for(MemberVO memberVO : memberList) {
	    				 if(memberVO.getId().equals(participant.getId())) {
	    					 map.put("tel", memberVO.getTel());
	    				 }
	    			  }
	    			  totalList.add(map);
	    		  }		
		    	  
		    	  String msg;
		    	  for(Map<String, String> map : totalList) {
		    		 msg = "[HANA MOIM] 모임통장 ";
		    		 msg += map.get("name") + "(" + map.get("safeAccountNo") + ")";
		    		 msg += "이 종료되었습니다.";
		    		 System.out.println(msg);
		    		 //smsSender.send(map.get("tel"), msg);
		    	  }
	      }catch(Exception e){
	            e.printStackTrace();
	        }
		System.out.println("오늘 종료 예정이었던 모임통장 목록이 삭제되었습니다.");			
	}
	
	//회비입금일이 오늘인 계좌의 알림 허용 모임원에게 문자 보내가
	@Scheduled(cron="0 11 18 * * *")
	@Override
	public void notifyScheduledDepositDate() {
		try {
	  	  //오늘 회비 입금일인 모임통장의 정보 알아오기(별칭, 안심계좌번호)
			List<GatheringVO> gatheringList = gatheringDAO.accountTodayDepositDate();	
	  	  
	  	  //오늘 회비 입금 알림을 받을 모임원 id에 맞는 전화번호 알아오기
			List<MemberVO> memberList = memberDAO.memberTodayDepositDate();	
	  	  
	  	  //오늘 회비 입금 알림을 받을 계좌번호, id 쌍으로 정보 가져오기
	  	  List<ScheduledDepositDateVO> SDDList = scheduledDepositDateDAO.selectTodayDepositDate();
	  	  
	  	  //문자 알림 보내기 위한 정보 얻어오기
	  	  List<Map<String, String>> totalList = new ArrayList<Map<String,String>>();  
			  for(ScheduledDepositDateVO sddVO : SDDList) {
				  Map<String, String> map = new HashMap<String, String>();
				  for(GatheringVO gathering : gatheringList) {
	  			  if(gathering.getAccountNo().equals(sddVO.getAccountNo())) {
	  				  map.put("accountNo", gathering.getAccountNo());
	  				  map.put("alias", gathering.getName());
	  				  map.put("afeAccountNo", gathering.getSafeAccountNo());
	  				  map.put("id", sddVO.getId());
	  			  }
				  }
				  for(MemberVO memberVO : memberList) {
					 if(memberVO.getId().equals(sddVO.getId())) {
						 map.put("tel", memberVO.getTel());
					 }
				  }
				  totalList.add(map);
			  }		  
	  	  
			  //문자 알림 보내기
		  	  String msg;
		  	  for(Map<String, String> map : totalList) {
		  		 msg = "[HANA MOIM]오늘은 모임통장 ";
		  		 msg += map.get("alias") + "(" + map.get("safeAccountNo") + ")";
		  		 msg += "의 회비 입금일입니다.";
		  		 System.out.println(msg);
		  		 //smsSender.send(map.get("tel"), msg);
		  	  }
		}catch(Exception e){
            e.printStackTrace();
        }
		System.out.println("입금 알림 문자가 발송되었습니다.");
	}
	
	
}

