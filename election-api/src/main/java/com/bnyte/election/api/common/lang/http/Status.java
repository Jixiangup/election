package com.bnyte.election.api.common.lang.http;

/**
 * 0: 同步成功
 * 1-10: 提交成功(异步使用)等待处理
 * 10000 - 19999: 鉴权错误
 * 20000 - 39999: 微服务通信异常 但是通常在返回给响应是应该转换为正确异常
 * 40000 - 49999: 参数校验错误
 * 50000 - 59999: 系统内部错误 如: 未捕获、未定义、未知异常
 * 60000 - 69999: 。。。。还不知道再说吧
 * @author bnyte
 * @since 1.0.0
 */
public enum Status {

    /**
     * 请求成功
     */
    SUCCESSFUL(0, "successful"),

    ERROR(-1, "error"),


    ;

    public static Status code(Integer code) {
        for (Status value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new RuntimeException();
    }

    private Integer code;
    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
