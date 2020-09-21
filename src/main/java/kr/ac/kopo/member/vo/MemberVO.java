package kr.ac.kopo.member.vo;


//import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;
@Component
public class MemberVO {

//	@NotEmpty(message = "아이디를 입력하세요")
	private String id;
//	@NotEmpty(message = "비밀번호를 입력하세요")
	private String password;
	private String name;
	private String tel;
	private String email;
	private String addr;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", tel=" + tel + ", email=" + email
				+ ", addr=" + addr + "]";
	}

}
	
