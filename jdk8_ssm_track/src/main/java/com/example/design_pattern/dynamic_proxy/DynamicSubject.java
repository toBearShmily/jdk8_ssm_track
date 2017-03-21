package com.example.design_pattern.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/3/13.
 */
public class DynamicSubject implements InvocationHandler {

    private boolean isUpdate;
    private Object object;
    //通过构造函数传入需要代理的对象即赋予上面的object
    public DynamicSubject(Object obj,boolean isUpdate){
        this.object = obj;
        this.isUpdate = isUpdate;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before:"+method);

        if(isUpdate == true){
            String str = (String)method.invoke(object,args);
            System.out.println("等于true时："+str);
        }else{
            System.out.println("你毛有修改的权限...");
        }


        System.out.println("after:"+method);
        return null;
    }
}
