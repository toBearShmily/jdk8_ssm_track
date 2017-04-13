package com.example.design_pattern.abstractFactory;

/**
 * Created by Administrator on 2017/4/6.
 */
public interface TextField {
    public void TextSkin();
}

class indexTextField implements TextField{
    @Override
    public void TextSkin() {
        System.out.println("首页文本皮肤！！！");
    }
}

class formTextField implements TextField{
    @Override
    public void TextSkin() {
        System.out.println("表单文本皮肤！！！");
    }
}