<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
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
              <h1 class="text-white">모임통장 전환</h1>
            </div>
          </div>
        </div>

      </div>
    </section>
    
    <section class="section">
      <div class="container">
        <div class="row mb-5 justify-content-center">
          <div class="col-md-9 order-lg-1"> 
	
	
	
		<h3 id="transaction" class="table-title">모임통장 전환</h3>			
				  <div>			
				  	<form method="post" name="aForm">
				  		<table class="transferForm">
				  			<tr>
				  				<th style="width:300px;">모임통장으로 전환할 계좌</th>
				  				<td>
									<select name="accountNo" class="custom-select mb-2 form-control form-control-lg" id="validationCustom01" required>
							        	<c:forEach items="${ accountList }" var="account" varStatus="loop">
							        		<option value="${ account.accountNo }">${fn:substring(account.accountNo, 0, 3)}-${fn:substring(account.accountNo, 3, 9)}-${fn:substring(account.accountNo, 9, 14)} ${ account.productName }
							        			&nbsp;잔액 <fmt:formatNumber value="${account.balance}" pattern="#,###.##" />원
							        		</option>							      		
							      		</c:forEach>
							     	</select>	
							     	<br>	
							     	<p>※모임통장으로 이용불가능 입출금 계좌</p>
							     	<p>: 대출 이용 계좌, 비활성 계좌, 거래제한 계좌</p>	  					
				  				</td>
				  			</tr>
				  			<tr>
				  				<th>
				  					계좌 비밀번호
				  				</th>
				  				<td><input type="password" name="password" placeholder="비밀번호 4자리를 입력하세요." style="width:345px;" class="form-control form-control-lg"></td>
				  			</tr>				 
				  			<tr>
				  				<th>모임통장 이름</th>
				  				<td>
							      <input type="text" name="name" class="form-control  form-control-lg" id="validationCustom03" 
							      			placeholder="멤버들과 함께 볼 계좌 이름을 지어주세요." required>
				  				</td>
				  			</tr>
				  			<tr>
				  				<th>모임태그(최대 2개)</th>
				  				<td>
									<input type="checkbox" id="tag1" name="tag" value="1">
									<label for="vehicle1">가족/생활비<img src="${ pageContext.request.contextPath }/resources/img/tag/1.svg" class="ml-1"></label><br>
									<input type="checkbox" id="tag2" name="tag" value="2">
									<label for="vehicle2">데이트<img src="${ pageContext.request.contextPath }/resources/img/tag/2.svg" class="ml-1"></label><br>
									<input type="checkbox" id="tag3" name="tag" value="3">
									<label for="vehicle3">팬모임<img src="${ pageContext.request.contextPath }/resources/img/tag/3.svg" class="ml-1"></label><br>
									<input type="checkbox" id="tag4" name="tag" value="4">
									<label for="vehicle3">공연/전시<img src="${ pageContext.request.contextPath }/resources/img/tag/4.svg" class="ml-1"></label><br>
									<input type="checkbox" id="tag5" name="tag" value="5">
									<label for="vehicle3">레저/스포츠<img src="${ pageContext.request.contextPath }/resources/img/tag/5.svg" class="ml-1"></label><br>
									<input type="checkbox" id="tag6" name="tag" value="6">
									<label for="vehicle3">여행<img src="${ pageContext.request.contextPath }/resources/img/tag/6.svg" class="ml-1"></label><br>
									<input type="checkbox" id="tag7" name="tag" value="7">
									<label for="vehicle3">학교/동아리<img src="${ pageContext.request.contextPath }/resources/img/tag/7.svg" class="ml-1"></label><br>
									<input type="checkbox" id="tag8" name="tag" value="8">
									<label for="vehicle3">회사/팀<img src="${ pageContext.request.contextPath }/resources/img/tag/8.svg" class="ml-1"></label><br>
									<input type="checkbox" id="tag9" name="tag" value="9">
									<label for="vehicle3">스터디<img src="${ pageContext.request.contextPath }/resources/img/tag/9.svg" class="ml-1"></label><br>
									<input type="checkbox" id="tag10" name="tag" value="10">
									<label for="vehicle3">친목<img src="${ pageContext.request.contextPath }/resources/img/tag/10.svg" class="ml-1"></label><br>	
				  				</td>
				  			</tr>	
				  			<tr>
				  				<th>모임통장 이용약관</th>
				  				<td>
							      <input type="checkbox" value="" id="invalidCheck" required
							      				data-toggle="modal" data-target="#myModal">
							      <label>본인은 위 안내에 대해 확인하고 이해합니다.</label> 
				  				</td>
				  			</tr>				  						  			
				  		</table>
					  	<div class="d-flex jusify-content-center">
					  		<button type="button" class="btn btn-primary button transferFormSubmit" id="applyBtn" style="margin-left:auto;margin-right:2px">확인</button>
					  		<button type="button" class="btn btn-primary button transferFormSubmit" onclick = "location.href = '${ pageContext.request.contextPath }/' " style="margin-left:2px;margin-right:auto;background-color:#6e7277;border-color:#6e7277;">취소</button>
					  	</div>
				  	</form>
				  </div>  	

	
	
			
		  </div>
		</div>				
      </div>
    </section>


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
	          <h3 class="modal-title">모임통장 특약</h3>
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
	

	<script>

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
/* 					alert("준비해라");
					alert(addr);
					alert(addr.substr(1, 16));*/
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
/* 					alert(typeof(data)); */
					if(data == 'no'){
						alert("비밀번호가 일치하지 않습니다.")
						rtn = false; 
					}else if(data == 'yes'){
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