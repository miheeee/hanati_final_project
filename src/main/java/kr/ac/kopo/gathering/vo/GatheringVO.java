package kr.ac.kopo.gathering.vo;

import org.springframework.stereotype.Component;

@Component
public class GatheringVO {

	private String safeAccountNo;
	private String accountNo;
	private String name;
	private String password;
	private int balance;
	private int tagS;
	private int tagL;
	private String startDate;
	private String id;
	
	public String getSafeAccountNo() {
		return safeAccountNo;
	}
	public void setSafeAccountNo(String safeAccountNo) {
		this.safeAccountNo = safeAccountNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getTagS() {
		return tagS;
	}
	public void setTagS(int tagS) {
		this.tagS = tagS;
	}
	public int getTagL() {
		return tagL;
	}
	public void setTagL(int tagL) {
		this.tagL = tagL;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "GatheringVO [safeAccountNo=" + safeAccountNo + ", accountNo=" + accountNo + ", name=" + name
				+ ", password=" + password + ", balance=" + balance + ", tagS=" + tagS + ", tagL=" + tagL
				+ ", startDate=" + startDate + ", id=" + id + "]";
	}

}
