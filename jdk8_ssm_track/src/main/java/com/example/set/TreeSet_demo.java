package com.example.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/2/20.
 */
public class TreeSet_demo {
    public static void main(String[] args){
        TreeSet set = new TreeSet(new MyComparator());
        Person p1 = new Person("11");
        Person p2 = new Person("33");
        Person p3 = new Person("22");
        Person p4 = new Person("44");
        Person p5 = new Person("55");
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        set.add(p5);
        System.out.println(set);

        System.out.println("------------------");
        System.out.println(" ");
        for(Iterator itr = set.iterator(); itr.hasNext();){
            Person person = (Person) itr.next();
            System.out.println(person.nickName);
        }

    }
}

class Person{
    String nickName;
    public Person(String name){
        this.nickName = name;
    }

    @Override
    public String toString() {
        return this.nickName;
    }
}

class MyComparator implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        String str1 = String.valueOf(o1);
        String str2 = String.valueOf(o2);
        return str2.compareTo(str1);
    }
}