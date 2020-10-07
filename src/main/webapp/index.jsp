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
    
    <section class="home-slider owl-carousel">
    
      <div class="slider-item" style="background-image: url('${ pageContext.request.contextPath }/resources/img/pexels-startup-stock-photos-7095.jpg');">       
        <div class="container">
          <div class="row slider-text align-items-center justify-content-center text-center">
            <div class="col-md-7 col-sm-12 element-animate">
              <h1 class="mb-4" style="text-shadow: 2px 2px 2px gray;"> Make One's Invaluable Momemt </h1>
              	<c:if test="${empty loginVO}">
              		<p class="mb-0"><a href="#" target="_blank" class="btn btn-primary">회원가입</a></p> 
				</c:if>
            </div>
          </div>
        </div>
      </div>

      <div class="slider-item" style="background-image: url('${ pageContext.request.contextPath }/resources/img/nw_bg_main_dollar_200903.jpg');">
        <div class="container">
          <div class="row slider-text">
            <div class="col-md-8 col-sm-12 element-animate">
              <h2 class="mb-4 mt-5 pt-5" style="color:black;font-family:'NotoSans_Light';">손님의 기쁨 그 하나를 위한 은행이 되도록 최선을 다하겠습니다.</h2>
            </div>
          </div>
        </div>     
      </div>

    </section>
    <!-- END slider -->


    <section class="section" style="background-color:#f4f6f7;">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-md-6 pr-lg-5 mb-5 mb-md-0  element-animate">
            <div class="pr-lg-5">
              <h2 class="text-uppercase heading border-bottom mb-4 text-left"> Make One's <br>Invaluable Momemt</h2>
              <p>모두의 기쁨, 그 하나를 위하여<br>친구, 가족, 연인과의 만남에서 소중한 순간에만 집중할 수 있도록  하나모임이 노력하겠습니다.</p>
            </div>
          </div>
          <div class="col-md-6  element-animate">
            <img src="${ pageContext.request.contextPath }/resources/img/2977989.jpg" alt="" class="img-fluid">
          </div>
        </div>
      </div>
    </section>
    
    <section class="section border-t">
      <div class="container">
        <div class="row justify-content-center mb-5 element-animate">
          <div class="col-md-8 text-center">
            <h2 class="text-uppercase heading border-bottom mb-4">Recent News</h2>
		  </div>
        </div>

        <div class="row no-gutters">
          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/20200805.jpg" alt="Image placeholder" class="img-fluid">
            </a>
          </div>
          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/20200608.jpg" alt="Image placeholder" class="img-fluid" style="  background-size: cover;background-position: center;background-repeat: no-repeat;">
            </a>
          </div>
          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/20200524.jpg" alt="Image placeholder" class="img-fluid" style="  background-size: cover;background-position: center;background-repeat: no-repeat;">
            </a>
          </div>

          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/5836_7144_2310.jpg" alt="Image placeholder" class="img-fluid">
            </a>
          </div>
          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/20200707.jpg" alt="Image placeholder" class="img-fluid" style="  background-size: cover;background-position: center;background-repeat: no-repeat;">
            </a>
          </div>
          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/20200706.jpg" alt="Image placeholder" class="img-fluid" style="  background-size: cover;background-position: center;background-repeat: no-repeat;">
            </a>
          </div>

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