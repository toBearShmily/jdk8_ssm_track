package com.example.design_pattern.abstractFactory;

/**
 * 抽象产品（按钮皮肤）
 * Created by Administrator on 2017/4/6.
 */
public interface Button {
    public void display();
}

class indexButton implements Button{

    @Override
    public void display() {
        System.out.println("首页按钮皮肤！！！");
    }
}

class formButton implements Button{
    @Override
    public void display() {
        System.out.println("表单按钮皮肤！！！");
    }
}