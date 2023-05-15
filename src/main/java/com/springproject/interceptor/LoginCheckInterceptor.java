package com.springproject.interceptor;

import com.springproject.sessionConst.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println("[인증 체크 인터셉터 실행] : " + requestURI);

        HttpSession session = request.getSession();

        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null ) {
            System.out.println("[미인증 사용자 요청]");
            //로그인으로  redirect
            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                w.println("<script language='javascript'>");
                w.println("alert('로그인을 먼저 진행해주세요.'); location.href='/';");
                w.println("</script>");
                //  location.href='/?redirectURI='+requestURI;

                w.flush();

                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;

    }

}
