<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
	<table class="table">
		<c:forEach items="${participantList}" var="participant">	
			<tr> 
				<td>${participant.name}</td>
				<c:set var="cnt" value="0"/>
				<%-- <fmt:formatNumber value="${cnt}" type="number" var="cnt" /> --%>
				<c:set var="totalAmount" value="0"/>
				<%-- <fmt:formatNumber value="${totalAmount}" type="number" var="totalAmount" /> --%>
		
				<c:forEach items="${duesList}" var="dues">
					<c:if test="${participant.name == dues.paidMember}">
						<c:set var="cnt" value="${cnt+1}"/>
						<c:set var="totalAmount" value="${totalAmount + dues.amount}"/>
					</c:if>
				</c:forEach>
					<td>${cnt}건</td>
					<td>${totalAmount}원</td>	
			</tr>
		</c:forEach>
	</table>


