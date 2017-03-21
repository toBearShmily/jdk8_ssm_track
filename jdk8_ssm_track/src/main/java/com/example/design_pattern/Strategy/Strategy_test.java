package com.example.design_pattern.Strategy;

/**
 * 策略模式测试
 * 抽象策略角色：策略类，由接口或者抽象类实现
 * 具体策略角色：包装了相关的算法和功能
 * 环境角色：持有一个策略类的引入，，，供客户端调用
 * Created by Administrator on 2017/2/21.
 */
public class Strategy_test {
    public static void main(String[] args) {
        EnvironmentalStrategy ev = new EnvironmentalStrategy(new AdditionStrategy());
        int c = ev.result(2,3);
        System.out.println(c);

        EnvironmentalStrategy ev1 = new EnvironmentalStrategy(new subtracStratrgy());
        int d = ev1.result(2,3);
        System.out.println(d);

    }
}
