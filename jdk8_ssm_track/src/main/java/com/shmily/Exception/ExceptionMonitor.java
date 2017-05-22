package com.shmily.Exception;

import com.alibaba.fastjson.JSON;
import com.shmily.util.Files_Helper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Date;

/**
 * 异常监测切面类
 * 采用记录方式为记入文本
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
            //上传日志系统,自行完善(输出为文件)
            Files_Helper.uploadLog(JSON.toJSONString(info));
            return null;
        }
    }
}

