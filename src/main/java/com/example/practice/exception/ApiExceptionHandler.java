package com.example.practice.exception;

import com.example.practice.config.Translator;
import com.example.practice.domain.dto.response.ResponseData;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData<?> handleAllException(Exception ex, WebRequest request) {
        log.error("System error: {}", ex.getMessage());
        return new ResponseData<>()._fail(Translator.toLocale("fail"));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseData<?> handleFeignException(Exception ex, WebRequest request) {
        log.error("Feign error: {}", ex.getMessage());
        return new ResponseData<>()._fail(Translator.toLocale("network"));
    }

}
