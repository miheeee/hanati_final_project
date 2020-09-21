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
      <div class="slider-item py-5" style="background-image: url('${ pageContext.request.contextPath }/resources/img/slider-2.jpg');">
        
        <div class="container">
          <div class="row slider-text align-items-center justify-content-center text-center">
            <div class="col-md-7 col-sm-12 element-animate">
              <h1 class="text-white">모임통장 신청</h1>
            </div>
          </div>
        </div>

      </div>
    </section>
    
    <section class="section">
      <div class="container">
        <div class="row mb-5 justify-content-center">
          <div class="col-md-8 order-lg-1">

			<form method="post" class="needs-validation" name="aForm" novalidate>
			  
			  <div class="form-row">
			  	<div class="col-md-12 mb-3">
			  	 <h2 class="text-uppercase heading mb-4">모임통장으로 사용할 계좌를 선택해 주세요</h2>
			      <select name="accountNo" class="custom-select" id="validationCustom01" required>
			        <c:forEach items="${ accountList }" var="account" varStatus="loop">
			        	<option value="${ account.accountNo }">${ account.accountNo } ${ account.productName }</option>
			      	</c:forEach>
			      </select>
			      <div class="invalid-feedback">
					모임통장으로 전환할 입출금 계좌를 선택해주세요!
			      </div>
			      
			  	<div id="selectAccount"></div>
			  </div>			  
			 </div>
			 
			  <div class="form-row">
			  	<div class="col-md-12 mb-3">
			  	 <h2 class="text-uppercase heading mb-4">선택한 계좌의 비밀번호를 입력해주세요</h2>
			      <input type="password" name="password" class="form-control" id="validationCustom02" required>
			      <div class="invalid-feedback">
					계좌 비밀번호를 입력해주세요!
			      </div>
			      
			  	<div id="selectAccount"></div>
			  </div>			  
			 </div>			 
			  
			  <div class="form-row">   
			    <div class="col-md-12 mb-3">
			    <h2 class="text-uppercase heading mb-4">멤버들과 함께 볼 계좌 이름을 지어주세요</h2>
			      <input type="text" name="name" class="form-control" id="validationCustom03" 
			      			placeholder="계좌이름" required>
			      <div class="invalid-feedback">
					모임통장 이름을 설정해주세요!
			      </div>
			    </div>
			  </div> 
			
			
			  <div class="form-row">   
			    <div class="col-md-12 mb-3">
			     <h2 class="text-uppercase heading mb-4">모임 성격에 맞는 태그를 최대 2개까지 선택할 수 있습니다</h2>
					<input type="checkbox" id="tag1" name="tag" value="1">
					<label for="vehicle1">가족/생활비</label><br>
					<input type="checkbox" id="tag2" name="tag" value="2">
					<label for="vehicle2">데이트</label><br>
					<input type="checkbox" id="tag3" name="tag" value="3">
					<label for="vehicle3">팬모임</label><br>
					<input type="checkbox" id="tag4" name="tag" value="4">
					<label for="vehicle3">공연/전시</label><br>
					<input type="checkbox" id="tag5" name="tag" value="5">
					<label for="vehicle3">레저/스포츠</label><br>
					<input type="checkbox" id="tag6" name="tag" value="6">
					<label for="vehicle3">여행</label><br>
					<input type="checkbox" id="tag7" name="tag" value="7">
					<label for="vehicle3">학교/동아리</label><br>
					<input type="checkbox" id="tag8" name="tag" value="8">
					<label for="vehicle3">회사/팀</label><br>
					<input type="checkbox" id="tag9" name="tag" value="9">
					<label for="vehicle3">스터디</label><br>
					<input type="checkbox" id="tag10" name="tag" value="10">
					<label for="vehicle3">친목</label><br>																								
			      <div class="invalid-feedback">
					모임 태그를 설정해주세요!
			      </div>
			    </div>
			  </div> 
			  
			  <div class="form-row">
			  	<div class="col-md-12 mb-3">
			  		<h2 class="text-uppercase heading mb-4">모임통장 이용 약관을 읽고 동의해주세요</h2>
			  	</div>
			  </div>  
			  <div class="form-group">
				 <div class="form-check">
				      <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required
				      				data-toggle="modal" data-target="#myModal">
				      <label class="form-check-label" for="invalidCheck">
				       	약관에 동의
				      </label>
				      <div class="invalid-feedback">
						제출하시기 전 이용약관에 동의해주세요!
				      </div>
				    </div>
				    <p class="mb-3">모임통장으로 이용불가능 입출금 계좌 : 대출 이용 계좌, 비활성 계좌, 거래제한 계좌</p>
				  	<!-- <button class="btn btn-primary button" id="applyBtn">신청</button> -->
				  	<button type="button" class="btn btn-primary button" id="applyBtn" >신청</button>
				  </div>
			</form>
			
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


	  <!-- The Modal -->
	  <div class="modal" id="myModal">
	    <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered modal-lg">
	      <div class="modal-content">
	      
	        <!-- Modal Header -->
	        <div class="modal-header">
	          <h1 class="modal-title">모임통장 특약</h1>
	          <button type="button" class="close" data-dismiss="modal">×</button>
	        </div>
	        
	        <!-- Modal body -->
	        <div class="modal-body">
				<img src="${ pageContext.request.contextPath }/resources/img/terms_of_use.jpg" alt="My Image"
					width="100%" height="100%">
	        </div>
	        
	        <!-- Modal footer -->
	        <div class="modal-footer">
	          <button type="button" class="btn button" data-dismiss="modal">확인</button>
	        </div>
	        
	      </div>
	    </div>
	  </div>
	



    <script src="${ pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/popper.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/owl.carousel.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/jquery.waypoints.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/main.js"></script>
  
	<script>
 /* 	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function() {
	  'use strict';
	  window.addEventListener('load', function() {
	    // Fetch all the forms we want to apply custom Bootstrap validation styles to
	    var forms = document.getElementsByClassName('needs-validation');
	    // Loop over them and prevent submission
	    var validation = Array.prototype.filter.call(forms, function(form) {
	      form.addEventListener('submit', function(event) {
	        if (form.checkValidity() === false) {
	          event.preventDefault();
	          event.stopPropagation();
	        }
	        form.classList.add('was-validated');
	      }, false);
	    });
	  }, false);
	})(); */ 

	
		//모임통장 신청폼 넘기는 함수
		function submitForm(){
			let accountNo = document.aForm.accountNo.value;
			let password = document.aForm.password.value;
			let name = document.aForm.name.value;
			
			let tags = [];
			let tagArr = document.aForm.tag;
			for(let i = 0; i < tagArr.length; i++) {
				if(tagArr[i].checked) {
					console.log(tagArr[i].value)
					tags.push(tagArr[i].value)
				} 
					
			}
			console.log(tags)
			
			/*
			let tagArr = document.aForm.tag.value;
			let tagS, tagL;
			console.log(tagArr);
			if(tagArr.length == 1){
				tagS = tagArr[0];
			}else if(tagArr.length == 2){
				if(tagArr[0] < tagArr[1]){
					tagS = tagArr[0];
					tagL = tagArr[1];
				}else{
					tagS = tagArr[1];
					tagL = tagArr[0];
				}
			}
			console.log(tagS)
			console.log(tagL)
			*/ 
			$.ajax({
				url: '${ pageContext.request.contextPath }/gathering/apply',
				type: 'post',
				data: {
					accountNo: accountNo,
					password: password,
					name: name,
					tag: tags
				},
				success: function(addr){
					console.log("신청폼 보내기");
					alert("준비해라");
					alert(addr);
					alert(addr.substr(1, 16));
					let accountNo = document.aForm.accountNo.value;
					let address = "${pageContext.request.contextPath}" + addr.substr(1, 16) + "/" + accountNo;
					location.href =  address;
				},error: function(){
					alert("신청폼을 전송하는 데 문제가 생겼습니다. 다시 시도해주시기 바랍니다.");
				}
			}) 
		}
		
		$(document).ready(function() {
			
		
		//비밀번호 일치 여부
		$('#applyBtn').click(function(){
			alert('!!!')
			let password = document.aForm.password.value;
			let accountNo = document.aForm.accountNo.value;
			var rtn = true; 
			$.ajax({
				url: '${ pageContext.request.contextPath }/account/checkPassword',
				type: 'post',
				data: {
					password: password,
					accountNo: accountNo
				},
				success: function(data){
					console.log("비밀번호 확인")
					data = JSON.parse(data);
					console.log("[" + data + "]");
					console.log("[" + data.trim() + "]");
					alert(typeof(data));
					if(data == 'no'){
						alert("no구간")
						alert("비밀번호가 일치하지 않습니다.")
						rtn = false; 
					}else if(data == 'yes'){
						alert("yes구간")
						submitForm();
					}
				},error: function(){
					alert("비밀번호 일치 여부를 확인하는 데 문제가 생겼습니다. 다시 시도해주시기 바랍니다.");
				}
			})
			return rtn;
		})
		})

	</script>  	
	
  </body>
</html>