package kr.ac.kopo.scheduledDepositDate.vo;

import org.springframework.stereotype.Component;

@Component
public class ScheduledDepositDateVO {

	private String accountNo;
	private int dueDate;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public int getDueDate() {
		return dueDate;
	}
	public void setDueDate(int dueDate) {
		this.dueDate = dueDate;
	}
	@Override
	public String toString() {
		return "ScheduledDepositDateVO [accountNo=" + accountNo + ", dueDate=" + dueDate + "]";
	}
	
}
