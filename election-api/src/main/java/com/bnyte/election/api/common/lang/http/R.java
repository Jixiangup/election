package com.bnyte.election.api.common.lang.http;

/**
 * <p>
 *     统一接口响应结果集
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class R<T> {

    /**
     * 响应业务状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 本次请求是否成功
     *  true: 成功
     *  false: 失败
     */
    private Boolean success = false;

    /**
     * 请求完成之后的通知类型
     */
    private Integer type = ENotificationType.NONE.getType();

    /**
     * 通知类型目标值
     */
    private String target = ENotificationType.NONE.getTarget();

    /**
     * 本次请求id(由后台生成, 每次请求都会生成唯一请求id用于定位错误日志)
     */
    private String requestId;

    /**
     * 本次请求时间戳
     */
    private Long timestamp;

    /**
     * 本次请求响应数据
     */
    private T date;

    private R() {
    }

    private R(Integer code, String message, Boolean success, Integer type, String target, T date) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.type = type;
        this.target = target;
        this.date = date;
    }

    private R(Integer code, String message, Boolean success, Integer type, String target, String requestId, T date) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.type = type;
        this.target = target;
        this.requestId = requestId;
        this.date = date;
    }



    public static R<Void> EMPTY() {
        R<Void> r = new R<>();
        r.code = Status.SUCCESSFUL.getCode();
        r.success = true;
        r.type = ENotificationType.NONE.getType();
        r.target = ENotificationType.NONE.getTarget();
        return r;
    }

    public static R<Void> EMPTY(ENotificationType notification) {
        R<Void> r = new R<>();
        r.code = Status.SUCCESSFUL.getCode();
        r.success = true;
        r.type = notification.getType();
        r.target = notification.getTarget();
        return r;
    }

    public static R<Void> ERROR() {
        R<Void> r = new R<>();
        r.code = Status.ERROR.getCode();
        r.success = false;
        r.type = ENotificationType.NONE.getType();
        r.target = ENotificationType.NONE.getTarget();
        return r;
    }

    public static R<Void> ERROR(Status status) {
        R<Void> r = new R<>();
        r.code = status.getCode();
        r.success = false;
        r.type = ENotificationType.NONE.getType();
        r.target = ENotificationType.NONE.getTarget();
        r.message = status.getMessage();
        return r;
    }

    public static R<Void> ERROR(Status status, ENotificationType notification) {
        R<Void> r = new R<>();
        r.code = status.getCode();
        r.success = false;
        r.type = notification.getType();
        r.target = notification.getTarget();
        return r;
    }

    public static <T> R<T> OK(T data) {
        R<T> r = new R<>();
        r.code = Status.SUCCESSFUL.getCode();
        r.success = true;
        r.type = ENotificationType.NONE.getType();
        r.target = ENotificationType.NONE.getTarget();
        r.date = data;
        return r;
    }

    public static <T> R<T> OK(T data, ENotificationType notification) {
        R<T> r = new R<>();
        r.code = Status.SUCCESSFUL.getCode();
        r.success = true;
        r.type = notification.getType();
        r.target = notification.getTarget();
        r.date = data;
        return r;
    }

    public R<T> message(String message) {
        this.message = message;
        return this;
    }

    public R<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public R<T> notification(ENotificationType notificationType) {
        this.type = notificationType.getType();
        this.target = notificationType.getTarget();
        return this;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
