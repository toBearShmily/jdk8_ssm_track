package com.shmily.common;

/**
 * 用于统一相应格式的返回数据
 * Created by Administrator on 2017/1/3.
 */
public class Response {
    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;
    private Object data;

    /**
     * 无数据返回成功
     * @return
     */
    public Response success() {
        this.meta = new Meta(true,OK);
        return this;
    }

    /**
     * 有数据返回成功
     * @param data
     * @return
     */
    public Response success(Object data) {
        this.meta = new Meta(true,OK);
        this.data = data;
        return this;
    }

    /**
     * 返回异常(无说明)
     * @return
     */
    public  Response failure(){
        this.meta = new Meta(false,ERROR);
        return this;
    }

    /**
     * 返回异常说明
     * @param message
     * @return
     */
    public Response failure(String message){
        this.meta = new Meta(false,message);
        return this;
    }

    public Response failure(Object data){
        this.meta = new Meta(false,ERROR);
        this.data = data;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    public class Meta {

        private boolean success;
        private String message;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
