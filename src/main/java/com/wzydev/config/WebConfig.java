package com.wzydev.config;

import com.wzydev.interceptor.DemoInterceptor;
import com.wzydev.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
//    因为只是个demo，实际应用中不用，所以把@Autowired注解注释掉，防止其发挥作用
//    @Autowired
//    private DemoInterceptor demoInterceptor;

//    @Autowired
//    private TokenInterceptor tokenInterceptor;
//
//    // 注册拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/**")  // 拦截所有请求
//                .excludePathPatterns("/login");  //不拦截哪些请求
//    }
}
