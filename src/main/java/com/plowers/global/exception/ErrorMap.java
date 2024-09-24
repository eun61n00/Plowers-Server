package com.plowers.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMap {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 1000, "USER_NOT_FOUND", "User not found"),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, 1001, "USER_ALREADY_EXISTS", "User already exists");

    private final HttpStatus httpStatus;
    private final int errorCode;
    private final String errorName;
    private final String message;

    ErrorMap(HttpStatus httpStatus, int errorCode, String errorName, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.message = message;
    }
}