<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/include/head.jsp" /> 	
</head>
<body>
<!-- BEGIN header -->
	<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<!-- END header -->
    
    <section class="inner-page">
      <div class="slider-item py-5" style="background-image: url('${ pageContext.request.contextPath }/resources/img/slider-1.jpg');">
        
        <div class="container">
          <div class="row slider-text align-items-center justify-content-center text-center">
            <div class="col-md-7 col-sm-12 element-animate">
            </div>
          </div>
        </div>

      </div>
    </section>
    
    <section class="section">
      <div class="container">
        <div class="row justify-content-center mb-5 element-animate text-center">
          <div class="col-md-5">
          	<h2 class="text-uppercase heading border-bottom mb-4 price-hide-wrap btn-price">로그인</h2>
            <form action="${ pageContext.request.contextPath }/login" method="post"
            				onsubmit="return checkForm()" name="lform">
              <div class="row">
                <div class="col-md-12 form-group">
                  <label for="id"></label>
                  <input type="text" class="form-control form-control-lg" name="id" placeholder="아이디">
                </div>
                <div class="col-md-12 form-group">
                  <label for="password"></label>
                  <input type="password" class="form-control form-control-lg" name="password" placeholder="패스워드">
                </div>
              </div>
              <div class="row">
                <div class="col-md-12 form-group">
                  <input type="submit" value="로그인" class="btn btn-primary btn-lg btn-block">
                </div>
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

    <script src="${ pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/popper.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/owl.carousel.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/jquery.waypoints.min.js"></script>
    <script src="${ pageContext.request.contextPath }/resources/js/main.js"></script>
    
	 <script>
		function isNull(obj, msg){
			if(obj.value == ''){
				alert(msg);
				obj.focus();
				return true;
			}
			return false;
		}
		
		function checkForm(){
			var f = document.lform;
			
			if(isNull(f.id, '아이디를 입력하세요')){
				return false;
			}
			
			if(isNull(f.password, '패스워드를 입력하세요')){
				return false;
			}
			
			return true;
				
		}
	</script>
  </body>
</html>