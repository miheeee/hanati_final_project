package kr.ac.kopo.notify.service;

import java.util.List;

import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.SmsSender;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.participant.dao.ParticipantDAO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.transaction.dao.TransactionDAO;
import kr.ac.kopo.transaction.dao.TransactionDAOImpl;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Service
public class NotifyServiceImpl implements NotifyService{

	@Autowired
	private SmsSender smsSender;
	
	@Autowired
	private ParticipantDAO participantDAO;
	
	@Autowired
	private ParticipantVO participantVO;
	
	@Autowired
	private TransactionDAO transactionDAO;
	
	@Autowired
	private TransactionVO transactionVO;
	
	/**
	 * 모임원 밑 모임통장 변동 알림
	 */
	
	//모임통장 종료 예정
	@Override
	public void scheduledTerminate(GatheringVO gatheringVO) {
		
		//알림을 받으려는 모임원(전화번호) 조회
		participantVO.setAccountNo(gatheringVO.getAccountNo());
		List<MemberVO> memberList = participantDAO.selectAccChangeY(participantVO);
		
		//문자 내용 설정
		String msg = "[HANA MOIM] 모임통장 " + gatheringVO.getName() + "(" + gatheringVO.getSafeAccountNo() + ")"
						+ "이 3일 뒤 종료 예정입니다.";
		
		//문자 보내기				
		for(MemberVO memberVO : memberList) {
			smsSender.send(memberVO.getTel(), msg);
		}
	}
	
	/**
	 * 입출금 알림
	 */
	
	//입출금 알림
	@Override
	public void transferNoti(int transactionNo) {
	
		//거래 내역 조회
		transactionVO = transactionDAO.selectByTranNo(transactionNo);
		System.out.println(transactionVO);
		
		//알림을 받으려는 모임원(전화번호) 조회
		participantVO.setAccountNo(transactionVO.getAccountNo());
		List<MemberVO> memberList = participantDAO.selectAccChangeY(participantVO);
		
		//문자 내용 설정
		String msg = "[HANA MOIM] " + transactionVO.getTime() + " " + transactionVO.getAccountNo() + " ";
		if(transactionVO.getClassification().equals("402")) {
			msg += "출금 ";
		}else if(transactionVO.getClassification().equals("401")){
			msg += "입금 ";
		}
		msg += transactionVO.getAmount() + "원 ";
		
		if(transactionVO.getClassification().equals("402")) {
				msg += transactionVO.getCounterpart();
		}else{
			if("".equals(transactionVO.getIndication())) {
				msg += transactionVO.getIndication();
			}
		}
		
		msg += " 잔액 " + transactionVO.getBalance() + "원";
		
		//문자 보내기				
		for(MemberVO memberVO : memberList) {
			System.out.println(memberVO.getTel());
			System.out.println(msg);
			//smsSender.send(memberVO.getTel(), msg);
		}
		
	}
		
		
		
		
}
