<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<style>
	#profile{	
		display: inline;
		float: left; 
   		content: '';
 		width:30px;
		height:30px;
		border-radius: 100%;
		background: url("${ pageContext.request.contextPath }/resources/img/img-phising-default.png") 0 0 no-repeat;
		background-size : cover;
		position: relative;
	}
</style>
 <header role="banner">
   <nav class="navbar navbar-expand-lg navbar-light bg-light">
     <div class="container">

		<a class="navbar-brand position-absolute" href="${ pageContext.request.contextPath }/"><img src="${ pageContext.request.contextPath }/resources/img/logo6.JPG""></a>
		
       <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
         <span class="navbar-toggler-icon"></span>
       </button>

       <div class="collapse navbar-collapse position-relative" id="navbarsExample05">
         <ul class="navbar-nav mx-auto pl-lg-5 pl-0 d-flex align-items-center font-weight-bold">
			<c:choose>
				<c:when test="${empty loginVO}">
		             <li class="nav-item">
		               <a class="nav-link active" href="${ pageContext.request.contextPath }/">Home</a>
		             </li>   				
				</c:when>
				<c:otherwise>
					<li class="nav-item">
		               <a class="nav-link" href="${ pageContext.request.contextPath }/">Home</a>
		             </li>  
				</c:otherwise>
			</c:choose>              

			<li class="nav-item">
             <a class="nav-link" href="${ pageContext.request.contextPath }/gathering/apply">모임통장전환</a>
           </li>
           	<li class="nav-item">
             <a class="nav-link" href="${ pageContext.request.contextPath }/">계좌조회</a>
           </li>
           <li class="nav-item">
             <a class="nav-link" href="${ pageContext.request.contextPath }/transaction/transfer">이체</a>
           </li>
           <c:choose>
			<c:when test="${ empty loginVO }">	
				<li class="nav-item cta-btn2" style="border:#27b2a5;">
		             <a class="nav-link" href="${ pageContext.request.contextPath }/login">
		               <span class="d-inline-block px-4 py-2 border btn btn-primary">로그인</span>
		             </a>
		       </li>
	        </c:when>
	        <c:otherwise>
	           <li class="nav-item">
					<a class="nav-link active" href="${ pageContext.request.contextPath }/main">My moim</a>
	           </li>	
	        	<li class="pl-3">
					<div id="profile" class="mr-1"></div><span>${loginVO.name}님, 안녕하세요.</span> 
	           </li>	           				        
				<li class="nav-item cta-btn2" style="border:#27b2a5;">
		             <a class="nav-link" href="${ pageContext.request.contextPath }/logout">
		               <span class="d-inline-block px-4 py-2 border btn btn-primary">로그아웃</span>
		             </a>
		       </li>
	        </c:otherwise>
	  	 </c:choose>
           
         </ul>
       </div>
     </div>
   </nav>
 </header>