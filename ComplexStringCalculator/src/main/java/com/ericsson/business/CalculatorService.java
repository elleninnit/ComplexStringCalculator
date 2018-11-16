package com.ericsson.business;

import com.ericsson.converter.InfixToPostfix;
import com.ericsson.operations.Calculator;
import com.ericsson.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
@Service
public class CalculatorService {

    @Autowired
    private Validator validator;

    @Autowired
    private InfixToPostfix convert;

    @Autowired
    private Calculator calculate;

    @RequestMapping(method = RequestMethod.GET)
    public String calculate(@RequestParam("infixExpression") String infixExpression) {
        String infixExpressionSpacesRemoved = infixExpression.replaceAll("\\s", "");
        // NEEDED FOR INTEGRATION TEST
        String infixExpressionDecoded = infixExpressionSpacesRemoved.replaceAll("%2B", "+");

        if (validator.validate(infixExpressionDecoded)) {
            String postfixExpression = convert.convertToPostfix(infixExpressionDecoded);
            return calculate.calculateExpression(postfixExpression);
        } else {
            throw new IllegalArgumentException(
                    "Invalid expression inputted into calculator. Supported operators are ()*/-+ and 0-9. Double operators and negative numbers not supported.");
        }
    }
}