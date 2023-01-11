package com.bnyte.election.api.common.util;

import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Properties;

/**
 * <p>
 *     字符串工具类
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class StringUtils {

    /**
     * Spring模版字符串占位符替换对象
     */
    private static final PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");

    public static boolean hasText(String str) {
        return org.springframework.util.StringUtils.hasText(str);
    }

    public static boolean nonText(String str) {
        return !org.springframework.util.StringUtils.hasText(str);
    }

    public static String replacePlaceholders(String source, Properties properties) {
        if (StringUtils.nonText(source)) {
            return source;
        }
        return helper.replacePlaceholders(source, properties);
    }
}
