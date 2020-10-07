package kr.ac.kopo.participant.vo;

import org.springframework.stereotype.Component;

@Component
public class ParticipantVO {

	private String accountNo;
	private String id;
	private String type;
	private String name;
	private String accountChange;
	private String transfer;
	private String depositDate;
	private String registerDate;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountChange() {
		return accountChange;
	}
	public void setAccountChange(String accountChange) {
		this.accountChange = accountChange;
	}
	public String getTransfer() {
		return transfer;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	public String getDepositDate() {
		return depositDate;
	}
	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	@Override
	public String toString() {
		return "ParticipantVO [accountNo=" + accountNo + ", id=" + id + ", type=" + type + ", name=" + name
				+ ", accountChange=" + accountChange + ", transfer=" + transfer + ", depositDate=" + depositDate
				+ ", registerDate=" + registerDate + "]";
	}

}
