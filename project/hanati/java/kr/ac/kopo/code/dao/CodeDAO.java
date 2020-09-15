package kr.ac.kopo.code.dao;

import java.util.List;

import kr.ac.kopo.code.vo.CodeVO;

public interface CodeDAO {

	/**
	 * 은행 코드, 이름 조회
	 */
	public List<CodeVO> selectBankCode();
}
