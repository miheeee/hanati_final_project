package kr.ac.kopo.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.gathering.dao.GatheringDAO;
import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.notify.service.NotifyService;
import kr.ac.kopo.participant.dao.ParticipantDAO;
import kr.ac.kopo.participant.vo.ParticipantVO;
import kr.ac.kopo.transaction.dao.TransactionDAO;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionDAO transactionDAO;
	
	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private ParticipantDAO participantDAO;
	
	@Autowired
	private GatheringDAO gatheringDAO;
	
	@Autowired
	private AccountVO fromAccount;
	
	@Autowired
	private AccountVO toAccount;

	@Autowired
	private TransactionVO toTransaction;
	
	@Autowired
	private ParticipantVO participantVO;
	
	@Autowired
	private NotifyService notifyService;
	
	@Autowired
	private GatheringVO gatheringVO;
	
	//이체
	@Transactional
	@Override
	public void transfer(TransactionVO fromTransaction) {

		//출금 전 하나은행으로의 이체에 대해선 이체를 받는 계좌번호가 실제 테이블에 존재하지 않으면 어떠한 작업도 수행하지 않고 리턴해주기
		if(fromTransaction.getCounterpartBank().equals("081")) {
			//입력받은 계좌번호가 안심계좌번호 형태일 때
			if(fromTransaction.getCounterpartAccountNo().startsWith("7979")) {
				gatheringVO.setSafeAccountNo(fromTransaction.getCounterpartAccountNo());
				//그에 맞는 실제 계좌번호를 가진 입출금 계좌가 존재하는지 확인
				GatheringVO gathering = gatheringDAO.selectBySafeAccountNo(gatheringVO);
				//존재한다면
				if(gathering != null) {
					//실제 계좌번호로 이체 정보 다시 세팅
					fromTransaction.setCounterpartAccountNo(gathering.getAccountNo());
				//존재하지 않는다면
				}else {
					//바로 리턴
					return;
				}
			//입력받은 계좌번호가 안심계좌번호 형태가 아닐 때
			}else {
				//실제 계좌번호라고 가정하고 입출금 계좌가 존재하는지 확인
				AccountVO acc = new AccountVO();
				acc.setAccountNo(fromTransaction.getCounterpartAccountNo());
				acc = accountDAO.selectByAccountNo(acc);
				//존재하지 않는다면
				if(acc == null) {
					//바로 리턴
					return;
				}
			}
		}	
		
		//출금은 공통
		accountDAO.withdraw(fromTransaction);
		gatheringDAO.withdraw(fromTransaction);
		
		//거래내역 삽입을 위해 출금 계좌 정보 알아오기(소유주이름, 잔액)
		fromAccount.setAccountNo(fromTransaction.getAccountNo());
		fromAccount = accountDAO.selectByAccountNo(fromAccount);
		
		if(!"".equals(fromTransaction.getIndication())) {
			toTransaction.setIndication(fromTransaction.getIndication());
		}
		
		fromTransaction.setBalance(fromAccount.getBalance());
		fromTransaction.setClassification("402");
		
		// i) 타행 이체면 출금만 수행
		if(!fromTransaction.getCounterpartBank().equals("081")) {
	
			//출금 거래 내역 삽입
			fromTransaction.setCounterpart("타행 이체");
			int transactionNo = transactionDAO.selectTransactionNo();		//다음 거래번호 알아오기
			System.out.println(transactionNo);
			fromTransaction.setTransactionNo(transactionNo);
			transactionDAO.insertDetails(fromTransaction);
			//출금 알림
			notifyService.transferNoti(transactionNo);
		}

		// ii) 하나은행으로 이체면 출금에 더해 입금까지 모두 수행
		if(fromTransaction.getCounterpartBank().equals("081")) {
			
			//입금 수행 전 이체를 받는 상대방의 계좌번호가 안심계좌번호라면 실제 계좌번호 알아오기			
			if(fromTransaction.getCounterpartAccountNo().startsWith("7979")) {
				gatheringVO.setSafeAccountNo(fromTransaction.getCounterpartAccountNo());
				GatheringVO gathering = gatheringDAO.selectBySafeAccountNo(gatheringVO);
				if(gathering != null) {
					fromTransaction.setCounterpartAccountNo(gathering.getAccountNo());
				}
			}
			
			//입금 수행
			toTransaction.setAccountNo(fromTransaction.getCounterpartAccountNo());
			toTransaction.setAmount(fromTransaction.getAmount());
			accountDAO.deposit(toTransaction);
			gatheringDAO.deposit(toTransaction);
			
			//입금 거래내역 삽입을 위해 입금 계좌 정보 알아오기(소유주이름, 잔액)
			toAccount.setAccountNo(toTransaction.getAccountNo());
			toAccount = accountDAO.selectByAccountNo(toAccount);				
		
			//출금 거래 내역 삽입
			fromTransaction.setCounterpart(toAccount.getHolder());		//?없는 계좌번호면 어떻게 할까
			int transactionNo = transactionDAO.selectTransactionNo();
			fromTransaction.setTransactionNo(transactionNo);
			
			transactionDAO.insertDetails(fromTransaction);
			//출금 알림
			notifyService.transferNoti(transactionNo);
		
			//입금 거래 내역 삽입
			toTransaction.setBalance(toAccount.getBalance());
			toTransaction.setClassification("401");
			toTransaction.setCounterpart(fromAccount.getHolder());
			toTransaction.setCounterpartAccountNo(fromTransaction.getAccountNo());
			toTransaction.setCounterpartBank("081");
				
			int transactionNo2 = transactionDAO.selectTransactionNo();		//다음 거래번호 알아오기
			toTransaction.setTransactionNo(transactionNo2);
			
//			participantVO = participantDAO.selectIfDepositDues(toTransaction);	
			//입금 내역을 거래 내역에 추가하기 전, 
			//입금 계좌가 모임계좌이며 입금한 사람이 해당 계좌의 모임원인지 여부 판단하기 위해 
			//계좌번호에 맞는 모임원 목록을 가져온다. DB에서 해결하려 했으나
			//indication에 name이 포함되어있는지 여부는 판단할 수 없었음
			List<ParticipantVO> participantList = participantDAO.selectByAccountNo(toTransaction);
			for(ParticipantVO participantVO:participantList) {
				if( toTransaction.getIndication() != null
						&& toTransaction.getIndication().contains(participantVO.getName())) {
					toTransaction.setPaidMember(participantVO.getName());
				}else if( toTransaction.getIndication() == null && 
						toTransaction.getCounterpart().equals(participantVO.getName())) {
					toTransaction.setPaidMember(participantVO.getName());
				}
			}
//			List<ParticipantVO> participantList = participantDAO.selectByAccountNo(toTransaction);
//			for(ParticipantVO participantVO:participantList) {
//				if(toAccount.getHolder().equals(participantVO.getName())
//						|| toTransaction.getIndication().contains(toAccount.getHolder())) {
//					toTransaction.setPaidMember(toAccount.getHolder());
//				}
//			}
			
			transactionDAO.insertDetails(toTransaction);
			//입금 알림
			//notifyService.transferNoti(transactionNo2);
		}
	}
	
	@Override	//안심계좌번호로 거래 내역 조회
	public List<TransactionVO> selectTransBySafeAccountNo(GatheringVO gatheringVO) {
		List<TransactionVO> transactionList = transactionDAO.selectBySafeAccountNo(gatheringVO);
		return transactionList;
	}

	@Override	//안심계좌번호로 회비 입금 내역 조회
	public List<TransactionVO> selectDepositedDuesList(GatheringVO gatheringVO) {
		List<TransactionVO> transactionList = transactionDAO.selectDepositedDuesList(gatheringVO);
		return transactionList;
	}

	@Override	//기간별 회비 입금 내역 조회
	public List<TransactionVO> selectDuesByPeriod(TransactionVO transactionVO) {

		List<TransactionVO> transactionList;
		
		if(transactionVO.getTime().length() > 6) {
			//월간
			System.out.println("월간");
			System.out.println(transactionVO.getTime());
			
//			if(transactionVO.getTime().charAt(5) != '0') {
//				String[] arr = transactionVO.getTime().split(" ");
//				transactionVO.setTime(arr[0] + " 0" +arr[1]);
//				System.out.println(transactionVO.getTime());
//				//arr[0] + arr[1];
//				//transactionVO.getTime().substring(0, 5) + Character.toString(ch) + transactionVO.getTime().substring(7);
//			}
			transactionList = transactionDAO.selectDuesByMonthly(transactionVO);
		}else {
			//연간
			transactionList = transactionDAO.selectDuesByYearly(transactionVO);
			System.out.println("연간");
		}
		
		return transactionList;
	}

	@Override	//모임원별 회비 입금 내역 조회
	public List<TransactionVO> selectDuesByMember(ParticipantVO participantVO) {
		List<TransactionVO> transactionList = transactionDAO.selectDuesByMember(participantVO);
		return transactionList;
	}
	
	
	
	
	

	
}
