package com.example.async;

/**
 * Created by wuxubiao on 2017/5/3.
 */
public class Customer {
    public static void main(String[] args) {
        System.out.println("main BEGIN");
        CakeShop host = new CakeShop();
        Cake cake1 = host.request(10, 'A');
		Cake cake2 = host.request(20, 'B');
		Cake cake3 = host.request(30, 'C');
        System.out.println("main otherJob BEGIN");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        System.out.println("main otherJob END");
        System.out.println("cake1 = " + cake1.getCake());
		System.out.println("cake2 = " + cake2.getCake());
		System.out.println("cake3 = " + cake3.getCake());
        System.out.println("main END");

    }
}
