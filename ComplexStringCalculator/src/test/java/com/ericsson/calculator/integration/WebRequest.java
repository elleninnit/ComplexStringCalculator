package com.ericsson.calculator.integration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class WebRequest {

    private static final String HOST_URL = "http://localhost:8080";
    private static final String ENDPOINT = "/calculator";

    public static ResponseEntity<String> calculate(String infixExpression) throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = HOST_URL + ENDPOINT + "?infixExpression=" + URLEncoder.encode(infixExpression);
        System.out.println("URL " + url);
        return restTemplate.getForEntity(url, String.class);
    }

}
