package com.wzydev.interceptor;

import com.wzydev.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 令牌校验的拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否为登录请求这部分代码可以省略，因为可以在注册拦截器的时候直接将/login的请求路径加入到白名单中
//        //1.获取请求路径
//        String requestURI = request.getRequestURI();  //uri是url去掉协议、ip地址、端口号之后剩下的部分，例如/user/list
//
//        //2.判断是否为登录请求，如果路径中包含/login，则放行
//        if(requestURI.contains("/login")) {
//            return true;  //返回值为true，放行
//        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否存在，若不存在，说明用户没有登录，返回错误信息（响应401状态码）
        if(token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  //未认证
            return false;  //返回值为false，阻止
        }

        //5.如果token存在，校验令牌，若校验失败（令牌被篡改或者过期），返回错误信息（响应401状态码）
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //6.校验通过，放行
        return true;
    }
}
