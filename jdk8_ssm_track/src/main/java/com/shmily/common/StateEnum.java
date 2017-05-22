package com.shmily.common;

/**
 * 通过枚举封装异常状态及对应信息
 * Created by wuxubiao on 2017/5/22.
 */
public enum StateEnum {
    PRAMS_ERROR("100","参数异常"),
    JOSN_ERROR("520","json格式化异常"),
    NULL_ERROR("404","对象为空"),
    DATA_FORMAT_ERROR("400","时间格式化异常"),
    NOT_PERMISSION("305","没有权限"),
    SUCCESS("200","成功"),
    SYS_ERROR("-1","系统异常"),
    METHOD_ERROR("300","不支持当前请求方法"),
    CONTENT_TYPE_NOT_SUPPORTED("301","不支持当前媒体类型"),
    ERROR("500","调用接口失败"),
    CONNECT_ERROR("502","网络连接异常"),
    /**
     * 暂时这样，有需求后续添加
     * */
    ;
    private String retCode;

    private String retDesc;

    StateEnum(String retCode, String retDesc) {
        this.retCode = retCode;
        this.retDesc = retDesc;
    }

    public String getRetCode() {
        return retCode;
    }

    public String getRetDesc() {
        return retDesc;
    }
}
