package com.ericsson.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.converter.InfixToPostfix;
import com.ericsson.exceptions.InvalidArgumentException;
import com.ericsson.operations.PostfixCalculator;
import com.ericsson.validator.Validator;

@Service
public class CalculatorService {

    @Autowired
    private Validator validator;

    @Autowired
    private InfixToPostfix convert;
    
    @Autowired
    private PostfixCalculator evaluate;

    public String convert(String infixNotation) {
        
        infixNotation = infixNotation.replaceAll("\\s", "");
        infixNotation = infixNotation.replaceAll("[()\\-\\/+*]", " $0 ");
        infixNotation = infixNotation.trim().replaceAll("\\s{2,}", " ");
        
        if (validator.validate(infixNotation)) {
            String postfixNotation = convert.convertToPostfix(infixNotation);
            return evaluate.evaluateExpression(postfixNotation);
        } else {
            throw new InvalidArgumentException("Invalid input!");
        }
    }
}
