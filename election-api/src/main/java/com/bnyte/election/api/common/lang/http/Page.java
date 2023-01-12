package com.bnyte.election.api.common.lang.http;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象统一结果集
 * @author bnyte
 * @since 1.0.0
 */
public class Page<T> implements Serializable {

    /**
     * 当前页码
     */
    private Long current;

    /**
     * 查询记录数
     */
    private Long queryCount;

    /**
     * 当前查询到的记录书
     */
    private Long currentCount;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页码
     */
    private Long page;

    /**
     * 查询记录数
     */
    private List<T> records;

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

    public Long getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Long currentCount) {
        this.currentCount = currentCount;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
