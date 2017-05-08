package com.shmily.support.weixin;

/**
 * Created by wuxubiao on 2017/5/8.
 */
public class WeiXinUser {
    private String openId;

    private String nickname;

    private Integer sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private String privilege[];

    private String unionid;

    public WeiXinUser() {
    }

    public WeiXinUser(String openId, String nickname, Integer sex, String province, String city, String country, String headimgurl) {
        this.openId = openId;
        this.nickname = nickname;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.country = country;
        this.headimgurl = headimgurl;
    }

    public WeiXinUser(String openId, String nickname, Integer sex, String province, String city, String country, String headimgurl, String[] privilege) {
        this.openId = openId;
        this.nickname = nickname;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.country = country;
        this.headimgurl = headimgurl;
        this.privilege = privilege;
    }

    public WeiXinUser(String openId, String nickname, Integer sex, String province, String city, String country, String headimgurl, String[] privilege, String unionid) {
        this.openId = openId;
        this.nickname = nickname;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.country = country;
        this.headimgurl = headimgurl;
        this.privilege = privilege;
        this.unionid = unionid;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String[] getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
