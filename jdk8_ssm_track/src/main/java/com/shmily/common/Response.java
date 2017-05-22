package com.shmily.common;

/**
 * 用于统一相应格式的返回数据
 * Created by Administrator on 2017/1/3.
 */
public class Response {
    private StateEnum stateEnum;
    private Object data;

    /**
     * 无数据返回成功
     * @return
     */
    public Response success() {
        this.stateEnum = StateEnum.SUCCESS;
        return this;
    }

    /**
     * 有数据返回成功
     * @param data
     * @return
     */
    public Response success(Object data) {
        this.stateEnum = StateEnum.SUCCESS;
        this.data = data;
        return this;
    }

    /**
     * 成功返回自定义信息
     * @param retCode
     * @param retDesc
     * @param data
     * @return
     */
    public Response success(String retCode, String retDesc, Object data) {
        this.data = data;
        return this;
    }

    /**
     * 返回异常(无说明)
     * 无说明的情况下，默认系统异常
     * @return
     */
    public  Response failure(){
        this.stateEnum = StateEnum.SYS_ERROR;
        return this;
    }

    /**
     * 返回异常说明
     * @param stateEnum
     * @return
     */
    public Response failure(StateEnum stateEnum){
        this.stateEnum = stateEnum;
        return this;
    }

    /**
     * 返回异常说明,并带有数据
     * @param data
     * @return
     */
    public Response failure(StateEnum stateEnum, Object data){
        this.stateEnum = stateEnum;
        this.data = data;
        return this;
    }

    public StateEnum getStateEnum() {
        return stateEnum;
    }

    public Object getData() {
        return data;
    }
}
