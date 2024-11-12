package org.k8go4go.myprofile.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.k8go4go.myprofile.session.SessionConst;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
@Slf4j
public class LoginCheckFilter implements Filter {
    //로그인이 필요하지 않은 페이지 url 요청들
    private static final String[] whitelist = {"/","/home", "/guest/login", "/guest/signup", "/upload/profile/temp/*", "/file/upload",  "/file/upload/profile/image",  "/file/upload/profile",  "/profile/logout", "/css/*", "/js/*", "/out/*"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        if(isLoginCheckPath(requestURI)) {
            System.out.println("인증 체크 로직 실행 : " + requestURI);
            HttpSession session = httpRequest.getSession(false);
            if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null || session.isNew()) {
                // 로그인 되지 않음
                log.error("미 인증 사용자 요청");
                // 로그인으로 redirect
                // 사용자가 원래 가려고 했던 URI를 파라미터 값으로 기억했다가 로그인 후,
                //이 페이지로 이동
                httpResponse.sendRedirect("/guest/login");
                return;
            }
        }
        // 로그인이 되어있다면 다음 단계로 넘어간다.
        filterChain.doFilter(request, response);

    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
