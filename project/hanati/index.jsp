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
      <div class="slider-item" style="background-image: url('${ pageContext.request.contextPath }/resources/img/slider-2.jpg');">
        
        <div class="container">
          <div class="row slider-text align-items-center justify-content-center text-center">
            <div class="col-md-7 col-sm-12 element-animate">
              <h1 class="mb-4"> Fastest-Growing Construction Company</h1>
              <p class="mb-0"><a href="#" target="_blank" class="btn btn-primary">회원가입</a></p>
              
            </div>
          </div>
        </div>

      </div>

      <div class="slider-item" style="background-image: url('${ pageContext.request.contextPath }/resources/img/slider-1.jpg');">
        <div class="container">
          <div class="row slider-text align-items-center justify-content-center text-center">
            <div class="col-md-8 col-sm-12 element-animate">
              <h1 class="mb-4">We Are Leading The Way Construction Works</h1>
              <p class="mb-0"><a href="#" target="_blank" class="btn btn-primary">회원가입</a></p>
            </div>
          </div>
        </div>
        
      </div>

    </section>
    <!-- END slider -->


    <section class="section bg-light">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-md-6 pr-lg-5 mb-5 mb-md-0  element-animate">
            <div class="pr-lg-5">
              <h2 class="text-uppercase heading border-bottom mb-4 text-left">We Are Expert in <br>Construction Field</h2>
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ullam similique repellat dignissimos, omnis at ducimus pariatur odio praesentium eveniet porro sit quod, sequi unde atque magnam voluptate quae voluptatum. Delectus.</p>
              <p>At sed impedit, ab a officia blanditiis, fuga commodi delectus veniam architecto in nihil numquam eum maiores. Amet nihil, dolorum sit vitae fugit maxime earum optio culpa eum. Voluptates, labore.</p>
            </div>
          </div>
          <div class="col-md-6  element-animate">
            <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_1.jpg" alt="" class="img-fluid">
          </div>
        </div>
      </div>
    </section>
    
    <section class="section border-t">
      <div class="container">
        <div class="row justify-content-center mb-5 element-animate">
          <div class="col-md-8 text-center">
            <h2 class="text-uppercase heading border-bottom mb-4">Recent Projects</h2>
            <p class="mb-3 lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi unde impedit, necessitatibus, soluta sit quam minima expedita atque corrupti reiciendis.</p>
            <p><a href="works.html" class="btn btn-primary">See All Projects</a></p>
          </div>
        </div>

        <div class="row no-gutters">
          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <h3>House Renovation</h3>
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_1.jpg" alt="Image placeholder" class="img-fluid">
            </a>
          </div>
          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <h3>General Construction Building</h3>
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_2.jpg" alt="Image placeholder" class="img-fluid">
            </a>
          </div>
          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <h3>Pre-Construction</h3>
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_3.jpg" alt="Image placeholder" class="img-fluid">
            </a>
          </div>

          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <h3>House Renovation</h3>
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_3.jpg" alt="Image placeholder" class="img-fluid">
            </a>
          </div>
          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <h3>General Construction Building</h3>
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_1.jpg" alt="Image placeholder" class="img-fluid">
            </a>
          </div>
          <div class="col-md-4 element-animate">
            <a href="works-single.html" class="link-thumbnail">
              <h3>Pre-Construction</h3>
              <span class="ion-plus icon"></span>
              <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_2.jpg" alt="Image placeholder" class="img-fluid">
            </a>
          </div>

        </div>
      </div>
    </section>
    <!-- END section -->

    <section class="section">
      <div class="container">

        <div class="row justify-content-center mb-5 element-animate">
          <div class="col-md-8 text-center">
            <h2 class="text-uppercase heading border-bottom mb-4">Services</h2>
            <p class="mb-3 lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi unde impedit, necessitatibus, soluta sit quam minima expedita atque corrupti reiciendis.</p>
          </div>
        </div>

        <div class="row mb-5">

          <div class="col-lg-4 col-md-6 col-12 mb-3 element-animate">
            <div class="media d-block media-feature text-center">
              <span class="flaticon-blueprint icon"></span>
              <div class="media-body">
                <h3 class="mt-0 text-black">Pre-Construction</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                <p><a href="#" class="btn btn-outline-primary btn-sm">Learn More</a></p>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 col-12 mb-3 element-animate">
            <div class="media d-block media-feature text-center">
              <span class="flaticon-building-1 icon"></span>
              <div class="media-body">
                <h3 class="mt-0 text-black">House Renovation</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                <p><a href="#" class="btn btn-outline-primary btn-sm">Learn More</a></p>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 col-12 mb-3 element-animate">
            <div class="media d-block media-feature text-center">
              <span class="flaticon-crane icon"></span>
              <div class="media-body">
                <h3 class="mt-0 text-black">General Contracting</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                <p><a href="#" class="btn btn-outline-primary btn-sm">Learn More</a></p>
              </div>
            </div>
          </div>


          
        </div>
        <!-- END row -->
        <div class="row justify-content-center element-animate"> 
          <div class="col-md-4"><p><a href="services.html" class="btn btn-primary btn-block">View All Services</a></p></div>
        </div>
      </div>
    </section>
    <!-- END section -->

  
    <section class="section bg-light">
      <div class="container">
        
        <!-- 제목 시작 -->
        <div class="row justify-content-center mb-5 element-animate">
          <div class="col-md-8 text-center mb-5">
            <h2 class="text-uppercase heading border-bottom mb-4">Our News</h2>
            <p class="mb-0 lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi unde impedit, necessitatibus, soluta sit quam minima expedita atque corrupti reiciendis.</p>
          </div>
        </div>
        <!-- 제목 끝-->
        
        <!-- 콘텐츠 시작 -->
        <div class="row element-animate">
        <!-- 시작 slider -->
          <div class="major-caousel js-carousel-1 owl-carousel">
            <!-- 1번째 슬라이더 -->
            <div>
              <div class="media d-block media-custom text-left">
                <img src="img/work_thumb_1.jpg" alt="Image Placeholder" class="img-fluid">
                <div class="media-body">
                  <span class="meta-post">December 2, 2017</span>
                  <h3 class="mt-0 text-black"><a href="#" class="text-black">Lorem ipsum dolor sit amet elit</a></h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                  <p class="clearfix">
                    <a href="#" class="float-left">Read more</a>
                    <a href="#" class="float-right meta-chat"><span class="ion-chatbubble"></span> 8</a>
                  </p>
                </div>
              </div>
            </div>
            <!--  -->
            <!-- 2번째 슬라이더 -->
            <div>
              <div class="media d-block media-custom text-left">
                <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_2.jpg" alt="Image Placeholder" class="img-fluid">
                <div class="media-body">
                  <span class="meta-post">December 2, 2017</span>
                  <h3 class="mt-0 text-black"><a href="#" class="text-black">Lorem ipsum dolor sit amet elit</a></h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                  <p class="clearfix">
                    <a href="#" class="float-left">Read more</a>
                    <a href="#" class="float-right meta-chat"><span class="ion-chatbubble"></span> 2</a>
                  </p>
                </div>
              </div>
            </div>
            <!--  -->
            <!-- 3번째 슬라이더 -->
            <div>
              <div class="media d-block media-custom text-left">
                <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_3.jpg" alt="Image Placeholder" class="img-fluid">
                <div class="media-body">
                  <span class="meta-post">December 2, 2017</span>
                  <h3 class="mt-0 text-black"><a href="#" class="text-black">Lorem ipsum dolor sit amet elit</a></h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                  <p class="clearfix">
                    <a href="#" class="float-left">Read more</a>
                    <a href="#" class="float-right meta-chat"><span class="ion-chatbubble"></span> 5</a>
                  </p>
                </div>
              </div>
            </div>
            <div>
              <div class="media d-block media-custom text-left">
                <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_1.jpg" alt="Image Placeholder" class="img-fluid">
                <div class="media-body">
                  <span class="meta-post">December 2, 2017</span>
                  <h3 class="mt-0 text-black"><a href="#" class="text-black">Lorem ipsum dolor sit amet elit</a></h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                  <p class="clearfix">
                    <a href="#" class="float-left">Read more</a>
                    <a href="#" class="float-right meta-chat"><span class="ion-chatbubble"></span> 7</a>
                  </p>
                </div>
              </div>
            </div>

            <div>
              <div class="media d-block media-custom text-left">
                <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_2.jpg" alt="Image Placeholder" class="img-fluid">
                <div class="media-body">
                  <span class="meta-post">December 2, 2017</span>
                  <h3 class="mt-0 text-black"><a href="#" class="text-black">Lorem ipsum dolor sit amet elit</a></h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                  <p class="clearfix">
                    <a href="#" class="float-left">Read more</a>
                    <a href="#" class="float-right meta-chat"><span class="ion-chatbubble"></span> 1</a>
                  </p>
                </div>
              </div>
            </div>
            <div>
              <div class="media d-block media-custom text-left">
                <img src="${ pageContext.request.contextPath }/resources/img/work_thumb_3.jpg" alt="Image Placeholder" class="img-fluid">
                <div class="media-body">
                  <span class="meta-post">December 2, 2017</span>
                  <h3 class="mt-0 text-black"><a href="#" class="text-black">Lorem ipsum dolor sit amet elit</a></h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                  <p class="clearfix">
                    <a href="#" class="float-left">Read more</a>
                    <a href="#" class="float-right meta-chat"><span class="ion-chatbubble"></span> 4</a>
                  </p>
                </div>
              </div>
            </div>
            <div>
              <div class="media d-block media-custom text-left">
                <img src="img/work_thumb_1.jpg" alt="Image Placeholder" class="img-fluid">
                <div class="media-body">
                  <span class="meta-post">December 2, 2017</span>
                  <h3 class="mt-0 text-black"><a href="#" class="text-black">Lorem ipsum dolor sit amet elit</a></h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                  <p class="clearfix">
                    <a href="#" class="float-left">Read more</a>
                    <a href="#" class="float-right meta-chat"><span class="ion-chatbubble"></span> 12</a>
                  </p>
                </div>
              </div>
            </div>
            <div>
              <div class="media d-block media-custom text-left">
                <img src="img/work_thumb_2.jpg" alt="Image Placeholder" class="img-fluid">
                <div class="media-body">
                  <span class="meta-post">December 2, 2017</span>
                  <h3 class="mt-0 text-black"><a href="#" class="text-black">Lorem ipsum dolor sit amet elit</a></h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                  <p class="clearfix">
                    <a href="#" class="float-left">Read more</a>
                    <a href="#" class="float-right meta-chat"><span class="ion-chatbubble"></span> 14</a>
                  </p>
                </div>
              </div>
            </div>
			<!--  -->
          </div>
          <!-- END slider -->
        </div>
        <<!-- 콘텐츠 끝 -->
        
      </div>
    </section>
    <!-- END section -->
    
    <section class="section">
      <div class="container">
        <div class="row justify-content-center mb-5 element-animate">
          <div class="col-md-8 text-center mb-5">
            <h2 class="text-uppercase heading border-bottom mb-4">Happy Customer Says</h2>
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