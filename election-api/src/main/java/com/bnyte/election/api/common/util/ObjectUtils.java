package com.bnyte.election.api.common.util;

import java.util.Objects;

/**
 * <p>
 *     对象工具类
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class ObjectUtils {

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    public static boolean nonNull(Object obj) {
        return Objects.nonNull(obj);
    }

}
