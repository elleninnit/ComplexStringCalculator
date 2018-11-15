package com.ericsson.calculator.integration;

import com.ericsson.calculator.integration.WebRequest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.websocket.DecodeException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static org.junit.Assert.assertEquals;

public class Steps {

    private ResponseEntity<String> response;

    @When("^A user sends a call to calculate a string expression \"([^\"]*)\"$")
    public void a_user_sends_a_call_to_calculate_a_string_expression(String infixExpression) throws UnsupportedEncodingException {
        response = WebRequest.calculate(infixExpression);
    }

    @Then("^The user receives back a correct string expression \"([^\"]*)\"$")
    public void the_user_receives_back_a_correct_string_expression(String result) throws UnsupportedEncodingException {
        assertEquals(result, response.getBody());
    }

}
