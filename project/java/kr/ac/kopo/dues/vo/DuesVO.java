package kr.ac.kopo.dues.vo;

import org.springframework.stereotype.Controller;

@Controller
public class DuesVO {

	private String transactionNo;
	private String time;
	private String accountNo;
	private int amount;
	private String counterpart;
	private String member;
	
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCounterpart() {
		return counterpart;
	}
	public void setCounterpart(String counterpart) {
		this.counterpart = counterpart;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "DuesVO [transactionNo=" + transactionNo + ", time=" + time + ", accountNo=" + accountNo + ", amount="
				+ amount + ", counterpart=" + counterpart + ", member=" + member + "]";
	}
	
	
}
