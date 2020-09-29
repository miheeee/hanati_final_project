<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	  		<c:choose>
		  		<c:when test="${empty scheduledEndVO}">
				  	<ul class="list-unstyled check">
		  				<li>현재 모임통장은 사용 중입니다.</li>
		 			<c:if test="${gatheringId == loginVO.id }">
		 				<li>종료하시려면 아래 사용 종료 버튼을 눌러주세요.</li>
		 				<button id="terminateBtn" class="terminateChangeBtn btn btn-primary button mt-3">모임통장 사용 종료</button>
		 			</c:if>
		 			</ul>
		 		</c:when>
		 		<c:otherwise>
				  	<ul class="list-unstyled check">
		  				<li>모임통장은 ${scheduledEndVO.endDate}에 종료 예정입니다.</li>		 		
			 			<c:if test="${gatheringId == loginVO.id }"><button id="terminateCancelBtn" type="button" class="terminateChangeBtn btn btn-primary button  mt-3">종료 취소</button></c:if>	
		 			</ul>
		 		</c:otherwise>
	  		</c:choose>