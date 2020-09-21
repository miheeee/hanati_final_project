package kr.ac.kopo.scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.SmsSender;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.scheduledDepositDate.vo.ScheduledDepositDateVO;

@Repository
public class SchedulerImpl implements Scheduler{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	SmsSender smsSender;
	
	//모임통장 종료 예정 내역에 추가
	@Override
	public void insertSchedule(AccountVO accountVO) {
		
		sqlSession.insert("scheduler.Scheduler.insertSchedule", accountVO);
	}

	//특정 계좌번호를 가진 종료 예정 내역 삭제
	@Override
	public void deleteScheduleByAccountNo(AccountVO accountVO) {
		sqlSession.insert("scheduler.Scheduler.deleteScheduleByAccountNo", accountVO);
		
	}

	//회비 입금일 안내 예정 내역에 추가
	@Override
	public void insertNotifyDepositDate(ScheduledDepositDateVO scheduledDepositDateVO) {
		sqlSession.insert("scheduler.Scheduler.insertNotifyDepositDate", scheduledDepositDateVO);
	}

	//모임통장 종료 예정 내역에 오늘 포함 이전 날짜가 있는 내역이 있음 삭제  
	//@Scheduled(cron="0 49 14 * * *")
	@Transactional
	@Override
	public void deleteSchedule() {
	      try{  
	    	//알림 (필요한 정보 가져오기)
				//오늘 종료 예정인 모임통장의 정보 알아오기(별칭, 안심계좌번호)
				List<AccountVO> accountList = sqlSession.selectList("scheduler.Scheduler.accountTodayTerminateDate");	
				//오늘 회비 입금일인 모임통장의 알림을 허락하는 모임원 정보 알아오기(계좌번호, id)
				List<ParticipantVO> participantList = sqlSession.selectList("scheduler.Scheduler.participantTerminateDate");	
				//해당 id에 맞는 전화번호 알아오기
				List<MemberVO> memberList = sqlSession.selectList("scheduler.Scheduler.memberTodayTerminateDate");	
	    	  
			//실질적 비즈니스 로직
	    	  	//모임통장 타입을 다시 입출금통장으로 전환하고 안심계좌번호, 별칭 지우기
	    	  	sqlSession.update("scheduler.Scheduler.resetAccountType");
	    	  	
	    	  	//종료하려는 모임계좌의 모임원 모두 삭제
	    	  	sqlSession.delete("scheduler.Scheduler.deleteAllParticipant");
	  			
	    	  	//오늘 기준 이전 du_date를 가진 모임통장 모두 종료 예정 내역 삭제
	    	  	sqlSession.delete("scheduler.Scheduler.deleteSchedule"); 	
	    	  	
	    	 //알림 (문자 보내기)
		    	  List<Map<String, String>> totalList = new ArrayList<Map<String,String>>();  
	    		  for(ParticipantVO participant : participantList) {
	    			  Map<String, String> map = new HashMap<String, String>();
	    			  for(AccountVO account : accountList) {
		    			  if(account.getAccountNo().equals(participant.getAccountNo())) {
		    				  map.put("accountNo", account.getAccountNo());
		    				  map.put("alias", account.getAlias());
		    				  map.put("safeAccountNo", account.getSafeAccountNo());
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
		    		 msg += map.get("alias") + "(" + map.get("safeAccountNo") + ")";
		    		 msg += "이 종료되었습니다.";
		    		 smsSender.send(map.get("tel"), msg);
		    	  }
	      }catch(Exception e){
	            e.printStackTrace();
	        }
		System.out.println("삭제되었습니다.");
	}
	
	//회비입금일이 오늘인 계좌의 알림 허용 모임원에게 문자 보내가
	//@Scheduled(cron="0 16 15 * * *")
	@Transactional
	@Override
	public void notifyDepositDate() {
	      try{  
	    	  //오늘 회비 입금일인 모임통장의 정보 알아오기(별칭, 안심계좌번호)
	    	  List<AccountVO> accountList = sqlSession.selectList("scheduler.Scheduler.accountTodayDepositDate");
	    	  
	    	  //오늘 회비 입금일인 모임통장의 알림을 허락하는 모임원 정보 알아오기(계좌번호, id)
	    	  List<ParticipantVO> participantList = sqlSession.selectList("scheduler.Scheduler.participantTodayDepositDate");	
	    	  
	    	  //해당 id에 맞는 전화번호 알아오기
	    	  List<MemberVO> memberList = sqlSession.selectList("scheduler.Scheduler.memberTodayDepositDate");	
	    	  
	    	  //문자 알림 보내기 위한 정보 얻어오기
	    	  List<Map<String, String>> totalList = new ArrayList<Map<String,String>>();  
    		  for(ParticipantVO participant : participantList) {
    			  Map<String, String> map = new HashMap<String, String>();
    			  for(AccountVO account : accountList) {
	    			  if(account.getAccountNo().equals(participant.getAccountNo())) {
	    				  map.put("accountNo", account.getAccountNo());
	    				  map.put("alias", account.getAlias());
	    				  map.put("safeAccountNo", account.getSafeAccountNo());
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
	    	  
    		  //문자 알림 보내기
	    	  String msg;
	    	  for(Map<String, String> map : totalList) {
	    		 msg = "[HANA MOIM]오늘은 모임통장 ";
	    		 msg += map.get("alias") + "(" + map.get("safeAccountNo") + ")";
	    		 msg += "의 회비 입금일입니다.";
	    		 smsSender.send(map.get("tel"), msg);
	    	  }
	      }catch(Exception e){
	            e.printStackTrace();
	        }
		System.out.println("삭제되었습니다.");
	}
//
//
//	public static void main(String[] args) {
//		Scheduler scheduler = new SchedulerImpl();
//		scheduler.notifyDepositDate();
//	
//	}
	
	
}
