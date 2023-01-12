package com.bnyte.election.api.param;

/**
 * @author bnyte
 * @since 1.0.0
 */
public abstract class AbsSearch {

    /**
     * 当前页码
     *  默认第一页
     */
    private Long current = 1L;

    /**
     * 每页查询记录数
     *  默认每页显示20条
     */
    private Long queryCount = 20L;

    /**
     * 通用查询关键字
     */
    private String key;

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(Long queryCount) {
        this.queryCount = queryCount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
