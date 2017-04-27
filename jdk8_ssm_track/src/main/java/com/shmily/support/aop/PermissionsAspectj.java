package com.shmily.support.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 关于权限操作的aop实现
 * Created by wuxubiao on 2017/4/27.
 */
@Aspect
public class PermissionsAspectj {
    Logger log = LoggerFactory.getLogger(PermissionsAspectj.class);

    /**
     * 环绕通知
     * @param joinPoint 可用于执行切点的类
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.shmily.controller.*Controller.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("当前被拦截的方法为：{}"+joinPoint.getSignature().getName());
        String methodName = joinPoint.getSignature().getName();
        Object obj = joinPoint.getTarget();//获取连接点所在的目标对象


        log.info("环绕通知前.....");
        Object result = joinPoint.proceed();//执行目标函数
        log.info("环绕通知后.....");
        return result;
    }


}
