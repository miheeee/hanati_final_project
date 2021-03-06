package kr.ac.kopo.code.service;

import java.util.List;

import kr.ac.kopo.code.vo.CodeVO;

public interface CodeService {

	/**
	 * 거래 가능한 은행 이름 가져오기
	 */
	public List<CodeVO> selectBankCode(); 
	
	/**
	 * 선택 가능한 태그 목록 가져오기
	 */
	public List<CodeVO> selectTagCode(); 
}
