package com.wzydev.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")  //拦截所有请求。因为只是个demo，实际应用中不用，所以把@WebFilter注解注释掉，防止其发挥作用
public class DemoFilter implements Filter {
    //初始化方法，web服务器启动时执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化方法……");
    }

    //拦截到请求之后执行，会执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求……");
        //放行。将请求和响应对象沿过滤器链传递到下一个过滤器，如果当前过滤器是最后一个过滤器，则请求会传递给目标资源
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //销毁方法，web服务器关闭时执行，只执行一次
    @Override
    public void destroy() {
        System.out.println("销毁方法……");
    }
}
