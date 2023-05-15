package com.springproject.session;

import com.springproject.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LogInterceptor())
//                .order(1)
//                .addPathPatterns("/**") //모든 경로 전체 가능
//                .excludePathPatterns("/", "/css/**", "/*.ico", "/error","/resources/**", "/assets/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**") //모든 경로 전체 가능
                .excludePathPatterns("/", "/home", "/index","/css/**","/resources/**", "/assets/**", "/logout");
    }
}
