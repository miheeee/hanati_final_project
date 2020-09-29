<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/925e80bab6.js"></script>

			<c:forEach items="${participantList}">
					<span style="font-size: 2em; color:Tomato;">
					  <a data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
					    <i class="fas fa-user-circle" style="color:gray;"></i>
					  </a>
					</span>
				</c:forEach>

				<div class="collapse" id="collapseExample">
				  <div class="card card-body">
				  		참여중 멤버(${fn:length(participantList)})
				   	<table class="table">
				   		<c:forEach items="${participantList}" var="participant">
				   			<c:choose>
					   			<c:when test="${participant.type == '301'}">
					   				<c:set var="holder" value="${participant.name}"/>
					   				<c:if test="${gathering.id == loginVO.id }">
					   					<a id="kakao-link-btn" href="javascript:sendLink()">초대</a>
							   		</c:if>
							   		<tr>
							   			<td>
										   	<span style="font-size: 2em; color: Tomato;">
											    <i class="fas fa-user-circle" style="color:gray;"></i>
											</span>${participant.name}★
							   			</td>
							   			<td>
							   				<span class="d-none">d</span>
							   			</td>
							   		</tr>					   				
					   			</c:when>
					   			<c:otherwise>
							   		<tr>
							   			<td>
										   	<span style="font-size: 2em; color: Tomato;">
											    <i class="fas fa-user-circle" style="color:gray;"></i>
											</span>${participant.name}
							   			</td>
							   			<td>
							   				<c:if test="${gathering.id == loginVO.id }">
							   					<input type="button" value="멤버끊기" id="${participant.id}" name="${participant.name}" class="expel btn btn-primary">
							   				</c:if>
							   			</td>
							   		</tr>					   			
					   			</c:otherwise>
				   			</c:choose>
				   		</c:forEach>

				   	</table>	
				  </div>