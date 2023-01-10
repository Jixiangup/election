package com.bnyte.election.api.util;

/**
 * <p>
 *     字符串工具类
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class StringUtils {

    public static boolean hasText(String str) {
        return org.springframework.util.StringUtils.hasText(str);
    }

    public static boolean nonText(String str) {
        return !org.springframework.util.StringUtils.hasText(str);
    }
}
