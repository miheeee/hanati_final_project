package kr.ac.kopo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ac.kopo.member.vo.MemberVO;

public final class LoginInterceptor extends HandlerInterceptorAdapter{

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)      //어떤 컨트롤러의 어느 메소드를 실행했는지 handler를 통해 알 수 있음
         throws Exception {

      //로그인 여부 체크
      HttpSession session = request.getSession();
      MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
      
      if(loginVO == null) {
         
         String uri = request.getRequestURI();
         uri = uri.substring(request.getContextPath().length());	//요청된 uri
         
         String query = request.getQueryString();

         if(query != null && query.length() != 0)
            uri = uri + "?" + query;
         session.setAttribute("dest", uri);		//로그인 화면으로 이동하기 전 원래 목적지(요청된 주소)를 세션 영역에 저장
         
         response.sendRedirect(request.getContextPath() + "/login");
         
         return false;      //컴포넌트로 가는 것을 막음, 빈화면이 나오게 되는데 그건 의미 없음      
      }
      
//      내가 @RequestMapping해준거 요청 함
//      이게 true값
//      false는 안함
      
      return true;
   }

}