package com.wzydev.filter;

import com.wzydev.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebFilter
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求路径
        String requestURI = request.getRequestURI();  //uri是url去掉协议、ip地址、端口号之后剩下的部分，例如/user/list

        //2.判断是否为登录请求，如果路径中包含/login，则放行
        if(requestURI.contains("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否存在，若不存在，说明用户没有登录，返回错误信息（响应401状态码）
        if(token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  //未认证
            return;
        }

        //5.如果token存在，校验令牌，若校验失败（令牌被篡改或者过期），返回错误信息（响应401状态码）
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //6.校验通过，放行
        filterChain.doFilter(request, response);
    }
}
