package com.pwc.codingchallenge.blackbox.config;

import io.restassured.RestAssured;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = SpringConfig.class)
public class Setup {

    private static final String BASE_URI = "http://localhost";
    private static final Integer PORT = 8090;

    @Before
    public void setup(){
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = PORT;
    }
}
