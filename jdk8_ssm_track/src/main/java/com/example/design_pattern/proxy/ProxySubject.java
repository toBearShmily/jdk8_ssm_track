package com.example.design_pattern.proxy;

/**
 * 代理对象(提供了对真实对象的引用，并可以包括自己的一些处理)
 * Created by Administrator on 2017/3/13.
 */

public class ProxySubject extends Subject{

    private RealSubject realSubject;
    @Override
    public void request() {
        this.preSubject();

        if(null == realSubject){
            realSubject = new RealSubject();
        }
        realSubject.request();

        this.postSubject();
    }

    private void preSubject(){
        System.out.println("pre...");
    }

    private void postSubject(){
        System.out.println("post...");
    }
}
