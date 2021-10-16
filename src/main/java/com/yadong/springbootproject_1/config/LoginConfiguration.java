package com.yadong.springbootproject_1.config;

import com.yadong.springbootproject_1.interceptor.LoginInterceptor;
import com.yadong.springbootproject_1.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //拦截后台登录地址及其后台管理页面
        registry.addInterceptor(loginInterceptor).addPathPatterns("/admin/**")
                .excludePathPatterns("/css/**","/fonts/**","/img/**","/js/**","/related/**")
                .excludePathPatterns("/","/index.html")
                .excludePathPatterns("/api/user/**");

        //拦截除两个登录页和除后台的管理的东西
        registry.addInterceptor(userInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/css/**","/fonts/**","/img/**","/js/**","/related/**")
                .excludePathPatterns("/","/index.html")
                .excludePathPatterns("/api/user/**");
    }
}
