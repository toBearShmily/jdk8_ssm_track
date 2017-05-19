package com.shmily.model.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 权限
 * Created by wuxubiao on 2017/5/19.
 */
@Entity
@Table(name = "permission")
public class SysPermission extends BaseEntity implements Serializable{
    /**
     * 权限名称
     */
    @Column(name = "name",length = 20)
    private String name;

    /**
     * 权限的描述
     */
    @Column(name = "description",length = 20)
    private String description;

    /**
     * 功能模块
     */
    @Column(name = "classUrl",length = 20)
    private String classUrl;

    /**
     * 操作
     */
    @Column(name = "methodUrl",length = 20)
    private String methodUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassUrl() {
        return classUrl;
    }

    public void setClassUrl(String classUrl) {
        this.classUrl = classUrl;
    }

    public String getMethodUrl() {
        return methodUrl;
    }

    public void setMethodUrl(String methodUrl) {
        this.methodUrl = methodUrl;
    }
}
