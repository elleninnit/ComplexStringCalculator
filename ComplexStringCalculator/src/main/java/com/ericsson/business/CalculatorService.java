package com.ericsson.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.converter.InfixToPostfix;
import com.ericsson.operations.Calculator;
import com.ericsson.validator.Validator;

@Service
public class CalculatorService {

    @Autowired
    private Validator validator;

    @Autowired
    private InfixToPostfix convert;

    @Autowired
    private Calculator calculate;

    public String calculate(final String infixExpression) {
        
        String infixExpressionSpacesRemoved = infixExpression.replaceAll("\\s", "");
        
        if (validator.validate(infixExpressionSpacesRemoved)) {
            String postfixExpression = convert.convertToPostfix(infixExpressionSpacesRemoved);
            return calculate.calculateExpression(postfixExpression);
        } else {
            throw new IllegalArgumentException(
                    "Invalid expression inputted into calculator. Supported operators are ()*/-+ and 0-9. Double operators and negative numbers not supported.");
        }
    }
}
