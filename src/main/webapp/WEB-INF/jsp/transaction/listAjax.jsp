<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

  <h3 id="transaction" class="table-title">거래내역</h3>
  	<div>
		<table class="hTable"> <!-- class="table-bordered table info-table" -->
			<thead class="hHead">	
				<tr>
					<th>일시</th>
					<th>거래 대상</th>
					<th>거래 구분</th>
					<th>금액</th>
					<th>거래 후 잔액</th>
				</tr>	
			</thead>
			<tbody class="hBody">				
				<c:forEach items="${transactionList}" var="transaction" varStatus="loop">
					<tr>
						<td>${transaction.time}</td>
						<td>
							<c:choose>
								<c:when test="${not empty transaction.indication}">
									${transaction.indication}
								</c:when>
								<c:otherwise>
									${transaction.counterpart}
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${transaction.classification == '401'}">
									입금
								</c:when>
								<c:when test="${transaction.classification == '402'}">
									출금
								</c:when>
							</c:choose>
						</td>
						
							<c:choose>
								<c:when test="${transaction.classification == '401'}">
									<td class="text-right" style="color:#27b2a5;"> <fmt:formatNumber value="${transaction.amount}" pattern="+#,###.##" /></td>
								</c:when>
								<c:when test="${transaction.classification == '402'}">
									<td class="text-right" style="color:red;"> <fmt:formatNumber value="${transaction.amount}" pattern="-#,###.##" /></td>
								</c:when>
							</c:choose>					
						<td><fmt:formatNumber value="${transaction.balance}" pattern="#,###.##" /></td>
						<%-- <td>${transaction.memo}</td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>			  		
  	</div>
	 <c:if test="${gathering.id == loginVO.id }"> 	
		<form id="excelForm" name="excelForm" method="post" action="${ pageContext.request.contextPath }/excelDownload">
			<input type="hidden" name="safeAccountNo" value="${gathering.safeAccountNo}">
			<input type="hidden" name="accountNo" value="${gathering.accountNo}">
			<input type="hidden" name="id" value="${gathering.id}">
			<input type="submit" class="excelDownBtn" value="거래내역 다운로드" />
			<img alt="excel" src="${ pageContext.request.contextPath }/resources/img/excel-png.png" style="width:2em;height:2em;float:right;">
		</form> 
	</c:if>