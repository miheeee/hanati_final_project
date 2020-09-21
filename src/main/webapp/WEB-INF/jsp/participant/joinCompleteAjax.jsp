<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<h2 class="text-uppercase heading mb-4">모임통장 수락 완료</h2>
           <p class="mb-3">모임통장 참여 요청을 수락하였습니다.</p>
           <p class="mb-3">아래 내용을 확인해 주세요.</p>
		<table class="table">
			<tr>
				<td>모임명</td>
				<td>${ gatheringVO.name }</td>
			</tr>
			<tr>
				<td>모임통장 수락일</td>
				<td><%= new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()) %></td>
			</tr>								
		</table>
		<ul class="list-unstyled check">
             <li>모임통장은 모임주 개인 명의의 통장으로 멤버들이 납입한 모임 회비의 지급, 해지 권한은 모임주에게 있습니다.</li>
             <li>압류 등 모임주의 상태에 따라 모임 회비 및 모임통장 서비스 이용이 제한될 수 있습니다.</li>
           </ul>
		<div class="row mb-5">
		<div class="col-md-8 order-lg-1">
           	<button id="detail" class="btn btn-primary" style="background-color:#27b2a5;border-color:#27b2a5;" 
           			onclick="location.href='${ pageContext.request.contextPath }/gathering/detail/${ gatheringVO.safeAccountNo }'">모임통장 보러가기</button>
           </div>
           </div>