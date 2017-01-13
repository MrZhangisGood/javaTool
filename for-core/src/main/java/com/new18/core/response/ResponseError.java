package com.new18.core.response;

/**
 * @Description: 返回的错误类
 * @author zhanglm@joyplus.com.cn 
 * @date 2016年6月9日
 * @version 1.0
 */
public class ResponseError extends RuntimeException {

    /** 消息码 */
    private Integer code;

    /** 消息内容 */
    private String msg;

    /** 数据内容 */
    private Object data;

    public ResponseError(String msg) {
        super(msg.toString());
        this.code = 0;
        this.msg = msg;
        this.data = "";
    }

    public ResponseError(Integer code, String msg, Object data) {
        super(msg.toString());
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    
}
