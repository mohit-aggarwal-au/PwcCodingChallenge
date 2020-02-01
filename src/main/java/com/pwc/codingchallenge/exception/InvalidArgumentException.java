package com.pwc.codingchallenge.exception;

public class InvalidArgumentException extends RuntimeException {

    public static final long serialVersionUID = 4328743;

    public InvalidArgumentException(String message) {
        super(message);
    }
}
