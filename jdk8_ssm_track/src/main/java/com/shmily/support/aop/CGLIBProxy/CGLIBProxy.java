package com.shmily.support.aop.CGLIBProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB代理
 * Created by Administrator on 2017/3/24.
 */

//代理类
public class CGLIBProxy implements MethodInterceptor{
    /**
     * 被代理的目标类
     */
    private A target;
    public CGLIBProxy (A target){
        super();
        this.target = target;
    }
    /**
     * 创建代理对象
     * @return
     */
    public A createProxy(){
        // 使用CGLIB生成代理:
        // 1.声明增强类实例,用于生产代理类
        Enhancer enhancer = new Enhancer();
        // 2.设置被代理类字节码，CGLIB根据字节码生成被代理类的子类
        enhancer.setSuperclass(target.getClass());
        // 3.设置回调函数，即一个方法拦截
        enhancer.setCallback(this);
        // 4.创建代理:
        return (A) enhancer.create();
    }
    /**
     * 回调函数
     * @param proxy 代理对象
     * @param method 委托类方法
     * @param args 方法参数
     * @param methodProxy 每个被代理的方法都对应一个MethodProxy对象，
     *                    methodProxy.invokeSuper方法最终调用委托类(目标类)的原始方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("目标对象执行前！！！");
        methodProxy.invokeSuper(proxy,args);
        System.out.println("目标对象执行后！！！");
        return null;
    }
}
class A{
    public void execute(){
        System.out.println("执行A的execute方法！！！");
    }
}
class test{
    public static void main(String[] args) {
        A a = new A();
        A aProxy = new CGLIBProxy(a).createProxy();
        aProxy.execute();
    }
}