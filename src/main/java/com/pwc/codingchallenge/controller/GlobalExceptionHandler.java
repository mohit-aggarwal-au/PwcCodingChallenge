package com.pwc.codingchallenge.controller;

import com.pwc.codingchallenge.api.ApiError;
import com.pwc.codingchallenge.exception.InvalidArgumentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ValidationException.class, IllegalArgumentException.class, ConstraintViolationException.class,
            MethodArgumentNotValidException.class, InvalidArgumentException.class})
    public ResponseEntity<ApiError> handleValidationException(Exception exception) {
        ApiError error = ApiError.builder().errorId(HttpStatus.BAD_REQUEST.name())
                .message(exception.getMessage()).build();
        return new ResponseEntity<ApiError>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception exception) {
        ApiError error = ApiError.builder().errorId(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(exception.getMessage()).build();
        return new ResponseEntity<ApiError>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
