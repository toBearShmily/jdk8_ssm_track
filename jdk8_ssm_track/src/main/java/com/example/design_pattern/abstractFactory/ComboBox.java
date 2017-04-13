package com.example.design_pattern.abstractFactory;

/**
 * Created by Administrator on 2017/4/6.
 */
public interface ComboBox {
    public void comboBoxSkin();
}

class indexComboBox implements ComboBox{
    @Override
    public void comboBoxSkin() {
        System.out.println("首页组合框皮肤！！！");
    }
}

class formComboBox implements ComboBox{
    @Override
    public void comboBoxSkin() {
        System.out.println("表单组合框皮肤！！！");
    }
}