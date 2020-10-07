<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<c:set var="totalMonthAmount" value="0"/>
		<c:choose>
			<c:when test="${fn:length(date) == 4}">
				<c:forEach items="${duesList}" var="dues">
					<c:if test="${fn:substring(dues.time, 0, 4) == date}">
						<c:set var="totalMonthAmount" value="${totalMonthAmount + dues.amount}"/>
					</c:if>				
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach items="${duesList}" var="dues">
					<c:if test="${fn:substring(dues.time, 0, 7) == date}">
						<c:set var="totalMonthAmount" value="${totalMonthAmount + dues.amount}"/>
					</c:if>				
				</c:forEach>			
			</c:otherwise>
		</c:choose>
	<h2 class="font-weight-bold"><fmt:formatNumber value="${totalMonthAmount}" pattern="#,###.##" />원</h2>						 


	<table class="table info-table mt-4">
		<c:set var="totalCnt" value="0"/>
		<c:forEach items="${participantList}" var="participant">	
			<tr> 
				<td style="width:300px;" class="pt-3 pb-3">${participant.name}</td>
				<c:set var="cnt" value="0"/>
				<c:set var="totalAmount" value="0"/>
		
				<c:forEach items="${duesList}" var="dues">
					<c:if test="${participant.name == dues.paidMember}">
						<c:set var="cnt" value="${cnt+1}"/>
						<c:set var="totalAmount" value="${totalAmount + dues.amount}"/>
					</c:if>
					<c:set var="totalCnt" value="${totalCnt + cnt}"/>
				</c:forEach>
					<td style="width:300px;" class="pt-3 pb-3">${cnt}건</td>
					<td style="font-weight:bold;width:300px;" class="pt-3 pb-3"><fmt:formatNumber value="${totalAmount}" pattern="#,###.##" />원</td>	
			</tr>
		</c:forEach>
	</table>


