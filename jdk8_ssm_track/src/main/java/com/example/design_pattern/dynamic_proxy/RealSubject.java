package com.example.design_pattern.dynamic_proxy;

/**
 * Created by Administrator on 2017/3/13.
 */
public class RealSubject implements Subject {
    @Override
    public String request(String name) {
        return "我是："+name+"已经执行修改...";
    }
}
