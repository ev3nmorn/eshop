package ru.ev3nmorn.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final HttpStatus status;
    private final String method;

    public ApiException(String message, HttpStatus status, String method) {
        super(message);
        this.status = status;
        this.method = method;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMethod() {
        return method;
    }
}
