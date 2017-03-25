package com.shmily.support.aop.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/3/24.
 */
//自定义的接口类，JDK动态代理的实现必须有对应的接口类
public interface ExInterface {
    void execute();
}
//A类，实现了ExInterface接口类
class A implements ExInterface{

    @Override
    public void execute() {
        System.out.println("执行A的execute方法！！！");
    }

}

//代理类的实现
class JdkProxy implements InvocationHandler{
    private Object object;
    public JdkProxy(Object object){
        this.object = object;
    }

    /*Object proxy ：生成的代理对象
    Method method：目标对象的方法，通过反射调用
    Object[] args：目标对象方法的参数*/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法前操作！！！");
        Object obj = method.invoke(object,args);
        System.out.println("方法后操作！！！");
        return obj;
    }
    /**
     * 创建代理类
     * @return
     */
    public ExInterface createProxy(){
        /**
         * @param loader 类加载器，一般传递目标对象(A类即被代理的对象)的类加载器
         * @param interfaces 目标对象(A)的实现接口
         * @param h 回调处理句柄(后面会分析到)
         */
        return (ExInterface) Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }
    //测试
    public static void main(String[] args) {
        A a = new A();
        JdkProxy jdkProxy = new JdkProxy(a);
        ExInterface e = jdkProxy.createProxy();
        e.execute();
    }
}

