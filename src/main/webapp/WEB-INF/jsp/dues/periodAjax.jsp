<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<table class="table">
		<c:forEach items="${participantList}" var="participant">	
			<tr> 
				<td>${participant.name}</td>
				<c:set var="cnt" value="0"/>
				<c:set var="totalAmount" value="0"/>
		
				<c:forEach items="${duesList}" var="dues">
					<c:if test="${participant.name == dues.paidMember}">
						<c:set var="cnt" value="${cnt+1}"/>
						<c:set var="totalAmount" value="${totalAmount + dues.amount}"/>
					</c:if>
				</c:forEach>
					<td>${cnt}건</td>
					<td><h2 class="font-weight-bold"><fmt:formatNumber value="${totalAmount}" pattern="#,###.##" />원</h2></td>	
			</tr>
		</c:forEach>
	</table>


