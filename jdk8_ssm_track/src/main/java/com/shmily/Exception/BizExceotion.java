package com.shmily.Exception;

/**
 * Created by wuxubiao on 2017/5/22.
 */
public class BizExceotion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 构造一个基本异常.
     *
     * @param message
     *            信息描述
     */
    public BizExceotion(String message) {
        super(message);
    }

    /**
     * 构造一个基本异常.
     * @param errorCode
     *            错误编码
     * @param message
     *            信息描述
     */
    public BizExceotion(String errorCode, String message) {
        super(message);
        this.setErrorCode(errorCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
