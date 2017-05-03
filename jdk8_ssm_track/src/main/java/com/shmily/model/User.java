package com.shmily.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


public class User implements Serializable{
    private Integer id;

    @Pattern(regexp="^[^><&#]{4,50}$",message="{username.pattern}")
    @NotEmpty(message="{username.not.empty}")
    private String nickName;

    @NotEmpty(message = "{password.not.null}")
    private String password;

    private Integer roleId;

    private Integer sex;

    private String img;

    private Integer isProxy;

    public User() {
    }

    public User(Integer id, String nickName, String password, Integer roleId, Integer sex, String img, Integer isProxy) {
        this.id = id;
        this.nickName = nickName;
        this.password = password;
        this.roleId = roleId;
        this.sex = sex;
        this.img = img;
        this.isProxy = isProxy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getIsProxy() {
        return isProxy;
    }

    public void setIsProxy(Integer isProxy) {
        this.isProxy = isProxy;
    }

}