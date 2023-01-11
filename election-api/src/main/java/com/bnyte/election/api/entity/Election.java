package com.bnyte.election.api.entity;

/**
 * <p>
 *     选举数据库映射对象
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class Election extends AbsAutoIdEntity {

    /**
     * 当前选举状态
     * @see com.bnyte.election.api.common.enums.EElectionStatus
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
