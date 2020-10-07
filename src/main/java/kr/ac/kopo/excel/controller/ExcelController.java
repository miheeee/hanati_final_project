package kr.ac.kopo.excel.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
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

		List<TransactionVO> transactionList = transactionService.selectTransBySafeAccountNo(gatheringVO);
		
		
	      HSSFWorkbook objWorkBook = new HSSFWorkbook();
	      HSSFSheet objSheet = null;
	      HSSFRow objRow = null;
	      HSSFCell objCell = null;       //셀 생성

	      //제목 폰트
		  HSSFFont font = objWorkBook.createFont();
		  font.setFontHeightInPoints((short)10);
		  font.setBoldweight((short)font.BOLDWEIGHT_BOLD);
		  font.setFontName("Arial");
	
		  //제목 스타일에 폰트 적용, 정렬
		  //1)제목
		  HSSFFont font1 = objWorkBook.createFont();
		  font1.setFontHeightInPoints((short)11);
		  font1.setBoldweight((short)font.BOLDWEIGHT_BOLD);
		  font1.setFontName("Arial");
		  
		  HSSFCellStyle styleHd1 = objWorkBook.createCellStyle();   
		  styleHd1.setFont(font1);
		  styleHd1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		  styleHd1.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);

		  //2)칼럼
		  HSSFCellStyle styleHd2 = objWorkBook.createCellStyle();   
		  styleHd2.setFont(font);
		  styleHd2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		  styleHd2.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
		  //배경색
		  styleHd2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		  styleHd2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		  //테두리 선 (우,좌,위,아래)
		  styleHd2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		  styleHd2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		  styleHd2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		  styleHd2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		  
		  //3)셀
		  HSSFFont font3 = objWorkBook.createFont();
		  font3.setFontHeightInPoints((short)10);
		  font3.setFontName("Arial");
		  
		  HSSFCellStyle styleHd3 = objWorkBook.createCellStyle();   
		  styleHd3.setFont(font3);
		  styleHd3.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
		  //테두리 선 (우,좌,위,아래)
		  styleHd3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		  styleHd3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		  styleHd3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		  styleHd3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		  
		  //4)금액
		  HSSFCellStyle styleHd4 = objWorkBook.createCellStyle();   
		  styleHd4.setFont(font3);
		  styleHd4.setAlignment(CellStyle.ALIGN_RIGHT);//우측 정렬
		  styleHd4.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
		  //테두리 선 (우,좌,위,아래)
		  styleHd4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		  styleHd4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		  styleHd4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		  styleHd4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		  //천단위 쉼표, 금액
		  styleHd4.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));

		  //워크시트 생성//
		  objSheet = objWorkBook.createSheet("Sheet1");     
		  //셀 병합
		  objSheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
		  //열 폭 수정
		  objSheet.setColumnWidth(0, 5000);
		  objSheet.setColumnWidth(1, 4000);		  
		  objSheet.setColumnWidth(2, 4000);		  
		  objSheet.setColumnWidth(3, 4000);		  
		  objSheet.setColumnWidth(4, 4000);		  
		  objSheet.setColumnWidth(5, 4000);		  
		  
		  // 1행
		  objRow = objSheet.createRow(0);
		  objRow.setHeight ((short) 0x250);
	
		  objCell = objRow.createCell(0);
		  objCell.setCellValue("거래내역조회");
		  objCell.setCellStyle(styleHd1);
	
		  // 2행
		  objRow = objSheet.createRow(1);
		  objRow.setHeight ((short) 0x150);
	
		  objCell = objRow.createCell(0);
		  objCell.setCellValue("계좌번호");
		  objCell.setCellStyle(styleHd2);
		  
		  objCell = objRow.createCell(1);
		  objCell.setCellValue(gatheringVO.getAccountNo());
		  objCell.setCellStyle(styleHd3);
		  
		  objCell = objRow.createCell(2);
		  objCell.setCellValue("모임통장 이름");
		  objCell.setCellStyle(styleHd2);
		  
		  objCell = objRow.createCell(3);
		  objCell.setCellValue(gatheringVO.getName());
		  objCell.setCellStyle(styleHd3);
		  
		  objCell = objRow.createCell(4);
		  objCell.setCellValue("잔액");
		  objCell.setCellStyle(styleHd2);
		  
		  objCell = objRow.createCell(5);
		  objCell.setCellValue(gatheringVO.getBalance());
		  objCell.setCellStyle(styleHd4);
		  
		  // 4행
		  objRow = objSheet.createRow(3);
		  objRow.setHeight ((short) 0x150);
	
		  objCell = objRow.createCell(0);
		  objCell.setCellValue("거래일시");
		  objCell.setCellStyle(styleHd2);
	
		  objCell = objRow.createCell(1);
		  objCell.setCellValue("거래 대상");
		  objCell.setCellStyle(styleHd2);
	
		  objCell = objRow.createCell(2);
		  objCell.setCellValue("구분");
		  objCell.setCellStyle(styleHd2);
		  
		  objCell = objRow.createCell(3);
		  objCell.setCellValue("금액");
		  objCell.setCellStyle(styleHd2);
		  
		  objCell = objRow.createCell(4);
		  objCell.setCellValue("잔액");
		  objCell.setCellStyle(styleHd2);
		  
		  //5행~
		  int cnt = 4;
		  for(TransactionVO transactionVO:transactionList) {
			  objRow = objSheet.createRow(cnt);
			  objRow.setHeight ((short) 0x150);
			  
			  objCell = objRow.createCell(0);
			  objCell.setCellValue(transactionVO.getTime());
			  objCell.setCellStyle(styleHd3);
	
			  if(transactionVO.getIndication() != null) {
				  objCell = objRow.createCell(1);
				  objCell.setCellValue(transactionVO.getIndication());
				  objCell.setCellStyle(styleHd3);
			  }else {
				  objCell = objRow.createCell(1);
				  objCell.setCellValue(transactionVO.getCounterpart());
				  objCell.setCellStyle(styleHd3);
			  }
	
			  if(transactionVO.getClassification().equals("401")) {
				  objCell = objRow.createCell(2);
				  objCell.setCellValue("입금");
				  objCell.setCellStyle(styleHd3);
			  }else if(transactionVO.getClassification().equals("402")) {
				  objCell = objRow.createCell(2);
				  objCell.setCellValue("출금");
				  objCell.setCellStyle(styleHd3);
			  }
			  
			  objCell = objRow.createCell(3);
			  objCell.setCellValue(transactionVO.getAmount());
			  objCell.setCellStyle(styleHd4);
			  
			  objCell = objRow.createCell(4);
			  objCell.setCellValue(transactionVO.getBalance());
			  objCell.setCellStyle(styleHd4);
			  
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

