package com.bnyte.election.api.common.lang.http;

/**
 * <p>
 *     接口响应通知类型
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public enum ENotificationType {
    // 无任何动作
    NONE(0, null),

    // 成功消息弹窗
    SUCCESS_MESSAGE(1, null),

    // 成功通知
    SUCCESS_NOTIFICATION(2, null),

    // 错误消息弹窗
    ERROR_MESSAGE(3, null),

    // 错误通知
    ERROR_NOTIFICATION(4, null),
    ;

    private Integer type;

    private String target;

    ENotificationType(Integer type, String target) {
        this.type = type;
        this.target = target;
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
}
