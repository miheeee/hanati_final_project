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
        <div class="row justify-content-center">
          <div class="col-md-8">
            <form action="${ pageContext.request.contextPath }/transaction/transfer" method="post">

				  <h3 id="transaction" class="table-title">출금정보</h3>
				  	<div>	
						<%-- 어떤 url로 요청이 들어왔는지에 따라 보여주는 형태 달라짐 --%>
						<c:set var="path" value="${requestScope['javax.servlet.forward.servlet_path']}"/> 				  			
				  		<table class="transferForm">			  		
				  			<tr>
				  				<th>
				  					하나은행 출금계좌<em class="aste">*<span>필수</span></em>
				  				</th>
				  				<td>
				  				
									<select name="accountNo" id="accountNo"  class="form-control form-control-lg">
										<!-- 일반 입출금 통장 목록 -->
										<c:forEach items="${ accountList }" var="account" varStatus="loop">
										  <option value="${account.accountNo}">
										 	${fn:substring(account.accountNo, 0, 3)}-${fn:substring(account.accountNo, 3, 9)}-${fn:substring(account.accountNo, 9, 14)} : 				  				
							  				${ account.productName }&nbsp;
										  	잔액 <fmt:formatNumber value="${account.balance}" pattern="#,###.##" />원
										  </option>
										</c:forEach>  
										<!-- 모임주로 있는 모임통장 목록  -->
										<c:forEach items="${ gatheringList }" var="gathering" varStatus="loop">
											<c:choose>
												<c:when test="${fn:substring(path, 0, fn:length(path) - 11) == '/transaction/transfer/' 
													&&	gathering.safeAccountNo == safeAccountNo}">								
													<option value="${ gathering.accountNo }" selected="selected">
													  	${fn:substring(gathering.safeAccountNo, 0, 4)}-${fn:substring(gathering.safeAccountNo, 4, 6)}-${fn:substring(gathering.safeAccountNo, 6, 11)} : 
													  	${ gathering.name }&nbsp;
													  	잔액<fmt:formatNumber value="${gathering.balance}" pattern="#,###.##" />원
												  	</option>
												</c:when>
												<c:otherwise>
													<option value="${ gathering.accountNo }">
													  	${fn:substring(gathering.safeAccountNo, 0, 4)}-${fn:substring(gathering.safeAccountNo, 4, 6)}-${fn:substring(gathering.safeAccountNo, 6, 11)} : 
													  	${ gathering.name }&nbsp;
													  	잔액 <fmt:formatNumber value="${gathering.balance}" pattern="#,###.##" />원
												  	</option>
												</c:otherwise>
											</c:choose>
										</c:forEach> 	
									</select>				  					
				  				</td>
				  			</tr>
				  			<tr>
				  				<th>
				  					계좌 비밀번호<em class="aste">*<span>필수</span></em>
				  				</th>
				  				<td><input type="password" name="password" placeholder="비밀번호 4자리를 입력하세요." style="width:345px;" class="form-control form-control-lg"></td>
				  			</tr>				 
				  			<tr>
				  				<th>
				  					이체금액<em class="aste">*<span>필수</span></em>
				  				</th>
				  				<td>
				  					<input type="text" name="amount" id="amount" placeholder="0" class="form-control form-control-lg" style="width:448px;text-align:right">
				  				</td>
				  			</tr>
				  		</table>
				  	</div>
				  				
				  <h3 id="transaction" class="table-title">입금정보</h3>
				  	<div>			
				  		<table class="hTable transferForm">
				  			<tr>
				  				<th>
				  					입금은행<em class="aste">*<span>필수</span></em>
				  				</th>
				  				<td>
									<select name="counterpartBank" id="counterpartBank" class="form-control form-control-lg" style="width:345px;">
										<c:forEach items="${ bankList }" var="bank" varStatus="loop">
											<c:choose>
												<c:when test="${bank.code == '081'}">
													<option value="${ bank.code }" selected="selected">${ bank.codeName }</option>
												</c:when>
												<c:otherwise>
													<option value="${ bank.code }">${ bank.codeName }</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>  
									</select>					  				
				  				</td>
							</tr>
							<tr>
								<th>
									입금 계좌번호<em class="aste">*<span>필수</span></em>
								</th>
								<td>
									<c:choose>	
									<%-- 회비를 입금하는 경우에는 --%>
									<c:when test="${fn:substring(path, 0, fn:length(path) - 11) == '/transaction/transfer/dues/'}">
										<input type="text" name="counterpartAccountNo" value="${safeAccountNo}" class="form-control form-control-lg">			
									</c:when>
										<%-- 회비를 입금하는 경우를 제외하고 --%>
										<c:otherwise>						
											<input type="text" name="counterpartAccountNo" placeholder="‘-’없이 계좌번호를 입력하세요." class="form-control form-control-lg">		
										</c:otherwise>
									</c:choose>								
								</td>								
							</tr>
							<tr>
								<th>받는분 통장 표시</th>
								<td>
									<input type="text" name="indication" id="indication" placeholder="최대 30자까지 입력 가능합니다." class="form-control form-control-lg" cols="30" rows="8">
								</td>
							</tr>							
				  		</table>
				  	</div>				  				
				  				
				  	<div class="d-flex jusify-content-center"><input type="submit" value="이체하기 " class="btn btn-primary transferFormSubmit" style="margin-left:auto;margin-right:2px">
				  		<button type="button" class="btn btn-primary button transferFormSubmit" onclick = "location.href = '${ pageContext.request.contextPath }/' " style="margin-left:2px;margin-right:auto;background-color:#6e7277;border-color:#6e7277;">취소</button>
				  	</div>			  		
          	</form>
          </div>    
       </div>
      </div>
    </section>


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