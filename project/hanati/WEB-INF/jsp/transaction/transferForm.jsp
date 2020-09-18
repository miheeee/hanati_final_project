<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
              <h1 class="text-white">이체</h1>
            </div>
          </div>
        </div>

      </div>
    </section>
    
    <section class="section">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-6">
            <form action="${ pageContext.request.contextPath }/transaction/transfer" method="post">
              
				<div class="row">
					<div class="col-md-12 form-group">
						<label for="counterpartBank">받는 분 정보</label>
						<select name="counterpartBank" id="counterpartBank" class="form-control form-control-lg">
							<c:forEach items="${ bankList }" var="bank" varStatus="loop">
								<option value="${ bank.code }">${ bank.codeName }</option>
							</c:forEach>  
						</select>
						<input type="text" name="counterpartAccountNo" placeholder="[-]없이 입력" class="form-control form-control-lg">
                	</div>
		 		</div>
		 
				<div class="row">
					<div class="col-md-12 form-group">
						<label for="amountl">금액</label>
						<input type="text" name="amount" id="amount" placeholder="0" class="form-control form-control-lg">
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 form-group">
						<label for="accountNo">출금계좌</label>
						<select name="accountNo" id="accountNo"  class="form-control form-control-lg">
							<c:forEach items="${ gatheringList }" var="gathering" varStatus="loop">
								<c:choose>									
									<c:when test="${gathering.safeAccountNo == safeAccountNo}">
										<option value="${ gathering.accountNo }" selected="selected">
										  	${ gathering.accountNo } : 
										  	${ gathering.name } &nbsp;&nbsp;&nbsp;&nbsp;
										  	잔액 ${ gathering.balance }원
									  	</option>
									</c:when>
									<c:otherwise>
										<option value="${ gathering.accountNo }">
										  	${ gathering.accountNo } : 
										  	${ gathering.name } &nbsp;&nbsp;&nbsp;&nbsp;
										  	잔액 ${ gathering.balance }원
									  	</option>
									</c:otherwise>
								</c:choose>	 
							</c:forEach> 
							
							<c:forEach items="${ accountList }" var="account" varStatus="loop">
							  <option value="${ account.accountNo }">
							 	${ account.accountNo } : 				  				
				  				${ account.productName } &nbsp;&nbsp;&nbsp;&nbsp;
							  	잔액 ${ account.balance }원
							  </option>
							</c:forEach>  
						</select>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12 form-group">
                  <label for="indication">받는 통장 표시</label>
                  <input type="text" name="indication" id="indication" class="form-control form-control-lg" cols="30" rows="8">
                </div>
              </div>

              <div class="row">
                <div class="col-md-12 form-group">
                  <input type="submit" value="이체" class="btn btn-primary btn-lg btn-block">
                </div>
              </div>
            </form>
          
          </div>    
       </div>
      </div>
    </section>
    
<%--     <section class="section">
      <div class="container">
        <div class="row mb-5">
          
          <div class="col-md-12 order-lg-3 mb-5 form-group">
            
            
				<form action="${ pageContext.request.contextPath }/transaction/transfer" method="post">
					<div>
						<p>받는 분</p>
						<select name="counterpartBank" id="counterpartBank">
							<c:forEach items="${ bankList }" var="bank" varStatus="loop">
							  <option value="${ bank.code }">${ bank.codeName }</option>
							</c:forEach>  
						</select>
						<input type="text" name="counterpartAccountNo" placeholder="[-]없이 입력">
					</div>
					<div class="col-12">
					</div>
					<div>
						<p>금액</p>
						<input type="text" name="amount" placeholder="0">원
					</div>
					<div class="col-12">
					</div>
					<div>
						<p>출금계좌</p>
						<select name="accountNo" id="accountNo">
							<c:forEach items="${ gatheringList }" var="gathering" varStatus="loop">
								<c:choose>									
									<c:when test="${gathering.safeAccountNo == safeAccountNo}">
										<option value="${ gathering.accountNo }" selected="selected">
										  	${ gathering.accountNo } : 
										  	${ gathering.name } &nbsp;&nbsp;&nbsp;&nbsp;
										  	잔액 ${ gathering.balance }원
									  	</option>
									</c:when>
									<c:otherwise>
										<option value="${ gathering.accountNo }">
										  	${ gathering.accountNo } : 
										  	${ gathering.name } &nbsp;&nbsp;&nbsp;&nbsp;
										  	잔액 ${ gathering.balance }원
									  	</option>
									</c:otherwise>
								</c:choose>	 
							</c:forEach> 
							
							<c:forEach items="${ accountList }" var="account" varStatus="loop">
							  <option value="${ account.accountNo }">
							 	${ account.accountNo } : 				  				
				  				${ account.productName } &nbsp;&nbsp;&nbsp;&nbsp;
							  	잔액 ${ account.balance }원
							  </option>
							</c:forEach>  
							
						</select>
					</div>
					<div class="col-12">
					</div>
					<div>
						<p>받는 통장 표시</p>
						<input type="text" name="indication">
					</div>
					<p>
						 <input type="submit" value="이체하기">
					</p>
				</form>             
            
            
          </div>
          
        </div> 
        
        
        
        
        <div class="row">
          <div class="col-md-6 order-lg-1 mb-5">
            <img src="${ pageContext.request.contextPath }/resources/img/slider-2.jpg" alt="Image placeholder" class="img-fluid">
          </div>
          <div class="col-md-1 order-lg-2"></div>
          <div class="col-md-5 order-lg-3">
            <h2 class="text-uppercase heading mb-4">We Are 25 Years in Industry</h2>
            <p class="mb-3">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi unde impedit, necessitatibus, soluta sit quam minima expedita atque corrupti reiciendis.</p>

            <ul class="list-unstyled check">
              <li>Soluta sit quam minima</li>
              <li>Consectetur adipisicing elit</li>
              <li>Commodi unde impedit</li>
            </ul>
          </div>
          
        </div>
      </div>
    </section>
    
    <section class="section border-t">
      <div class="container">
        <div class="row justify-content-center mb-5 element-animate">
          <div class="col-md-8 text-center mb-5">
            <h2 class="text-uppercase heading border-bottom mb-4">Testimonial</h2>
            <p class="mb-0 lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi unde impedit, necessitatibus, soluta sit quam minima expedita atque corrupti reiciendis.</p>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6 element-animate">
            <div class="media d-block media-testimonial text-center">
              <img src="${ pageContext.request.contextPath }/resources/img/person_1.jpg" alt="Image placeholder" class="img-fluid mb-3">
              <p>Jane Doe, <a href="#">XYZ Inc.</a></p>
              <div class="media-body">
                <blockquote>
                  <p>&ldquo;Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi unde impedit, necessitatibus, soluta sit quam minima expedita atque corrupti reiciendis.&rdquo;</p>  
                </blockquote>

              </div>
            </div>
          </div>

          <div class="col-md-6 element-animate">
            <div class="media d-block media-testimonial text-center">
              <img src="${ pageContext.request.contextPath }/resources/img/person_3.jpg" alt="Image placeholder" class="img-fluid mb-3">
              <p>John Doe, <a href="#">XYZ Inc.</a></p>
              <div class="media-body">
                <blockquote>
                  <p>&ldquo;Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi unde impedit, necessitatibus, soluta sit quam minima expedita atque corrupti reiciendis.&rdquo;</p>  
                </blockquote>

              </div>
            </div>
          </div>

        </div>
      </div>
    </section>
--%>

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