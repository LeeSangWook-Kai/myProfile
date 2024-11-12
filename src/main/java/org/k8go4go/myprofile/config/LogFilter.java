package org.k8go4go.myprofile.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String requestURI = httpServletRequest.getRequestURI();

        log.info("requestURI : " + requestURI);
        //다음 서블릿 요청으로 넘김

        chain.doFilter(request, response);
        log.info("responseURI : " + requestURI);
    }
}