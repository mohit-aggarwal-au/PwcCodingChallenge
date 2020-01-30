package com.pwc.codingchallenge.controller;

import com.pwc.codingchallenge.api.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ValidationException.class,IllegalArgumentException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleValidationError(Exception exception, HttpServletRequest request){
        ApiError error = new ApiError();
        error.setErrorId(HttpStatus.BAD_REQUEST.name());
        error.setMessage(exception.getMessage());
        return new ResponseEntity<ApiError>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception exception, HttpServletRequest request){
        ApiError error = new ApiError();
        error.setErrorId(HttpStatus.INTERNAL_SERVER_ERROR.name());
        error.setMessage(exception.getMessage());
        return new ResponseEntity<ApiError>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
