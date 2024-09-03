package com.orientalstorkhub.blog.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.orientalstorkhub.blog.common.context.UserContext;

/**
 * 用户令牌拦截器，用于处理用户令牌的验证和清理。
 * 
 * @author zhangj
 * @since 2024-09-03
 */




public class UserTokenInterceptor implements HandlerInterceptor{

@SuppressWarnings("null")
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String userId = request.getHeader("UserId");
    if (userId != null) {
        UserContext.setUserId(Integer.parseInt(userId));
    }
    return true;
}

@SuppressWarnings("null")
@Override
public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    UserContext.clear();
}
}
