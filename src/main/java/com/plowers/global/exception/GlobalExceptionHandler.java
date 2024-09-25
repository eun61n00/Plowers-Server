package com.plowers.global.exception;

import com.plowers.global.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice   // 전역 예외 처리기를 정의하는 어노테이션
@Slf4j  // 로깅
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> defaultExceptionHandler(Exception e) {
        log.error("[" + e.getClass().getSimpleName() + "] " + e.getMessage());
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(INTERNAL_SERVER_ERROR.value(), 9999, e.getClass().getSimpleName(), e.getMessage()));
    }

    @ExceptionHandler(PlowersException.class)
    public ResponseEntity<Object> plowersException(PlowersException e) {
        log.error("[" + e.getClass().getSimpleName() + "] " + e.getErrorResponse().getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(e.getErrorResponse());
    }

}
