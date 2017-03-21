package com.example.set;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/2/21.
 *
 * reverseOrder() 对需要排序的集合进行自然数排序的反序
 */
public class Fast_sort {
    public static void main(String[] args){
        LinkedList list = new LinkedList();
        list.add(new Integer(8));
        list.add(new Integer(-20));
        list.add(new Integer(20));
        list.add(new Integer(-8));

        Comparator comparator = Collections.reverseOrder();
        Collections.sort(list,comparator);
        System.out.println(list);

        System.out.println("--------------------");

        //打乱数据
        Collections.shuffle(list);
        System.out.println(list);

        //最大最小值
        System.out.println(Collections.min(list));
        System.out.println(Collections.max(list));
    }

}
