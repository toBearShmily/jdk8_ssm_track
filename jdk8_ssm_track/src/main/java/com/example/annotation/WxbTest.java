package com.example.annotation;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/3/14.
 */


public class WxbTest {

    @Wxb
    public String say(@Prams("5555") String name){

        return "computer : "+name;
    }

    public static void main(String[] args)throws Exception {
        WxbTest test = new WxbTest();
        String val = "";//需要给say赋值的变量值

        Class classType = test.getClass();
        Method method = classType.getDeclaredMethod("say",new Class[]{String.class});

        //获取该类下的方法是否有@Prams这个注解，有则获取
        Annotation annotation = method.getAnnotation(Wxb.class);
        if(annotation instanceof Wxb){//如果有@Wxb标识则取去获取方法参数注解的值




            Annotation[][] annotations = method.getParameterAnnotations();
            for(int i=0 ; i < annotations.length ; i++){
                for(int j = 0; j < annotations[i].length ; j++){
                    System.out.println(annotations[i][j]);
                }
            }
            if(annotations[0][0] instanceof  Prams){
                System.out.println("参数包含prams注解！！！");
            }

            Prams p = (Prams) annotations[0][0];
            val = p.value();
            System.out.println(val);

        }

        Object obj = method.invoke(test,new Object[]{val});

        System.out.println(obj.toString());
    }

}
