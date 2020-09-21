package kr.ac.kopo.code.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.code.vo.CodeVO;

@Repository
public class CodeDAOImpl implements CodeDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//은행 코드, 이름 조회
	@Override
	public List<CodeVO> selectBankCode() {
		List<CodeVO> codeList = sqlSession.selectList("code.dao.CodeDAO.selectBankCode");
		return codeList;
	}

	@Override	//태그 코드, 이름 조회
	public List<CodeVO> selectTagCode() {
		List<CodeVO> codeList = sqlSession.selectList("code.dao.CodeDAO.selectTagCode");
		return codeList;
	}
	
	
}
