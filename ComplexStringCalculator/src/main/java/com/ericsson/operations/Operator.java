package com.ericsson.operations;

public enum Operator {

    ADD("+", 2), SUBTRACT("-", 2), DIVIDE("/", 4), MULTIPLY("*", 4);

    private String sign;
    private int precedence;

    private Operator(String sign, int precedence) {
        this.sign = sign;
        this.precedence = precedence;
    }

    public String getOperator() {
        return sign;
    }

    public int getPrecedence() {
        return precedence;
    }

    public static Operator getOperatorForChar(String c) {
        for (Operator val : values()) {
            if (c.equals(val.sign)) {
                return val;
            }
        }
        return null;
    }
    
    public static boolean isOperator(String op) {

        Operator[] operators = Operator.values();
        for (Operator operator : operators) {
            if (operator.getOperator().equals(op)) {
                return true;
            }
        }
        return false;
    }
}
