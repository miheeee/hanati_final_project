package kr.ac.kopo.account.vo;

import org.springframework.stereotype.Component;

@Component
public class AccountVO {

	private String accountNo;
	private String password;
	private String holder;
	private int balance;
	private String productName;
	private String id;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "AccountVO [accountNo=" + accountNo + ", password=" + password + ", holder=" + holder + ", balance="
				+ balance + ", productName=" + productName + ", id=" + id + "]";
	}
	
}
