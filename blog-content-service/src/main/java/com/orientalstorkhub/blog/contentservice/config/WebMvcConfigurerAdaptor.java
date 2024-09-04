package com.orientalstorkhub.blog.contentservice.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.orientalstorkhub.blog.common.interceptor.UserTokenInterceptor;


@SpringBootConfiguration
@ConditionalOnWebApplication
public class WebMvcConfigurerAdaptor implements WebMvcConfigurer{

    private final String[] excludePathList = new String[]{
        "/auth/login",
        "/auth/register",
        "/doc",
        "/v2/api-docs",
        "/v3/api-docs",
    };
    
    @Override
    public void addInterceptors(@SuppressWarnings("null") InterceptorRegistry registry) {
        registry.addInterceptor(UserInterceptor()).excludePathPatterns(excludePathList);
    }


    @Bean 
    public HandlerInterceptor UserInterceptor(){
        return new UserTokenInterceptor();
    }
}
