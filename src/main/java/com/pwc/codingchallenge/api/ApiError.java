package com.pwc.codingchallenge.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ApiError {

    private String message;

    private String errorId;

}
