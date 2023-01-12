package com.bnyte.election.api.common.util;

import com.bnyte.election.api.common.lang.http.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author bnyte
 * @since 1.0.0
 */
public class PageUtils {

    /**
     * 分页数据转换
     * @param pageInfo page helper获取到的分页数据
     * @param targetClass 需要转换的目标字节码对象
     * @return 分页之后的数据
     * @param <T> 分页数据中的结果集的项类型
     */
    public static <T> Page<T> pageTransfer(PageInfo<?> pageInfo, Class<T> targetClass) {
        Page<T> page = new Page<>();
        page.setCurrent(Integer.toUnsignedLong(pageInfo.getPageNum()));
        page.setCurrentCount(Integer.toUnsignedLong(pageInfo.getSize()));
        page.setTotal(pageInfo.getTotal());
        page.setQueryCount(Integer.toUnsignedLong(pageInfo.getPageSize()));
        page.setPage(Integer.toUnsignedLong(pageInfo.getPages()));
        return page;
    }

}
