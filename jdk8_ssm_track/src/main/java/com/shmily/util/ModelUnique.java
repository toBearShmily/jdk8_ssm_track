package com.shmily.util;

import com.alibaba.fastjson.JSON;
import com.shmily.model.Person;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.*;

/**
 * 对象中根据属性内容的去重
 * Created by wuxubiao on 2017/5/10.
 */
public class ModelUnique {
    public <E> List<E> unique(List<E> param, String property){
        Set<Object> iSet = new HashSet<Object>();
        List<E> result = (List<E>) CollectionUtils.select(param, new Predicate<E>(){
            @Override
            public boolean evaluate(E object) {
                Object val = null;
                try {
                    val = PropertyUtils.getProperty(object, property);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return iSet.add(val);
            }
        });
        return result;
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1,"jack",new Date()));
        list.add(new Person(2,"jack2",new Date()));
        list.add(new Person(1,"jack3",new Date()));
        list.add(new Person(1,"jack2",new Date()));
        System.out.println(JSON.toJSONString(new ModelUnique().unique(list,"name")));
    }
}