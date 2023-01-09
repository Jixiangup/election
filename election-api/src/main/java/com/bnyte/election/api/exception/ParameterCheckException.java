package com.bnyte.election.api.exception;

import com.bnyte.election.api.common.lang.http.Status;

/**
 * <p>
 *     校验异常
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class ParameterCheckException extends GlobalException {

    public ParameterCheckException(Status status) {
        super(status);
    }


}
