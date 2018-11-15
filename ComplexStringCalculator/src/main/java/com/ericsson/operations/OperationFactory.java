package com.ericsson.operations;

import org.springframework.stereotype.Component;

@Component
public class OperationFactory {

    private static Add add;
    private static Subtract subtract;
    private static Multiply multiply;
    private static Divide divide;

    static {
        add = new Add();
        subtract = new Subtract();
        multiply = new Multiply();
        divide = new Divide();
    }

    public Operation executeOperation(String operator) {
        if ("+".equals(operator)) {
            return add;
        } else if ("-".equals(operator)) {
            return subtract;
        } else if ("*".equals(operator)) {
            return multiply;
        } else if ("/".equals(operator)) {
            return divide;
        }
        throw new IllegalArgumentException("Invalid Operator Found");
    }
}
