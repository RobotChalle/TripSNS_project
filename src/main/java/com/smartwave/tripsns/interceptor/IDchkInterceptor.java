package com.smartwave.tripsns.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class IDchkInterceptor implements HandlerInterceptor {
    //  HandlerInterceptor를 상속받음
    // 	 * preHandle()-컨트롤러 진입전에 실행
    //	 * postHandle()-컨트롤러 수행 후에 실행
    //	 * afterCompletion()-화면으로 가기 직전에 수행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("userid");//세션으로부터 값을 가져온다
        if (user == null) {//세션값이 없을경우 -> 로그인x
            response.sendRedirect("/login");//로그인화면으로 보낸다
            return false;//컨트롤러 실행 x
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);// 로그인 해서 세션값 있으면 그대로 실행
    }


}
