package kr.ac.kopo.excel.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.gathering.vo.GatheringVO;
import kr.ac.kopo.transaction.service.TransactionService;
import kr.ac.kopo.transaction.vo.TransactionVO;

@Controller
public class ExcelController {
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping("/excelDownload")
	  public void ExcelPoi(HttpServletResponse response, Model model, GatheringVO gatheringVO) throws Exception {

		System.out.println("안녕");
		System.out.println(gatheringVO);
		List<TransactionVO> transactionList = transactionService.selectTransBySafeAccountNo(gatheringVO);
		
	      HSSFWorkbook objWorkBook = new HSSFWorkbook();
	      HSSFSheet objSheet = null;
	      HSSFRow objRow = null;
	      HSSFCell objCell = null;       //셀 생성

	        //제목 폰트
		  HSSFFont font = objWorkBook.createFont();
		  font.setFontHeightInPoints((short)9);
		  font.setBoldweight((short)font.BOLDWEIGHT_BOLD);
		  font.setFontName("맑은고딕");
	
		  //제목 스타일에 폰트 적용, 정렬
		  HSSFCellStyle styleHd = objWorkBook.createCellStyle();    //제목 스타일
		  styleHd.setFont(font);
		  styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		  styleHd.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
	
		  objSheet = objWorkBook.createSheet("Sheet1");     //워크시트 생성
	
		  // 1행
		  objRow = objSheet.createRow(0);
		  objRow.setHeight ((short) 0x150);
	
		  objCell = objRow.createCell(0);
		  objCell.setCellValue("거래내역조회");
		  objCell.setCellStyle(styleHd);
	
		  // 2행
		  objRow = objSheet.createRow(1);
		  objRow.setHeight ((short) 0x150);
	
		  objCell = objRow.createCell(0);
		  objCell.setCellValue("계좌번호");
		  objCell.setCellStyle(styleHd);
		  
		  objCell = objRow.createCell(1);
		  objCell.setCellValue("계좌번호");
	
		  objCell = objRow.createCell(2);
		  objCell.setCellValue("모임통장 이름");
		  objCell.setCellStyle(styleHd);
		  
		  objCell = objRow.createCell(3);
		  objCell.setCellValue("모임통장 이름");
		  
		  objCell = objRow.createCell(4);
		  objCell.setCellValue("잔액");
		  objCell.setCellStyle(styleHd);
		  
		  objCell = objRow.createCell(5);
		  objCell.setCellValue("잔액");
		  
		  // 4행
		  objRow = objSheet.createRow(3);
		  objRow.setHeight ((short) 0x150);
	
		  objCell = objRow.createCell(0);
		  objCell.setCellValue("거래일시");
		  objCell.setCellStyle(styleHd);
	
		  objCell = objRow.createCell(1);
		  objCell.setCellValue("거래 대상");
		  objCell.setCellStyle(styleHd);
	
		  objCell = objRow.createCell(2);
		  objCell.setCellValue("구분");
		  objCell.setCellStyle(styleHd);
		  
		  objCell = objRow.createCell(3);
		  objCell.setCellValue("금액");
		  objCell.setCellStyle(styleHd);
		  
		  objCell = objRow.createCell(4);
		  objCell.setCellValue("잔액");
		  objCell.setCellStyle(styleHd);
		  
		  //5행~
		  int cnt = 4;
		  for(TransactionVO transactionVO:transactionList) {
			  objRow = objSheet.createRow(cnt);
			  objRow.setHeight ((short) 0x150);
			  
			  objCell = objRow.createCell(0);
			  objCell.setCellValue(transactionVO.getTime());
	
			  if(transactionVO.getIndication() != null) {
				  objCell = objRow.createCell(1);
				  objCell.setCellValue(transactionVO.getIndication());
			  }else {
				  objCell = objRow.createCell(1);
				  objCell.setCellValue(transactionVO.getCounterpart());
			  }
	
			  if(transactionVO.getClassification().equals("401")) {
				  objCell = objRow.createCell(2);
				  objCell.setCellValue("입금");
			  }else if(transactionVO.getClassification().equals("402")) {
				  objCell = objRow.createCell(2);
				  objCell.setCellValue("출금");
			  }
			  
			  objCell = objRow.createCell(3);
			  objCell.setCellValue(transactionVO.getAmount());
			  
			  objCell = objRow.createCell(4);
			  objCell.setCellValue(transactionVO.getBalance());
			  
			  cnt += 1;
		  }
		  
		  //제목을 설정하며 확장자 이름을 설정해 준수, 다운로드를 할 수 있도록 지원하고 있다.
		  response.setContentType("Application/Msexcel");
		  //파일명에 나타내기 위해 오늘 날짜를 구함 
		  String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		  response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("거래내역조회_" + date,"UTF-8")+".xls");
	
		  OutputStream fileOut  = response.getOutputStream();
		  objWorkBook.write(fileOut);
		  fileOut.close();
	
		  response.getOutputStream().flush();
		  response.getOutputStream().close();
	}
}

