package com.plowers.global.exception;

import com.plowers.global.dto.response.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PlowersException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ErrorResponse errorResponse;

    public PlowersException(ErrorMap errorMap) {
        this.httpStatus = errorMap.getHttpStatus();
        this.errorResponse = ErrorResponse.of(errorMap.getHttpStatus().value(), errorMap.getErrorCode(), errorMap.getErrorName(), errorMap.getMessage());
    }

}
