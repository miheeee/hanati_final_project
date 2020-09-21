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
	<script src="https://code.jquery.com/jquery-latest.js"></script>
	<jsp:include page="/WEB-INF/jsp/include/head.jsp" /> 	

<script>
	$(document).ready(function() {
	    $('#myModal').show();
	});
	//팝업 Close 기능
	function close_pop(flag) {
 		if(!'${loginVO}'){
	 		location.href='${ pageContext.request.contextPath }/login';	 		
		}else{ 
			$.ajax({
				url: '${ pageContext.request.contextPath }/participant/join',
				type: 'post', 
				data: {
					accountNo: ${ gatheringVO.accountNo }
				},
				success: function(data){
					$('#myModal').hide(); 
					$('#joinComplete').html(data); 
				},error: function(){
					alert("문제가 생겼습니다. 다시 시도해주세요");
				}
			})
 		}

	};
</script>
</head>
<body>
<!-- header BEGIN -->
	<!-- 아이콘 -->
	<script src="https://kit.fontawesome.com/925e80bab6.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
	
	<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
	
<!-- header END -->
 
 <!-- The Modal -->
<div id="myModal" class="modal">
  <div class="modal-dialog modal-dialog-centered modal-lg">
    <div class="modal-content">
     
      <div class="modal-header  justify-content-center text-center">
        <h5 class="modal-title" id="exampleModalLabel">HANA MOIM</h5>
      </div>
      
      <div class="modal-body justify-content-center text-center">
      	<div>
			<span style="font-size: 2em; color: black;">
				<i class="fas fa-user-friends style="color:black;"></i>
			</span>      		
      	</div>
        <p>${accountVO.holder}님이 ${ gatheringVO.name } 모임통장에</p>
      	<p>초대하셨습니다.</p>
      </div>
      
      <div class="modal-footer">
      	<button type="button" class="btn btn-secondary"
      				onclick="location.href='${ pageContext.request.contextPath }/' ">취소</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" 
        			onclick="close_pop();">수락하기</button>
      </div>
    </div>
  </div>
</div> 


 
   
    <section class="inner-page">
      <div class="slider-item py-5" style="background-image: url('${ pageContext.request.contextPath }/resources/img/slider-2.jpg');">
        
        <div class="container">
          <div class="row slider-text align-items-center justify-content-center text-center">
            <div class="col-md-7 col-sm-12 element-animate">
              <h1 class="text-white">모임통장 수락</h1>
            </div>
          </div>
        </div>

      </div>
    </section>
    
    <section class="section">
      <div class="container">
        <div class="row mb-5 justify-content-center">
          <div id="joinComplete" class="col-md-7 order-lg-1">
			
            	
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