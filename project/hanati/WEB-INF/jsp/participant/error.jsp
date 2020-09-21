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
              <h1 class="text-white">초대 수락</h1>
            </div>
          </div>
        </div>

      </div>
    </section>
    
    <section class="section">
      <div class="container">
        <div class="row justify-content-center mb-5 element-animate">
          <div class="col-md-5">
           		<h3>유효하지 않은 url입니다.</h3> 
           		<h3>친구에게 다시 초대 메시지를 요청하세요</h3>
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
              <img src="img/person_1.jpg" alt="Image placeholder" class="img-fluid mb-3">
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
              <img src="img/person_3.jpg" alt="Image placeholder" class="img-fluid mb-3">
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