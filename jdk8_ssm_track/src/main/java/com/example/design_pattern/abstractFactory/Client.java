package com.example.design_pattern.abstractFactory;

/**
 * Created by Administrator on 2017/4/6.
 */
public class Client {
    public static void main(String[] args) {
        SkinFactory skinFactory = new indexSkinFactory();
        SkinFactory skinFactory1 = new formSkinFactory();

        skinFactory.createButtn().display();
        skinFactory.createComboBox().comboBoxSkin();
        skinFactory.createTextField().TextSkin();

        skinFactory1.createButtn().display();
        skinFactory1.createComboBox().comboBoxSkin();
        skinFactory1.createTextField().TextSkin();

    }
}
