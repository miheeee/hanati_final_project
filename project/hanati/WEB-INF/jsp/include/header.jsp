<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 <header role="banner">
   <nav class="navbar navbar-expand-lg navbar-light bg-light">
     <div class="container">
		<c:choose>
			<c:when test="${ empty loginVO }">	
				<a class="navbar-brand position-absolute" href="${ pageContext.request.contextPath }">Hana MOIM</a>
	        </c:when>
	        <c:otherwise>
				<a class="navbar-brand position-absolute" href="${ pageContext.request.contextPath }/main">Hana MOIM</a>
	        </c:otherwise>
		</c:choose> 
		
       <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
         <span class="navbar-toggler-icon"></span>
       </button>

       <div class="collapse navbar-collapse position-relative" id="navbarsExample05">
         <ul class="navbar-nav mx-auto pl-lg-5 pl-0 d-flex align-items-center">
           <li class="nav-item">
			<c:choose>
				<c:when test="${ empty loginVO }">	
					<a class="nav-link active" href="${ pageContext.request.contextPath }">Home</a>
		        </c:when>
		        <c:otherwise>
					<a class="nav-link active" href="${ pageContext.request.contextPath }/main">Home</a>
		        </c:otherwise>
			</c:choose>            
           </li>
           <li class="nav-item dropdown">
             <a class="nav-link dropdown-toggle" href="services.html" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">모임통장</a>
             <div class="dropdown-menu" aria-labelledby="dropdown04">
               <a class="dropdown-item" href="${ pageContext.request.contextPath }/gathering/apply">신청</a>
             </div>
           </li>
<%-- 			<li class="nav-item dropdown">
             <a class="nav-link dropdown-toggle" href="services.html" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">이체</a>
             <div class="dropdown-menu" aria-labelledby="dropdown04">
               <a class="dropdown-item" href="${ pageContext.request.contextPath }/transaction/transfer">이체</a>
             </div>
           </li> --%>
           <li class="nav-item">
             <a class="nav-link" href="works.html">Projects</a>
           </li>
           <li class="nav-item">
             <a class="nav-link" href="news.html">News</a>
           </li>
           <li class="nav-item">
             <a class="nav-link" href="about.html">About</a>
           </li>
           <li class="nav-item">
             <a class="nav-link" href="contact.html">Contact</a>
           </li>
           <li class="nav-item cta-btn2">
           		<c:choose>
					<c:when test="${ empty loginVO }">	
			             <a class="nav-link" href="${ pageContext.request.contextPath }/login">
			               <span class="d-inline-block px-4 py-2 border">로그인</span>
			             </a>
			        </c:when>
			        <c:otherwise>
			        	<a class="nav-link" href="${ pageContext.request.contextPath }/logout">
			               <span class="d-inline-block px-4 py-2 border">로그아웃</span>
			             </a>
			        </c:otherwise>
			   </c:choose>
           </li>
         </ul>
       </div>
     </div>
   </nav>
 </header>