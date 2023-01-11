package com.bnyte.election.api.common.util;

import java.util.Collection;

/**
 * 集合工具类
 * @author bnyte
 * @since 1.0.0
 */
public class CollectionUtils {

    /**
     * 校验元素是否为空
     * @param collection 需要被校验的元素
     * @return true为空 false不为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return org.springframework.util.CollectionUtils.isEmpty(collection);
    }

}
