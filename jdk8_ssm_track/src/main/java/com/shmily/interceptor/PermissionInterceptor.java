package com.shmily.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuxubiao on 2017/4/28.
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    private Logger log = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("可以进行编码、安全控制等处理...");
        HandlerMethod handlerMethod = null;
        if(handler instanceof HandlerMethod){
            handlerMethod = (HandlerMethod)handler;

        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("可以根据ex是否为null判断是否发生了异常，进行日志记录...");
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("可以在此进行页面跳转根据业务处理....");
        super.postHandle(request, response, handler, modelAndView);
    }
}
