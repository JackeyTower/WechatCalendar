package com.echo.calendar.exception;
import org.springframework.security.core.AuthenticationException;
public class VerifyException extends AuthenticationException {
    public VerifyException(String msg) {
        super(msg);
    }
    public VerifyException(String msg, Throwable t) {
        super(msg, t);
    }
}