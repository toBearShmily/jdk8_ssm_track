package com.example.design_pattern.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/3/13.
 */
public class Client {

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Class<?> classType = realSubject.getClass();
        InvocationHandler handler = new DynamicSubject(realSubject,false);

        Subject subject = (Subject) Proxy.newProxyInstance(classType.getClassLoader()
                ,classType.getInterfaces(),handler);

        subject.request("吴旭彪");
    }
}
