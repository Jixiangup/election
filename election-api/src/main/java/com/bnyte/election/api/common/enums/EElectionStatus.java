package com.bnyte.election.api.common.enums;

/**
 * 选举状态
 * @author bnyte
 * @since 1.0.0
 */
public enum EElectionStatus {
    /* 未开始 */
    UN_START(0),

    /* 已开始 */
    STARTED(1),

    /* 已完成 */
    FINISHED(2),
    ;

    /**
     * 映射数据库字段状态码
     */
    private Integer code;

    EElectionStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
