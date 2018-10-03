package com.ericsson.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ericsson.business.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorRestController {

    @Autowired
    private CalculatorService calculatorService;

    @RequestMapping(method = RequestMethod.GET)
    public String calculateString(@RequestParam String infixExpression) {
        return (calculatorService.calculate(infixExpression));
    }
}
