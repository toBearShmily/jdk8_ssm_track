package com.example.design_pattern.proxy;

/**
 * 真实对象
 * Created by Administrator on 2017/3/13.
 */
public class RealSubject extends Subject{

    @Override
    public void request() {
        System.out.println("real subject!!!");
    }
}
