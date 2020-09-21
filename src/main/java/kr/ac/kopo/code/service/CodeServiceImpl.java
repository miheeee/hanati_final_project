package kr.ac.kopo.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.code.dao.CodeDAO;
import kr.ac.kopo.code.vo.CodeVO;

@Service
public class CodeServiceImpl implements CodeService{

	@Autowired
	private CodeDAO codeDAO;
	
	//거래 가능한 은행 이름 가져오기
	@Override
	public List<CodeVO> selectBankCode() {
		List<CodeVO> codeList = codeDAO.selectBankCode();
		return codeList;
	}

	@Override	//선택 가능한 태그 이름 가져오기
	public List<CodeVO> selectTagCode() {
		List<CodeVO> codeList = codeDAO.selectTagCode();
		return codeList;
	}

	
}
