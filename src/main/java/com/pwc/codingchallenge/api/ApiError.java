package com.pwc.codingchallenge.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiError {

    private String errorId;

    private String message;

}
