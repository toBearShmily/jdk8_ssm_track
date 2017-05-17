package com.shmily.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/5/18.
 */
@Entity
@Table(name = "employee")
public class Employee {

    @GeneratedValue
    @Id
    private Integer id;

    @Column(length = 20)
    private String empName;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
