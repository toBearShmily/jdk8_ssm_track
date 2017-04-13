package com.example.design_pattern.abstractFactory;

/**
 * 抽象工厂类
 * Created by Administrator on 2017/4/6.
 */
public interface SkinFactory {

    public Button createButtn();

    public TextField createTextField();

    public ComboBox createComboBox();
}

//首页皮肤工厂（具体工厂）
class indexSkinFactory implements SkinFactory{
    @Override
    public Button createButtn() {
        return new indexButton();
    }

    @Override
    public TextField createTextField() {
        return new indexTextField();
    }

    @Override
    public ComboBox createComboBox() {
        return new indexComboBox();
    }
}

//表单皮肤工厂（具体工厂）
class formSkinFactory implements SkinFactory{
    @Override
    public Button createButtn() {
        return new formButton();
    }

    @Override
    public TextField createTextField() {
        return new formTextField();
    }

    @Override
    public ComboBox createComboBox() {
        return new formComboBox();
    }
}

