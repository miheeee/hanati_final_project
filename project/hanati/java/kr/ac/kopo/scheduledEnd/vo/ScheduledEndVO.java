package kr.ac.kopo.scheduledEnd.vo;

import org.springframework.stereotype.Component;

@Component
public class ScheduledEndVO {

	private String accountNo;
	private String endDate;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "ScheduledEndVO [accountNo=" + accountNo + ", endDate=" + endDate + "]";
	}
}
