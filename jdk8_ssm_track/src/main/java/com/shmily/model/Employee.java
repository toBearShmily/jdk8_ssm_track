package com.shmily.model;

import com.shmily.model.system.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/18.
 */
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity implements Serializable{

    @Column(length = 20)
    private String empName;

    @Column(length = 5)
    private Integer age;

    @Column(length = 2)
    private Integer sex;


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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        if(sex == null) this.sex = 0;
            this.sex = sex;
    }
}
