package kr.ac.kopo.scheduledDepositDate.vo;

import org.springframework.stereotype.Component;

@Component
public class ScheduledDepositDateVO {

	private String accountNo;
	private String id;
	private int regularDay;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRegularDay() {
		return regularDay;
	}
	public void setRegularDay(int regularDay) {
		this.regularDay = regularDay;
	}
	@Override
	public String toString() {
		return "ScheduledDepositDateVO [accountNo=" + accountNo + ", id=" + id + ", regularDay=" + regularDay + "]";
	}
	
}
