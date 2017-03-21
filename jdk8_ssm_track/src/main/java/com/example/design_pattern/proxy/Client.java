package com.example.design_pattern.proxy;

/**
 * Created by Administrator on 2017/3/13.
 */
public class Client {

    public static void main(String[] args) {
        Subject proxy = new ProxySubject();
        proxy.request();
    }

}
