package com.plowers.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMap {

    // Auth(1xxx)
    INVALID_EMAIL_PATTERN(HttpStatus.BAD_REQUEST, 1000, "INVALID_EMAIL_PATTERN", "잘못된 이메일 형식입니다."),
    INVALID_PASSWORD_PATTERN(HttpStatus.BAD_REQUEST, 1001, "INVALID_PASSWORD_PATTERN", "비밀번호는 영문 소문자, 영문 대문자, 숫자, 특수문자가 각각 1개 이상 포함되어야 합니다."),
    PASSWORD_TOO_SHORT(HttpStatus.BAD_REQUEST, 1002, "PASSWORD_TOO_SHORT", "비밀번호는 8자리 이상이어야합니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, 1003, "USER_ALREADY_EXISTS_ERROR", "이미 가입된 사용자입니다."),
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, 1004, "DUPLICATED_USERNAME", "이미 사용중인 이름입니다."),

    // User(2xxx)
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 2000, "USER_NOT_FOUND_ERROR", "존재하지 않는 사용자입니다.");

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