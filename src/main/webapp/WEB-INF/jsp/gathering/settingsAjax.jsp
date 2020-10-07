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
	.btn-27b2a5 {
	  color: #fff;
	  background-color: #27b2a5;
	  border-color: #27b2a5; 
	}
/* 	.modal-header{
		background-color: #27b2a5;
		border-bottom: 3px solid #27b2a5;
	} */
</style>

<script src="${ pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>

<!-- 토글버튼 부트스트랩 -->
<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
<!-- iOS Style: Rounded -->	

<!-- 알림 -->  			    
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    
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
		            		$("#normalBody p").html('모임원 및 통장 변동 알림이 설정되었습니다.');
		            		$("#normal").modal('show');
		            	}, error : function(){
		            		$("#error").modal('show');		            		
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
		            		$("#normalBody p").html('모임원 및 통장 변동 알림이 해제되었습니다.');
		            		$("#normal").modal('show');		            		
		            	}, error : function(){
		            		$("#error").modal('show');			            		
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
		            		$("#normalBody p").html('입출금 알림이 설정되었습니다.');
		            		$("#normal").modal('show');		            		
		            	}, error : function(){
		            		$("#error").modal('show');	
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
		            		$("#normalBody p").html('입출금 알림이 해제되었습니다.');
		            		$("#normal").modal('show');			            		
		            	}, error : function(){
		            		$("#error").modal('show');	
		            	}
		            })
		        }
		    })
		 })
		 
	 	  $(document).ready(function(){
		    $('#depositDate').change(function(){
		        if($('#depositDate').is(":checked")){
		        	/* var regularDay = prompt("알림을 보낼 회비 입금일을 입력하세요"+""); */
		        	$('#forDepositDateInput').modal('show');
		        	
		        	$("#oBtnForDepositDateInput").click(function(){
			        	$.ajax({
			            	url : '${ pageContext.request.contextPath }/participant/notify/depositDateNotiChangeY',
			            	type : 'post',
			            	data : {
			            		accountNo : ${gathering.accountNo},
			            		depositDate: 'Y',
			            		regularDay : $('#regularDay').val()
			            	},
			            	success : function(){
			            		$("#normalBody p").html('회비 입금일 정기알림이 설정되었습니다.');
			            		$("#normal").modal('show');	
			            	}, error : function(){
			            		/* alert('문제가 생겼습니다. 다시 시도해주세요.'); */
			            	}
			            })
			            $('#depositDate').prop("checked", true);
			        	$('#forDepositDateInput').modal('hide');
			            return false;
			        })
		    	   	$('#depositDate').prop("checked", false);
		        }else{ 
		        	$.ajax({
		            	url : '${ pageContext.request.contextPath }/participant/notify/depositDateNotiChangeN',
		            	type : 'post',
		            	data : {
		            		accountNo : ${gathering.accountNo},
		            		depositDate: 'N'
		            	},
		            	success : function(){
		            		$("#normalBody p").html('회비 입금일 정기알림이 해제되었습니다.');
		            		$("#normal").modal('show');			            		
		            	}, error : function(){
		            		$("#error").modal('show');	
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
            		
            		$("#normalBody p").html('모임통장이 사용 종료 신청이 완료되었습니다.<br>3일 후 모든 정보가 삭제됩니다.<br>취소하시려면 종료 취소 버튼을 눌러주세요.');
            		$("#normal").modal('show');		            		
            		
            	}, error : function(){
            		$("#error").modal('show');		
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
            		$("#error").modal('show');	
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
						  <input type="checkbox" id="transfer">
						  <span class="slider round"></span>
						</label>
					</td> 					
  				</tr>
  				<tr>
  					<td>회비 입금일 정기알림</td> 					
  					<td>					 
	  					<label class="switch">
						  <input type="checkbox" id="depositDate">
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
		 				<button id="terminateBtn" class="terminateChangeBtn btn btn-primary button mt-3" style="border-color:#27B2A5;background-color:#27B2A5;">모임통장 사용 종료</button>
		 			</c:if>
		 			</ul>
		 		</c:when>
		 		<c:otherwise>
				  	<ul class="list-unstyled check">
		  				<li>모임통장은 ${scheduledEndVO.endDate}에 종료 예정입니다.</li>		 		
			 			<c:if test="${gathering.id == loginVO.id }"><button id="terminateCancelBtn" type="button" class="terminateChangeBtn btn btn-primary button  mt-3" style="border-color:#27B2A5;background-color:#27B2A5;">종료 취소</button></c:if>	
		 			</ul>
		 		</c:otherwise>
	  		</c:choose>
  		</div>
	</div>

	<!-- !!!!모달!!!! -->
	
	<!-- 회비 입금일 입력 받기 -->
	<div class="modal" tabindex="-1" id="forDepositDateInput">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">정기적으로 알림을 받을 날짜를 선택해주세요</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body text-center">
	        <select id="regularDay" name="regularDay" style="width:80px;">
	        	<c:forEach var="i" begin="1" end="31">
	        		<option>${i}</option>
	        	</c:forEach>
	        </select>
	        	일
	      </div>
	      <div class="modal-footer">
	        <button id="xBtnForDepositDateInput" type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
	        <button id="oBtnForDepositDateInput" type="button" class="btn btn-primary" style="border-color:#27B2A5;background-color:#27B2A5;">확인</button>
	      </div>
	    </div>
	  </div>
	</div>	
	
	<!-- 공통 모달(알림 설정 확인 메세지) -->
	<div class="modal" tabindex="-1" id="normal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 id="normalTitle" class="modal-title">변경 완료</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div id="normalBody" class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal" style="border-color:#27B2A5;background-color:#27B2A5;">확인</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 에러 발생 모달 -->
	<div class="modal" tabindex="-1" id="error">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">문제가 발생하였습니다.</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div id="normalBody" class="modal-body">
	        <p>다시 시도해주세요.</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal" style="border-color:#27B2A5;background-color:#27B2A5;">확인</button>
	      </div>
	    </div>
	  </div>
	</div>	