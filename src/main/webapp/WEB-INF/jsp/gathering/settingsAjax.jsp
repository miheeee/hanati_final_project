<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<style>
  .toggle.ios, .toggle-on.ios, .toggle-off.ios { border-radius: 20rem !important;} 
  .toggle.ios .toggle-handle { border-radius: 20rem !important;}
  input[type="checkbox"]{
     -webkit-appearance:none;  /* Safari and Chrome */
     appearance:none;  /* 화살표 없애기 공통*/
  }
</style>
<style>
	th{
	background-color: /* #008375 */  #f6f6f6 ;
	}
	.switch {
	  position: relative;
	  display: inline-block;
	  width: 60px;
	  height: 34px;
	}
	
	.switch input { 
	  opacity: 0;
	  width: 0;
	  height: 0;
	}
	
	.slider {
	  position: absolute;
	  cursor: pointer;
	  top: 0;
	  left: 0;
	  right: 0;
	  bottom: 0;
	  background-color: #ccc;
	  -webkit-transition: .4s;
	  transition: .4s;
	}
	
	.slider:before {
	  position: absolute;
	  content: "";
	  height: 26px;
	  width: 26px;
	  left: 4px;
	  bottom: 4px;
	  background-color: white;
	  -webkit-transition: .4s;
	  transition: .4s;
	}
	
	input:checked + .slider {
	  background-color: #29cbbc;;
	}
	
	input:focus + .slider {
	  box-shadow: 0 0 1px #29cbbc;;
	}
	
	input:checked + .slider:before {
	  -webkit-transform: translateX(26px);
	  -ms-transform: translateX(26px);
	  transform: translateX(26px);
	}
	
	/* Rounded sliders */
	.slider.round {
	  border-radius: 34px;
	}
	
	.slider.round:before {
	  border-radius: 50%;
	}
</style>

<script src="${ pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
    
<!-- 토글버튼 부트스트랩 -->
<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
<!-- iOS Style: Rounded -->	

<!-- 알림 -->  			    
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
<script type="text/javascript">
	<%-- 알림 토글 설정 --%>
	$(document).ready(function(){		
		if(${participantVO.accountChange eq 'Y'}){
	   		$('#accountChange').attr("checked", "checked");
	   	}
	   	if(${participantVO.transfer eq 'Y'}){
	   		$('#transfer').attr("checked", "checked");
	   	}
	   	if(${participantVO.depositDate eq 'Y'}){
	   		$('#depositDate').attr("checked", "checked");
	   	}		
	}) 

		$(document).ready(function(){	
		    $('#accountChange').change(function(){
		    	
		        if($('#accountChange').is(":checked")){
		            $.ajax({
		            	url : '${ pageContext.request.contextPath }/participant/notify/accountChange',
		            	type : 'post',
		            	data : {
		            		accountNo : ${gathering.accountNo},
		            		accountChange: 'Y'
		            	},
		            	success : function(){
		            		alert('모임원 및 통장 변동 알림이 설정되었습니다.')
		            	}, error : function(){
		            		alert('문제가 생겼습니다. 다시 시도해주세요.')
		            	}
		            })
		        }else{
		            $.ajax({
		            	url : '${ pageContext.request.contextPath }/participant/notify/accountChange',
		            	type : 'post',
		            	data : {
		            		accountNo : ${gathering.accountNo},
		            		accountChange: "N",
		            	},
		            	success : function(){
		            		alert('모임원 및 통장 변동 알림이 해제되었습니다.')
		            	}, error : function(){
		            		alert('문제가 생겼습니다. 다시 시도해주세요.')
		            	}
		            })
		        }
		    })
		})
		
		 $(document).ready(function(){
		    $('#transfer').change(function(){
		        if($('#transfer').is(":checked")){
		        	$.ajax({
		            	url : '${ pageContext.request.contextPath }/participant/notify/transferNotiChange',
		            	type : 'post',
		            	data : {
		            		accountNo : ${gathering.accountNo},
		            		transfer: 'Y'
		            	},
		            	success : function(){
		            		alert('입출금 알림이 설정되었습니다.')
		            	}, error : function(){
		            		alert('문제가 생겼습니다. 다시 시도해주세요.')
		            	}
		            })
		        }else{
		        	$.ajax({
		            	url : '${ pageContext.request.contextPath }/participant/notify/transferNotiChange',
		            	type : 'post',
		            	data : {
		            		accountNo : ${gathering.accountNo},
		            		transfer: 'N'
		            	},
		            	success : function(){
		            		alert('입출금 알림이 해제되었습니다.')
		            	}, error : function(){
		            		alert('문제가 생겼습니다. 다시 시도해주세요.')
		            	}
		            })
		        }
		    })
		 })
		 
	 	  $(document).ready(function(){
		    $('#depositDate').change(function(){
		        if($('#depositDate').is(":checked")){
		        	var regularDay = prompt("알림을 보낼 회비 입금일을 입력하세요"+"");
		        	if(regularDay !== null){
			        	$.ajax({
			            	url : '${ pageContext.request.contextPath }/participant/notify/depositDateNotiChangeY',
			            	type : 'post',
			            	data : {
			            		accountNo : ${gathering.accountNo},
			            		depositDate: 'Y',
			            		regularDay : regularDay
			            	},
			            	success : function(){
			            		alert('회비 입금일 정기알림이 설정되었습니다.');
			            	}, error : function(){
			            		alert('문제가 생겼습니다. 다시 시도해주세요.');
			            	}
			            })
		        	}else{
		        		alert("잘못된 입력입니다.");
		        	}
		        }else{
		        	$.ajax({
		            	url : '${ pageContext.request.contextPath }/participant/notify/depositDateNotiChangeN',
		            	type : 'post',
		            	data : {
		            		accountNo : ${gathering.accountNo},
		            		depositDate: 'N'
		            	},
		            	success : function(){
		            		alert('회비 입금일 정기알림이 해제되었습니다.');
		            	}, error : function(){
		            		alert('문제가 생겼습니다. 다시 시도해주세요.');
		            	}
		            })
		        }
		    })
		  }) 
</script>

<!-- 모임통장 사용/종료 설정 -->
<script>	  
	<%-- 모임통장 종료 --%>			<%-- 동적으로 생성한 버튼에는 이벤트 핸들러가 잘 안 먹기 때문에 반드시 다음과 같은 문법을 사용해야 함 --%>
	$(document).ready(function(){
		$(document).on("click","#terminateBtn",function(){
        	$.ajax({
            	url : '${ pageContext.request.contextPath }/gathering/terminate',
            	type : 'post',
            	data : {
            		accountNo : ${gathering.accountNo},
            		name : '${gathering.name}', 
     				safeAccountNo : ${gathering.safeAccountNo}, 
     				id : '${gathering.id}' 
            	},   
            	success : function(data){
            		$('#terminateChangeBtnDiv').empty();
            		$('#terminateChangeBtnDiv').html(data);
            		alert('모임통장이 사용 종료 신청이 완료되었습니다. 3일 후 모든 정보가 삭제됩니다. 취소하시려면 종료 취소 버튼을 눌러주세요.');
            		
            	}, error : function(){
            		alert('문제가 생겼습니다. 다시 시도해주세요.');
            	}
            })
	    })
	})
	 
	
	 <!-- 모임통장 종료 취소 --> 
	$(document).ready(function(){
		$(document).on("click","#terminateCancelBtn",function(){
        	$.ajax({
            	url : '${ pageContext.request.contextPath }/gathering/terminateCancel',
            	type : 'post',
            	data : {
            		accountNo : ${gathering.accountNo},
            		id : '${gathering.id}'
            	},
            	success : function(data){	           
            		$('#terminateChangeBtnDiv').empty;
            		$('#terminateChangeBtnDiv').html(data); 			       		
            	}, error : function(){
            		alert('문제가 생겼습니다. 다시 시도해주세요.');
            	}
            })
        })
    })  
</script>


  	<div class="mb-5 col-md-7" style="margin-left: auto;margin-right: auto;">
  		<!-- 알림 설정 -->
  		<div>
  			<h3 id="transaction" class="table-title">알림</h3>
  			<ul class="list-unstyled check">
  				<li>모임통장 알림을 개별적으로 설정할 수 있습니다.</li>
  			</ul>
  			<!-- <p></p> -->
  			<table class="table">
  				<tr>
  					<td>모임원 및 통장 변동 알림</td> 					
  					<td>					 
	  					<label class="switch">
						  <input type="checkbox" id="accountChange">
						  <span class="slider round"></span>
						</label>
					</td> 					
  				</tr>
  				<tr>
  					<td>입출금 알림</td> 					
  					<td>					 
	  					<label class="switch">
						  <input type="checkbox" id="accountChange">
						  <span class="slider round"></span>
						</label>
					</td> 					
  				</tr>
  				<tr>
  					<td>회비 입금일 정기알림</td> 					
  					<td>					 
	  					<label class="switch">
						  <input type="checkbox" id="accountChange">
						  <span class="slider round"></span>
						</label>
					</td> 					
  				</tr>  		
  			</table>
			</div>	
  	</div>
  	
  	<div class="pt-5 col-md-7" style="margin-left: auto;margin-right: auto;">
 		<h3 id="transaction" class="table-title">모임통장 사용</h3>
  		<div id="terminateChangeBtnDiv">
	  		<c:choose>
		  		<c:when test="${empty scheduledEndVO}">
				  	<ul class="list-unstyled check">
		  				<li>현재 모임통장은 사용 중입니다.</li>
		 			<c:if test="${gathering.id == loginVO.id }">
		 				<li>종료하시려면 아래 사용 종료 버튼을 눌러주세요.</li>
		 				<button id="terminateBtn" class="terminateChangeBtn btn btn-primary button mt-3">모임통장 사용 종료</button>
		 			</c:if>
		 			</ul>
		 		</c:when>
		 		<c:otherwise>
				  	<ul class="list-unstyled check">
		  				<li>모임통장은 ${scheduledEndVO.endDate}에 종료 예정입니다.</li>		 		
			 			<c:if test="${gathering.id == loginVO.id }"><button id="terminateCancelBtn" type="button" class="terminateChangeBtn btn btn-primary button  mt-3">종료 취소</button></c:if>	
		 			</ul>
		 		</c:otherwise>
	  		</c:choose>
  		</div>
	</div>

