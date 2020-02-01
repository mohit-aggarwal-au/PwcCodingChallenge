package com.pwc.codingchallenge.component.controller;

import com.pwc.codingchallenge.api.ApiError;
import com.pwc.codingchallenge.component.ComponentTest;
import com.pwc.codingchallenge.controller.GlobalExceptionHandler;
import com.pwc.codingchallenge.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@ComponentTest
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Test
    public void handleInvalidArgumentException_shouldReturnBadRequestError() {
        ResponseEntity<ApiError> response = exceptionHandler.handleValidationException
                (new InvalidArgumentException("Invalid Argument"));
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("BAD_REQUEST", response.getBody().getErrorId());
    }

    @Test
    public void handleException_shouldReturnBadRequestError() {
        ResponseEntity<ApiError> response = exceptionHandler.handleException
                (new NullPointerException("Invalid Argument"));
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("INTERNAL_SERVER_ERROR", response.getBody().getErrorId());
    }

}
