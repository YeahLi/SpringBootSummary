package com.henry.springboot.mvc.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器 Demo
 */
public class MyInterceptor implements HandlerInterceptor {
    /**
     * controller方法执行前，进行拦截的方法
     * return true放行
     * return false拦截
     * 可以使用转发或者重定向直接跳转到指定的页面。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("拦截器执行了..."); return true;
    }

    /**
     * postHandle是controller方法执行后执行的方法，在JSP视图执行前。 一般在这里指定response的header
     * 1. 可以使用request或者response跳转到指定的页面
     * 2. 如果指定了跳转的页面，那么controller方法跳转的页面将不会显示。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle 方法执行了");
    }

    /**
     * postHandle方法是在JSP执行后执行
     * 1. request或者response不能再跳转页面了
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        System.out.println("afterCompletion 方法执行了");
    }
}
