package com.echo.calendar.exception;

/**
 * @author Demon
 * @version 1.0
 * @date 2021/1/31 22:27
 * 基础异常
 */
public class BasicException extends RuntimeException {
    public BasicException() {
    }

    public BasicException(String message) {
        super(message);
    }

    public BasicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicException(Throwable cause) {
        super(cause);
    }

    public BasicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
