package com.pwc.codingchallenge.blackbox.common;

import io.restassured.response.ExtractableResponse;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
@Data
public class ScenarioContext {

    private String request;
    private ExtractableResponse extractableResponse;
    private HttpHeaders headers;
}
