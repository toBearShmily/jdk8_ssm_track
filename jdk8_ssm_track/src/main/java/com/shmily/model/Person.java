package com.shmily.model;

import java.util.Date;

/**
 * Created by wuxubiao on 2017/5/10.
 */
public class Person {
    private int id;
    private String name;
    private Date date;

    public Person() {
    }

    public Person(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
