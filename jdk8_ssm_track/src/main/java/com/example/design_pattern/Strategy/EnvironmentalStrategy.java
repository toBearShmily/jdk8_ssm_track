package com.example.design_pattern.Strategy;

/**
 * 环境策略角色
 * Created by Administrator on 2017/2/21.
 */
public class EnvironmentalStrategy {
    private Strategy strategy;

    public EnvironmentalStrategy (Strategy strategy){
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int result(int a, int b){
        return strategy.calculate(a,b);
    }
}
