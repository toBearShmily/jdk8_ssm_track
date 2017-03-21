package com.example.design_pattern.Strategy;

/**
 * 具体策略角色
 * 减
 * Created by Administrator on 2017/2/21.
 */
public class subtracStratrgy implements Strategy {
    @Override
    public int calculate(int a, int b) {
        return a - b;
    }
}
