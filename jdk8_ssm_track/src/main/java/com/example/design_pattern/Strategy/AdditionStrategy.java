package com.example.design_pattern.Strategy;

/**
 * 具体策略角色
 * 加
 * Created by Administrator on 2017/2/21.
 */
public class AdditionStrategy implements Strategy {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
