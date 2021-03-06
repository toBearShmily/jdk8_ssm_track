package com.shmily.support.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/3/23.
 */
@Aspect
public class MyAspectj_logback implements Ordered {

    private static Logger log = LoggerFactory.getLogger(MyAspectj_logback.class);

    /**
     * 前置通知
     */
    @Before("execution(* com.shmily.controller.*Controller.*(..)))")
    public void before(JoinPoint joinPoint){
        System.out.println("前置通知.....");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        log.info("url:{}",request.getRequestURL());

        //method
        log.info("method:{}",request.getMethod());

        //ip
        log.info("ip:{}",request.getRemoteAddr());

        //类方法
        log.info("class_method:{}",joinPoint.getSignature().getDeclaringTypeName() +"."+ joinPoint.getSignature().getName());

        //参数
        log.info("prams:{}", JSON.toJSONString(joinPoint.getArgs()));
    }

    /**
     * 后置通知
     * returnVal,切点方法执行后的返回值
     */
    @AfterReturning(value = "execution(* com.shmily.controller.*Controller.*(..))" , returning = "returnVal")
    public void afterReturning(Object returnVal){
        System.out.println("后置通知....."+returnVal);
    }

    /**
     * 无论什么情况下都会执行的方法
     */
    @After("execution(* com.shmily.controller.*Controller.*(..))")
    public void  after(){
        System.out.println("最终通知");
    }

    /**
     * 环绕通知
     * @param joinPoint 可用于执行切点的类
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.shmily.controller.*Controller.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("环绕通知前.....");
        Object result = joinPoint.proceed();//执行目标函数
        System.out.println("环绕通知后.....");
        return result;
    }

    /**
     * 抛出通知
     * @param e
     */
    @AfterThrowing(value = "execution(* com.shmily.controller.*Controller.*(..))" , throwing = "e")
    public void afterThrowable(Throwable e){
        System.out.println("异常通知>>>>msg:"+e.getMessage());
    }


    //通过定义切点方式
    @Pointcut("execution(* com.shmily.controller.*Controller.*(..))")
    public void pointcut(){}
    //使用
    @After("pointcut()")
    public void afterWxb(){
        System.out.println("......");
    }

    /**
     * 实现Ordered接口，，定义优先级，越小优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
