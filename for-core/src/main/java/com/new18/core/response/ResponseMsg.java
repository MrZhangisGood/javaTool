package com.new18.core.response;

/**
 * @Description: 返回数据的消息类
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月12日 下午10:09:05   
 * @version 1.0
 */
public class ResponseMsg {

    public static Integer SUCCESS = 0;
    public static Integer FAIL = 1;

    /** 消息码 */
    private Integer error;
    
    /** 消息内容 */
    private String message;
    
    /** 数据内容 */
    private Object data;

    public ResponseMsg() {
        this.error = SUCCESS;
        this.message = "";
        this.data = "";
    }
    
    public ResponseMsg(boolean result, Integer code, String message, Object data) {
        this.error = code;
        this.message = message;
        this.data = data;
    }

    public Integer getError() {
        return error;
    }
    public void setError(Integer error) {
        this.error = error;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @Description:返回失败
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    public ResponseMsg fail() {
        this.error = FAIL;
        return this;
    }
    /**
     * @Description:返回失败
     * @author zhanglm@joyplus.com.cn
     * @return
     */
    public ResponseMsg fail(String message, Object data) {
        this.error = FAIL;
        this.message = message;
        this.data = data;
        return this;
    }
    
    /**
     * @Description:返回成功
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    public ResponseMsg success() {
        this.error = SUCCESS;
        return this;
    }
    /**
     * @Description:返回成功
     * @author zhanglm@joyplus.com.cn
     * @return
     */
    public ResponseMsg success(String message, Object data) {
        this.error = SUCCESS;
        this.message = message;
        this.data = data;
        return this;
    }
    
}
