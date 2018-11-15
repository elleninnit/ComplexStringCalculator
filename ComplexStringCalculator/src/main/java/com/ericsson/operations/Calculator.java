package com.ericsson.operations;

import java.util.ArrayDeque;
import java.util.Deque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Calculator {

    @Autowired
    private OperationFactory factory;

    public String calculateExpression(String postfixExpression) {

        String[] tokens = postfixExpression.split("\\s");
        Deque<String> evalStack = new ArrayDeque<>();

        for (String token : tokens) {
            if (!Operator.isOperator(token)) {
                evalStack.push(token);
            } else {
                int val2 = Integer.parseInt(evalStack.pop());
                int val1 = Integer.parseInt(evalStack.pop());

                Operation op = factory.executeOperation(token);
                String result = String.valueOf(op.calc(val1, val2));

                evalStack.push(result);
            }
        }
        return evalStack.pop();
    }
}
