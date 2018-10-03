package com.ericsson.operations;

import org.springframework.stereotype.Component;

@Component
public class OperationFactory {
    
    public Operation executeOperation(String operator) {
        if ("+".equals(operator)) {
            return new Add();
        } else if ("-".equals(operator)) {
            return new Subtract();
        } else if ("*".equals(operator)) {
            return new Multiply();
        } else if ("/".equals(operator)) {
            return new Divide();
        }
        return null;
    }
    
}
