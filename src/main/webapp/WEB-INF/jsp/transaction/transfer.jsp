<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<jsp:include page="/WEB-INF/jsp/include/head.jsp" /> 	
</head>
<body>
<!-- header BEGIN -->
	<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<!-- header END -->
    
    <section class="inner-page">
      <div class="slider-item py-5" style="background-image: url('${ pageContext.request.contextPath }/resources/img/slider-1.jpg');">
        
        <div class="container">
          <div class="row slider-text align-items-center justify-content-center text-center">
            <div class="col-md-7 col-sm-12 element-animate">
              <h1 class="text-white">계좌이체</h1>
            </div>
          </div>
        </div>

      </div>
    </section>
    
    <section class="section">
      <div class="container">
        <div class="row mb-5 justify-content-center ">
          <div class="col-md-7 order-lg-1 text-center">
			
			<h2 class="text-uppercase heading border-bottom mb-4">이체 완료</h2>
            <p class="mb-3">이체가 완료되었습니다.</p>
            <p class="mb-3">타행 계좌로 이체 시 입금은행의 사정에 따라 </p>
            <p class="mb-3">해당 계좌의 입금이 다소 지연될 수 있습니다.</p>
            <button class="btn btn-primary" onclick = "location.href = '${ pageContext.request.contextPath }/transaction/transfer/${gatheringVO.safeAccountNo}' ">추가 이체하기</button>
			</div>
			<%-- <table class="table">
				<tr>
					<td>계좌</td>
					<td>모임통장<br>
					${ gatheringVO.accountNo }</td>
				</tr>
				<tr>
					<td>안심계좌번호</td>
					<td>${ gatheringVO.safeAccountNo }</td>
				</tr>	
				<tr>
					<td>모임통장 신청일</td>
					<td><%= new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()) %></td>
				</tr>							
			</table>
			<ul class="list-unstyled check">
              <li>안심계좌번호란 예금주의 계좌 정보 보호를 위해 멤버들에게 보여지는 계좌번호입니다.</li>
              <li>모임통장 신청일 이후 내역부터 멤버들에게 보여집니다.</li>
            </ul>
			<div class="row mb-5 justify-content-center">
			<div class="col-md-8 order-lg-1">
            	<button class="btn btn-primary" onclick = "location.href = '${ pageContext.request.contextPath }/tansaction/transfer' ">추가 이체하기</button>
            </div>
            </div> --%>
            	
		  </div>
		</div>				
      </div>
    </section>
    
    <section class="container cta-overlap">
      <div class="text d-flex">
        <h2 class="h3">Contact Us For Projects or Need a Quotations</h2>
        <div class="ml-auto btn-wrap">
          <a href="get-quote.html" class="btn-cta btn btn-outline-white">Get A Quote</a>
        </div>
      </div>
    </section>
    <!-- END section -->

	<!-- footer BEGIN -->
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp" /> 
    <!-- footer END -->

    <!-- loader -->
    <div id="loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#f4b214"/></svg></div>


    <script src="${ pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/popper.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/owl.carousel.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/jquery.waypoints.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/main.js"></script> 	
	
  </body>
</html>