package com.shmily.support.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Date;

/**
 * 异常监测切面类
 * Created by Administrator on 2017/3/24.
 */

@Aspect
public class ExceptionMonitor {
    /**
     * 定义异常监控类
     */
    @Pointcut("execution(com.shmily *(..))")
    void exceptionMethod() {
    }

    @Around("exceptionMethod()")
    public Object monitorMethods(ProceedingJoinPoint thisJoinPoint) {
        try {
            return thisJoinPoint.proceed();
        } catch (Throwable e) {
            ExceptionInfo info=new ExceptionInfo();
            //异常类记录
            info.setClassName(thisJoinPoint.getTarget().getClass().getName());
            info.setMethodName(thisJoinPoint.getSignature().getName());
            info.setLogTime(new Date());
            info.setMessage(e.toString());
            //上传日志系统,自行完善
            //ExceptionReportUtils.report(info);
            return null;
        }
    }
}

