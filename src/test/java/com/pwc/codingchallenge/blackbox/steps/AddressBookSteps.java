package com.pwc.codingchallenge.blackbox.steps;

import com.pwc.codingchallenge.blackbox.common.ScenarioContext;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.given;

public class AddressBookSteps {

    public static final String ADDRESS_BOOK_PATH = "ms-address-book/address";

    @Autowired
    private ScenarioContext scenarioContext;

    @When("^api is fired$")
    public void abc(){
        scenarioContext.setExtractableResponse(given().contentType(ContentType.JSON)).when().get(ADDRESS_BOOK_PATH));
    }
}
