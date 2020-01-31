package com.pwc.codingchallenge.component.controller;

import com.pwc.codingchallenge.api.ApiError;
import com.pwc.codingchallenge.component.ComponentTest;
import com.pwc.codingchallenge.controller.GlobalExceptionHandler;
import com.pwc.codingchallenge.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;

@ComponentTest
@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Mock
    private HttpServletRequest request;

    @Test
    public void handleInvalidArgumentException_shouldReturnBadRequestError() {
        ResponseEntity<ApiError> response = exceptionHandler.handleValidationException
                (new InvalidArgumentException("Invalid Argument"), request);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("BAD_REQUEST", response.getBody().getErrorId());
    }

    @Test
    public void handleException_shouldReturnBadRequestError() {
        ResponseEntity<ApiError> response = exceptionHandler.handleException
                (new NullPointerException("Invalid Argument"), request);
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("INTERNAL_SERVER_ERROR", response.getBody().getErrorId());
    }

}
