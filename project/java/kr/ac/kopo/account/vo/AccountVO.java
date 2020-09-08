package kr.ac.kopo.account.vo;

import org.springframework.stereotype.Component;

@Component
public class AccountVO {

	private String accountNo;
	private String holder;
	private int balance;
	private String type;
	private String safeAccountNo;
	private String status;
	private String alias;
	private String id;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSafeAccountNo() {
		return safeAccountNo;
	}
	public void setSafeAccountNo(String safeAccountNo) {
		this.safeAccountNo = safeAccountNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "AccountVO [accountNo=" + accountNo + ", holder=" + holder + ", balance=" + balance + ", type=" + type
				+ ", safeAccountNo=" + safeAccountNo + ", status=" + status + ", alias=" + alias + ", id=" + id + "]";
	}

	
}
