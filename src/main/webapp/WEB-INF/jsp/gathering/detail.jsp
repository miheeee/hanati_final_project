<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 토글버튼 style -->
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
			  background-color: #2196F3;
			}
			
			input:focus + .slider {
			  box-shadow: 0 0 1px #2196F3;
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
		<!-- 토글버튼 부트스트랩 -->
		<script
		  src="https://code.jquery.com/jquery-3.5.1.min.js"
		  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		  crossorigin="anonymous"></script>

		<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
		<!-- iOS Style: Rounded -->		
		
		<script type="text/javascript">
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
		</script>
	<!-- 아이콘 -->
	<script src="https://kit.fontawesome.com/925e80bab6.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
	<jsp:include page="/WEB-INF/jsp/include/head.jsp" /> 
</head>
<body>
<!-- header BEGIN -->
	<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<!-- header END -->
    
  <section class=" section mb-5" style="  background-repeat: no-repeat;background-size: cover;background-image:url(${ pageContext.request.contextPath }/resources/img/coffe4.jpg)">
	<!-- <div class="slider-item" style="background-image: url('img/slider-1.jpg');"> -->
      
      <div class="container">
      <div class="row slider-text align-items-center justify-content-center mb-5 element-animate fadeInUp element-animated text-center">
            <div class="col-md-7 col-sm-12 element-animate">

              <h2 class="text-uppercase heading border-bottom mb-4" style="font-weight:600">${gathering.name}</h2>   
              <c:choose>
              <c:when test="${gathering.id = loginVO.id }">
              	<p class="mb-0 lead" style="color: #6c757d;">${fn:substring(gathering.accountNo, 0, 3)}-${fn:substring(gathering.accountNo, 3, 9)}-${fn:substring(gathering.accountNo, 9, 14)}</p>
              </c:when>
              <c:otherwise>
              	<p class="mb-0 lead" style="color: #6c757d;">${fn:substring(gathering.safeAccountNo, 0, 4)}-${fn:substring(gathering.safeAccountNo, 4, 6)}-${fn:substring(gathering.safeAccountNo, 6, 11)}</p>
              </c:otherwise>
              </c:choose>
			  <h2 class="text-uppercase heading mb-4"><fmt:formatNumber value="${gathering.balance}" pattern="#,###.##" />원</h2>
		
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
					   				<c:if test="${gathering.id = loginVO.id }">
					   					<a id="kakao-link-btn" href="javascript:sendLink()">초대</a>
							   		</c:if>
							   		<tr>
							   			<td>
										   	<span style="font-size: 2em; color: Tomato;">
											    <i class="fas fa-user-circle" style="color:black;"></i>
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
											    <i class="fas fa-user-circle" style="color:black;"></i>
											</span>${participant.name}
							   			</td>
							   			<td>
							   				<c:if test="${gathering.id = loginVO.id }">
							   					<input type="button" value="멤버끊기" id="${participant.id}" name="${participant.name}" class="expel btn btn-primary">
							   				</c:if>
							   			</td>
							   		</tr>					   			
					   			</c:otherwise>
				   			</c:choose>
				   		</c:forEach>


				   	</table>	
				  </div>
				</div>
			
			
						<!-- 스크롤 내비 -->
			<nav id="navbar-example2" class="navbar navbar-light d-flex justify-content-center" style="overflow: visible">
			  <ul class="nav nav-pills bg-light rounded-lg p-2">
			    <li class="nav-item">
			      <c:if test="${gathering.id = loginVO.id }"><a class="nav-link" href="${ pageContext.request.contextPath }/transaction/transfer/${gathering.safeAccountNo}">이체</a></c:if>
			    </li>
			    <li class="nav-item">
			      <a class="nav-link" href="#transaction">거래내역</a>
			    </li>
			    <li class="nav-item dropdown" style="overflow: visible">
			      <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">회비 관리</a>
			      <div class="dropdown-menu">
			        <a class="dropdown-item" href="#duesStatus">회비 입금 현황</a>
			        <c:if test="${gathering.id = loginVO.id }"><a class="dropdown-item" href="#" data-toggle="modal" data-target="#messageCard">메세지카드 보내기</a></c:if>
			        <a class="dropdown-item" href="${ pageContext.request.contextPath }/transaction/transfer/${gathering.safeAccountNo}">회비 임급</a>
			      </div>
			    </li>
			    <li class="nav-item">
			      <a class="nav-link" href="#settings">관리</a>
			    </li>
			  </ul>
			</nav>
			
			
				
        </div>
      </div>
      </div>
    </section>
    <!-- END slider -->
    
    <section class="section pt-0">
      <div class="container">
        <div class="row mb-5">

          <div class="col-md-12 order-lg-1">

			
			<!-- 스크롤에 해당하는 내용 -->
			<div data-spy="scroll" data-target="#navbar-example2" data-offset="0">
			  <h3 id="transaction" class="table-title">거래내역</h3>
			  	<div>
					<table class="table-bordered table info-table">
						<thead>	
							<tr>
								<th>일시</th>
								<th>거래 대상</th>
								<th>거래 구분</th>
								<th>금액</th>
								<th>거래 후 잔액</th>
							</tr>	
						</thead>
						<tbody>				
							<c:forEach items="${transactionList}" var="transaction" varStatus="loop">
								<tr>
									<td>${transaction.time}</td>
									<td>
										<c:choose>
											<c:when test="${not empty transaction.indication}">
												${transaction.indication}
											</c:when>
											<c:otherwise>
												${transaction.counterpart}
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${transaction.classification == '401'}">
												입금
											</c:when>
											<c:when test="${transaction.classification == '402'}">
												출금
											</c:when>
										</c:choose>
									</td>
									
										<c:choose>
											<c:when test="${transaction.classification == '401'}">
												<td class="text-right" style="color:#27b2a5;"> <fmt:formatNumber value="${transaction.amount}" pattern="+#,###.##" /></td>
											</c:when>
											<c:when test="${transaction.classification == '402'}">
												<td class="text-right" style="color:red;"> <fmt:formatNumber value="${transaction.amount}" pattern="-#,###.##" /></td>
											</c:when>
										</c:choose>					
									<td><fmt:formatNumber value="${transaction.balance}" pattern="#,###.##" /></td>
									<%-- <td>${transaction.memo}</td> --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>			  		
			  	</div>
			  <h3 id="duesStatus" class="table-title">회비 입금 현황</h3>
			  	<div>
					<!-- 탭 -->
					<ul class="nav nav-tabs" id="myTab" role="tablist">
					  <li class="nav-item">
					    <a class="nav-link active" id="period-tab" data-toggle="tab" href="#period" role="tab" aria-controls="period" aria-selected="true">기간별</a>
					  </li>
					  <li class="nav-item">
					    <a class="nav-link" id="memeber-tab" data-toggle="tab" href="#memeber" role="tab" aria-controls="memeber" aria-selected="false">멤버별</a>
					  </li>
					</ul>
					
					<!-- 콘텐츠 -->
					<div class="tab-content" id="myTabContent">
						<!-- 기간별 -->
						<div class="tab-pane fade show active " id="period" role="tabpanel" aria-labelledby="period-tab">
							<!-- 기간 목록 -->
							<select id="periodList" name="periodList">
								<c:forEach items="${monthList}" var="month" varStatus="loop">
									<c:choose>
										<c:when test="${loop.index == 0}">
											<option value="${month}" selected="selected">${month}</option>	
										</c:when>
										<c:otherwise>
											<option value="${month}">${month}</option>	
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>							
							<div id="dListByPeriod">
 								<table class="table info-table">
									<c:forEach items="${participantList}" var="participant">	
										<tr> <!-- class="duesDetail" name="" value="" -->
											<td>${participant.name}</td>
											<c:set var="cnt" value="0"/>
											<fmt:formatNumber value="${cnt}" type="number" var="cnt" />
											<c:set var="totalAmount" value="0"/>
											<fmt:formatNumber value="${totalAmount}" type="number" var="totalAmount" />
											
											<c:forEach items="${duesList}" var="dues">
												<c:set var="yearmonth" value="${fn:substring(dues.time, 0, 7)}"/>
												<c:if test="${participant.name == dues.paidMember  && yearmonth == date}">
													<c:set var="cnt" value="${cnt+1}"/>
													<c:set var="totalAmount" value="${totalAmount + dues.amount}"/>
												</c:if>
											</c:forEach>
												<td>${cnt}건</td>
												<td>${totalAmount}원</td>	
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
						<!-- 멤버별 -->
						<div class="tab-pane fade" id="memeber" role="tabpanel" aria-labelledby="memeber-tab">
							<!-- 멤버 목록 -->
								<c:forEach items="${participantList}" var="participant" varStatus="loop">
									<span class="Bymember" id="${$participant.name}">	<!--  value="${$participant.name}" -->
										<span style="font-size: 3em; color: black;">
											<i class="fas fa-user-circle Bymember" id="${participant.name}"></i>									
										</span>
										${participant.name}
									</span>
								</c:forEach>
								<!-- 회비 내역 -->
								<div id="dListByMemeber">
									<table class="table">
										<c:forEach items="${yearList}" var="year">
											<tr>
												<th>${year}</th>
												<c:set var="yearlAmount" value='0'/>
												<c:forEach items="${duesList}" var="dues">
													<c:if test="${fn:substring(dues.time, 0, 4) == fn:substring(year, 0, 4)}">
														<c:set var="yearlAmount" value="${yearlAmount + dues.amount}"/>
													</c:if>
												</c:forEach>
												<th>총 ${yearlAmount}원</th>	
											</tr>	
							
											<c:forEach var="i" begin="1" end="12" step="1">
												<fmt:formatNumber type="number" value="${13-i}" var="month" pattern="00"/>
												<c:set var="totalMonthlyAmount" value="0"/>
												<c:forEach items="${duesList}" var="dues">

													<c:if test="${dues.paidMember == participantList[0].name 
																		&& fn:substring(dues.time, 0, 4) == fn:substring(year, 0, 4) 
																			&& fn:substring(dues.time, 5, 7) == month}">
														<c:set var="totalMonthlyAmount" value="${totalMonthlyAmount + dues.amount}"/>
													</c:if>
												</c:forEach>
												<c:if test="${totalMonthlyAmount != 0}">
													<tr>
														<td>${13-i}월</td>
														<td>${totalMonthlyAmount}원</td>
													</tr> 	
												</c:if>
											</c:forEach>
																						
										</c:forEach>
									</table>
								</div>
						</div>
					</div>
			  	</div>
			  <h3 id="settings">관리</h3>
			  	<div>
			  			<!-- 알림 설정 -->
			  			<h5>알림</h5>
			  			<p>모임통장 알림을 개별적으로 설정할 수 있습니다.</p>
			  			<div>
							<div>
								<span>모임원 및 통장 변동 알림</span>
								 <label class="switch">
								  <input type="checkbox" id="accountChange">
								  <span class="slider round"></span>
								</label>
							</div>
							<div>
							입출금 알림
								<label class="switch">
								  <input type="checkbox" id="transfer">
								  <span class="slider round"></span>
								</label>
							</div>
							<div>	
								회비 입금일 정기알림
								<label class="switch">
								  <input type="checkbox" id="depositDate">
								  <span class="slider round"></span>
								</label>
							</div>
						</div>	
			  	</div>
			  	
			  	<div>
			  		<h5>모임통장 상태</h5>
			  		<c:choose>
				  		<c:when test="${empty scheduledEndVO}">
				  			<div id="terminateButtonDiv">
					  			<p>현재 모임통장은 사용 중입니다.</p>
					 			<c:if test="${gathering.id = loginVO.id }">
					 				<p>종료하시려면 아래 사용 종료 버튼을 눌러주새요.</p>
					 				<button id="terminate" class="terminateChangeBtn btn btn-primary button" onclick="terminate();">모임통장 사용 종료</button>
					 			</c:if>
					 		</div>
				 		</c:when>
				 		<c:otherwise>
				 			<div id="terminateButtonDiv">
					 			<p>모임통장은 ${scheduledEndVO.endDate}에 종료 예정입니다.
					 			<c:if test="${gathering.id = loginVO.id }"><button id="terminateCancel" type="button" class="terminateChangeBtn btn btn-primary button">종료 취소</button></c:if>
					 			<%-- <c:set var="days" value="${date-scheduledEndVO.endDate}"/>  종료 예정이 ~일 남았습니다. --%>	
					 		</div>
				 		</c:otherwise>
			  		</c:choose>
			  	</div>
			</div>



          </div>
          
        </div>
       </div>
      </section>   
    
  
    <section class="section">
    </section>

<!-- 메세지 카드 보내기 모달 -->
<!-- Modal -->
<div class="modal fade" id="messageCard" tabindex="-1" role="dialog" aria-labelledby="messageCardLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="messageCardLabel">메시지카드 보내기</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
     
      <div class="modal-body">
        	<p>카카오톡으로 멤버들에게 메시지카드를 보내보세요.</p>	
			<input type="radio" name="chk_message" value="requestDues">회비 요청
			<input type="radio" name="chk_message" value="shareStatus">현황 공유
			<div id="messageContent"></div>
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" onclick="sendMessage()">카카오톡 공유하기</button>
      </div>
    </div>
  </div>
</div>


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
    
    <!-- 회비 내역 조회 -->
    <script>
    //기간별 회비 입금 내역 조회 에이쟉스
	 $(document).ready(function(){
	        $('#periodList').change(function(){
	            $.ajax({
	            	url : '${ pageContext.request.contextPath }/dues/period',
	            	type : 'post',
	            	data : {
						accountNo : ${gathering.accountNo}, 
						time : $("#periodList option:selected").val()
	            	}, 
	            	success : function(data){   
						$('#dListByPeriod').empty
						$('#dListByPeriod').html(data);
	            	}, error : function(){
	            		alert('문제가 생겼습니다. 다시 시도해주세요.');
	            	}
	            }) 
	        })
		})  	
    
    
    //멤버별 회비 입금 내역 조회 에이쟉스
    $(document).ready(function(){
		$('.Bymember').click(function(){
			$.ajax({
				url: '${ pageContext.request.contextPath }/dues/member',
				type: 'post',
				data: {
					accountNo : ${gathering.accountNo}, 
					name : $(this).attr('id') 
				}, success: function(data){
					$('#dListByMemeber').empty
					$('#dListByMemeber').html(data);
				}, error: function(){
					alert('문제가 생겼습니다. 다시 시도해주세요.');
				}	
			})
		})
	})
    </script>
    
    
    <!-- 카카오톡 초대 -->
   	<!-- 웹 페이지에 JavaScript SDK 포함하기 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>	
	<script>
	<!-- javaScript 키를 할당하여 초기화 함수를 호출 -->
	Kakao.init('');		//자신의 appkey 삽입
	console.log(Kakao.isInitialized());
	</script>
	<script>
	  function sendLink() {
			let random = Math.random();
  			let authenticationNum = Math.floor( random * 9000 + 1000 )
  			let code = '${ gathering.safeAccountNo }' + authenticationNum
		    Kakao.Link.sendCustom({
		        templateId: 35968,
		        //url: /hanati/participant/invite/${param}		//param은 안심계좌번호
		        templateArgs: {
		          title:
		        	  '${holder}',
		          description:
		        	  '${gathering.name}',
		       		param:
		       			code	       		  
		        },	callback: function(){
		       		alert("초대 메세지가 전송되었습니다");
		       		location.href='${ pageContext.request.contextPath }/participant/authentication/' + code;
		       	}
		      })
	  	}
	  </script>
	
	<!-- 카카오톡 메세지 -->
	<script>
	$(document).ready(function(){
		$("input:radio[name=chk_message]").click(function(){
			if($("input[name=chk_message]:checked").val() == "requestDues"){
				let $img = $('<img src="${ pageContext.request.contextPath }/resources/img/10_MANANDGIRL.png" width="100%" height="30%">');
				let $input = $('<input type="text" id="requestDuesAmount">')
				$('#messageContent').empty();
				$('#messageContent').append($img);
				$('#messageContent').append("요청할 금액 : ");
				$('#messageContent').append($input);
				$('#messageContent').append("원");
			}else if($("input[name=chk_message]:checked").val() == "shareStatus"){
				let $img = $('<img src="${ pageContext.request.contextPath }/resources/img/6_BIRD.png" width="100%" height="30%">');
				$('#messageContent').empty();
				$('#messageContent').append($img);
			}	
		})
	})

	  function sendMessage() {
		//회비 요청
		if($("input[name=chk_message]:checked").val() == "requestDues"){	
			
		    Kakao.Link.sendCustom({
		        templateId: 36914,
		        templateArgs: {
		        	amount:
		        	  $('#requestDuesAmount').val(),
		        	safeAccountNo:
		        	  '${gathering.safeAccountNo}',
		        	  name:
		        	   '${gathering.name}',	       		  
		        	   holder:
		        		'${holder}'
		        },	callback: function(){
		       		alert("메세지카드가 전송되었습니다");
		       	}
		      })    
		//현황 공유	
		}else if($("input[name=chk_message]:checked").val() == "shareStatus"){
			
		    Kakao.Link.sendCustom({
		        templateId: 36915,
		        templateArgs: {
		        	dues:
							'',
		        	  balance:
		        	  	'${gathering.balance}',
		        	  count:
							'',
		       			name:
		       				'${gathering.name}',
		       			safeAccountNo:
		       				'${gathering.safeAccountNo}'
		        },	callback: function(){
		       		alert("초대 메세지가 전송되었습니다");
		       		location.href='${ pageContext.request.contextPath }/participant/authentication/' + code;
		       	}
		      })
			
		}
	}
	</script>
	
	
	
	<!-- 알림 -->  
	<!-- 알림 토글 설정 -->
  	<script type="text/javascript">
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
		  
		  
		<!-- 모임통장 종료 -->
		    function terminate(){
			        	$.ajax({
			            	url : '${ pageContext.request.contextPath }/gathering/terminate',
			            	type : 'post',
			            	data : {
			            		accountNo : ${gathering.accountNo},
			            		name : '${gathering.name}', 
			     				safeAccountNo : ${gathering.safeAccountNo}
			            	},
			            	success : function(){
			            		alert('모임통장이 사용 종료 신청이 완료되었습니다. 3일 후 모든 정보가 삭제됩니다. 취소하시려면 종료 취소 버튼을 눌러주세요.');
			            		location.reload();
			            		
			            	}, error : function(){
			            		alert('문제가 생겼습니다. 다시 시도해주세요.');
			            	}
			            })
		        	}
		    
		 <!-- 모임통장 종료 취소 -->
		$(document).ready(function(){
		    $('#terminateCancel').click(function(){
			        	$.ajax({
			            	url : '${ pageContext.request.contextPath }/gathering/terminateCancel',
			            	type : 'post',
			            	data : {
			            		accountNo : ${gathering.accountNo},
			            	},
			            	success : function(){
			            		alert('모임통장 사용 종료 신청이 취소되었습니다. 계속 사용하실 수 있습니다.');
			            		location.reload();
	 		            		let str = '모임통장 사용이 종료될 예정입니다. ' + '<br> <button id="terminateCancel" type="button" class="terminateChangeBtn btn btn-primary button">종료 취소</button>';			           
			            		$('#terminateButtonDiv').empty;
			            		$('#terminateButtonDiv').html(data); 			       		
			            	}, error : function(){
			            		alert('문제가 생겼습니다. 다시 시도해주세요.');
			            	}
			            })
		        	})
		    }) 
		    
 		<!-- 모임통장 상태 reload -->
/* 		function gatheringStatusReload(){

        	$.ajax({
            	url : '${ pageContext.request.contextPath }/gathering/gatheringStatus',
            	type : 'post',
            	data : {
            		accountNo : ${gathering.accountNo},
            	},
            	success : function(){
            		alert("리로드합니다");           		
            	}, error : function(){
            		alert('문제가 생겼습니다. 다시 시도해주세요.');
            	}
            })			
		}	 */
		</script>
		
		<script>
			<!-- 모임원 조회 -->
/*   			function selectParticipant(){
				$.ajax({
	            	url : '${ pageContext.request.contextPath }/participant/select',
	            	type : 'post',
	            	data : {
	            		safeAccountNo : ${gathering.safeAccountNo},
	            	},
	            	success : function(data){
	            		alert('야호')
	            		alert(data);   
	            		$('#participantListDiv').empty();
	            		$('#participantListDiv').html(data);            		
	            	}, error : function(){
	            		alert('문제가 생겼습니다. 다시 시도해주세요.');
	            	}
				})
			}  */
			
			<!-- 멤버 내보내기 -->
			$(document).ready(function(){
			    $('.expel').click(function(){
			    	let name = $(this).attr('name');		
		        	$.ajax({
		            	url : '${ pageContext.request.contextPath }/participant/expel',
		            	type : 'post',
		            	data : {
		            		id : $(this).attr('id'),
		            		accountNo : ${gathering.accountNo},
		            		safeAccountNo : ${gathering.safeAccountNo},
		            	}, 
		            	success : function(){
		            		alert(name + '님을 내보냈습니다.');
		            		location.reload();
		            	}, error : function(){
		            		alert('문제가 생겼습니다. 다시 시도해주세요.');
		            	}
		            })
	        	})
		}) 
			
			
		</script>
		
		

    <script src="${ pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/popper.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/owl.carousel.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/jquery.waypoints.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/main.js"></script>
  </body>
</html>
