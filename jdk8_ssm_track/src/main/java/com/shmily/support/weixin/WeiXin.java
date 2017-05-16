package com.shmily.support.weixin;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by Administrator on 2017/3/25.
 */
public class WeiXin {
    //测试号
    public static final String APPID = "";
    public static final String APPSECRET = "";

    //我的订阅号
    public static final String DY_APPID = "";
    public static final String DY_APPSECRET = "";

    public static final String ACCESS_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;

    public static final String TOKEN = "";

    /**
     * 消息类型
     */
    //接收、发送消息类型
    public static final String MESSAGE_TEXT = "text";

    public static final String MESSAGE_IMAGE = "image";

    public static final String MESSAGE_VOICE = "voice";

    public static final String MESSAGE_VIDEO = "video";

    public static final String MESSAGE_SHORTVIDEO = "shortvideo";

    public static final String MESSAGE_LOCATION = "location";

    public static final String MESSAGE_LINK = "link";

    public static final String MESSAGE_NEWS = "news";//图文消息

    //接收事件推送
    public static final String EVENT_PUSH = "event";

    /**
     * 关注/取消关注事件
     */
    //也包含扫描带参数二维码事件（用户未关注时，进行关注后的事件推送）
    public static final String EVENT_subscribe ="subscribe";
    public static final String EVENT_unsubscribe ="unsubscribe";


    //扫描带参数二维码事件(用户已关注时的事件推送)
    public static final String EVENT_SCAN ="SCAN";


    //上报地理位置事件
    public static final String EVENT_LOCATION ="LOCATION";


    /**
     * 自定义菜单事件
     */
    //(点击菜单拉取消息时的事件推送--点击菜单弹出子菜单，不会产生上报)
    public static final String EVENT_CLICK ="CLICK";
    //点击菜单跳转链接时的事件推送
    public static final String EVENT_VIEW ="VIEW";


}
