package com.example.design_pattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */
public class Client {
    public static void main(String[] args) {
        List<Object> products = new ArrayList<>();
        products.add("11");
        products.add("22");
        products.add("33");
        products.add("44");
        products.add("55");
        products.add("66");

        AbstractObjectList list;
        AbstractIterator iterator;

        list = new ProductList(products); // 创建聚合对象
        iterator = list.createIterator(); // 创建迭代器对象

        System.out.println("正向遍历：");
        while (!iterator.isLast()) {
            System.out.print(iterator.getNextItem() + "，");
            iterator.next();
        }
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("逆向遍历：");
        while (!iterator.isFirst()) {
            System.out.print(iterator.getPreviousItem() + "，");
            iterator.previous();
        }
    }
}
