package com.study.orderservice.exception.handler;

import com.study.orderservice.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j(topic = "error")
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<?> commonExceptionHandler(CommonException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatusCode())
                .body(e.getErrorCode().getMsg());
    }
}
