package com.bnyte.election.api.exception;

import com.bnyte.election.api.common.lang.http.Status;

/**
 * <p>
 *     全局异常, 所有异常根父类, 用于方便管理API异常code定位
 * </p>
 * @author bnyte
 * @since 1.0.0
 */
public class GlobalException extends RuntimeException {

    private Status status;

    public GlobalException() {
    }

    public GlobalException(Status status) {
        super(status.getMessage());
        this.status = status;
    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

    public GlobalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
