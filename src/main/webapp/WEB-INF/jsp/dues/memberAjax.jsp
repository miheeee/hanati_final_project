<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<table class="table text-center mt-3">
		<c:forEach items="${yearList}" var="year">
			<tr>
				<th style="width:250px;background-color:#27b2a517;">${year}</th>	
				<!-- <th><span id="total"></span></th> -->
				<c:set var="yearlAmount" value='0'/>
				<c:forEach items="${duesList}" var="dues">
					<c:if test="${fn:substring(dues.time, 0, 4) == fn:substring(year, 0, 4)}">
						<c:set var="yearlAmount" value="${yearlAmount + dues.amount}"/>
					</c:if>
				</c:forEach>
				<th style="width:250px;background-color:#27b2a517;">총 <fmt:formatNumber value="${yearlAmount}" pattern="#,###.##" />원</th>	
			</tr>	
			<c:set var="amountYearly" value="0"/>
			<c:forEach items="${monthList}" var="month">
				<c:set var="amountMonthly" value="0"/>
				<c:if test="${fn:substring(month, 0, 5) == year}">
					<c:set var="mm" value="${fn:substring(month, 5, 8)}"/>
					<c:forEach items="${duesList}" var="dues">
						<c:set var="duesYear" value="${fn:substring(dues.time, 0, 4)}년"/>
						<c:set var="duesMonth" value="${fn:substring(dues.time, 5, 7)}월"/>
						<c:if test="${duesYear == year && mm == duesMonth}">
							<c:set var="amountMonthly" value="${amountMonthly + dues.amount}"/>
							<c:set var="amountYearly" value="${amountYearly + dues.amount}"/>
						</c:if>
					</c:forEach>
				</c:if>
				<tr>
					<td style="width:250px;">${ (fn:substring(month, 5, 6) == 0)? fn:substring(month, 6, 7) : fn:substring(month, 5, 7)}월</td>
					<td style="width:250px;"><fmt:formatNumber value="${amountMonthly}" pattern="#,###.##" />원</td>		<!--  ${amountYearly} 왜 안되지? -->
				</tr>
			</c:forEach>
		</c:forEach>
	</table>
	
	<script>
		$(document).ready(function(){ 
			$('#totalAmount').html(${totalAmount});  
		}) 
	</script>