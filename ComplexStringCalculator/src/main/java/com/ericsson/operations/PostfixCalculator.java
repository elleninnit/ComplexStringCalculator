package com.ericsson.operations;

import java.util.ArrayDeque;
import java.util.Deque;

import org.springframework.stereotype.Component;

@Component
public class PostfixCalculator {
    
    public String evaluateExpression(String postfixNotation) {
        String[] tokens = postfixNotation.split("\\s");
        Deque<String> evalStack = new ArrayDeque<>();

        for (String token : tokens) {
            if (!Operator.isOperator(token)) {
                evalStack.push(token);
            } else if (Operator.isOperator(token)) {
                String val2 = evalStack.pop();
                String val1 = evalStack.pop();

                int numResult = Operation.doOperation(val1, val2, token);
                String result = Integer.toString(numResult);
                evalStack.push(result);
            }
        }
        return evalStack.pop();
    }
}
