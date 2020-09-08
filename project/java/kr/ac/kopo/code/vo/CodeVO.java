package kr.ac.kopo.code.vo;

public class CodeVO {

	private String code;
	private String codeName;
	private String codeType;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	@Override
	public String toString() {
		return "CodeVO [code=" + code + ", codeName=" + codeName + ", codeType=" + codeType + "]";
	}
	
}

