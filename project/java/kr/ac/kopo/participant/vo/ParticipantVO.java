package kr.ac.kopo.participant.vo;

import org.springframework.stereotype.Component;

@Component
public class ParticipantVO {

	private String accountNo;
	private String id;
	private String type;
	private String accountChange;
	private String transfer;
	private String depositeDate;
	private String name;
	
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
	public String getDepositeDate() {
		return depositeDate;
	}
	public void setDepositeDate(String depositeDate) {
		this.depositeDate = depositeDate;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ParticipantVO [accountNo=" + accountNo + ", id=" + id + ", type=" + type + ", accountChange="
				+ accountChange + ", transfer=" + transfer + ", depositeDate=" + depositeDate + ", name=" + name + "]";
	}
	

}
