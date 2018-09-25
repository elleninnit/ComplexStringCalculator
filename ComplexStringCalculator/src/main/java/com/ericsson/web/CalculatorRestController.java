package com.ericsson.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
public class CalculatorRestController {

    @Autowired
    private CalculatorService calculatorService;

    @RequestMapping(method = RequestMethod.GET, value = "/convert")
    public String convertString(@RequestParam String infixNotation) {
        infixNotation = infixNotation.replace("%2B", "+");
        infixNotation = infixNotation.replace("%2F", "/");

        return (calculatorService.convert(infixNotation));
    }
}
