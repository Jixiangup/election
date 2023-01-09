package com.bnyte.election.api.exception;

import com.bnyte.election.api.common.lang.http.Status;

/**
 * <p>
 *     校验异常
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class AuthException extends GlobalException {

    public AuthException() {
        super(Status.NO_OPERATION_PERMISSION);
    }


}
