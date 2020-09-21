package kr.ac.kopo.transaction.vo;

import org.springframework.stereotype.Component;

@Component
public class TransactionVO {

	private int transactionNo;
	private String time;
	private String classification;
	private int amount;
	private int balance;
	private String accountNo;
	private String indication;
	private String counterpart;
	private String counterpartAccountNo;
	private String counterpartBank;
	private String paidMember;
	private String memo;
	
	public int getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(int transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getIndication() {
		return indication;
	}
	public void setIndication(String indication) {
		this.indication = indication;
	}
	public String getCounterpart() {
		return counterpart;
	}
	public void setCounterpart(String counterpart) {
		this.counterpart = counterpart;
	}
	public String getCounterpartAccountNo() {
		return counterpartAccountNo;
	}
	public void setCounterpartAccountNo(String counterpartAccountNo) {
		this.counterpartAccountNo = counterpartAccountNo;
	}
	public String getCounterpartBank() {
		return counterpartBank;
	}
	public void setCounterpartBank(String counterpartBank) {
		this.counterpartBank = counterpartBank;
	}
	public String getPaidMember() {
		return paidMember;
	}
	public void setPaidMember(String paidMember) {
		this.paidMember = paidMember;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "TransactionVO [transactionNo=" + transactionNo + ", time=" + time + ", classification=" + classification
				+ ", amount=" + amount + ", balance=" + balance + ", accountNo=" + accountNo + ", indication="
				+ indication + ", counterpart=" + counterpart + ", counterpartAccountNo=" + counterpartAccountNo
				+ ", counterpartBank=" + counterpartBank + ", paidMember=" + paidMember + ", memo=" + memo + "]";
	}

}
