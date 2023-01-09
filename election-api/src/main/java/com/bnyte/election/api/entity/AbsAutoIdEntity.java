package com.bnyte.election.api.entity;

import java.util.Date;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class AbsAutoIdEntity {

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 数据创建时间
     */
    private Date createTime;

    /**
     * 数据最后一次更新时间
     */
    private Date modifiedTime;

    /**
     * 数据逻辑删除状态, 1/true: 已删除 0/false: 未删除 默认0/未删除
     */
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
