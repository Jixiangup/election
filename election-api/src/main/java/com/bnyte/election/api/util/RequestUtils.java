package com.bnyte.election.api.util;

import java.util.UUID;

/**
 * <p>
 *     请求工具类
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class RequestUtils {

    public static String generateRequestId() {
        return UUID.randomUUID().toString();
    }

}
