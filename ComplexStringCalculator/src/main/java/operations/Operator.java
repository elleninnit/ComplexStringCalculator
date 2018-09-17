package operations;

import org.springframework.stereotype.Component;

@Component
public enum Operator {

    ADD('+', 2), SUBTRACT('-', 2), DIVIDE('/', 4), MULTIPLY('*', 4);

    private char operator;
    private int precedence;

    private Operator(char operator, int precedence) {
        this.operator = operator;
        this.precedence = precedence;
    }

    public char getOperator() {
        return operator;
    }

    public int getPrecedence() {
        return precedence;
    }

    public static Operator getOperatorForChar(char c) {
        for (Operator val : values()) {
            if (c == val.operator) {
                return val;
            }
        }
        return null;
    }
    
    public static boolean isOperator(char op) {

        Operator[] operators = Operator.values();
        for (Operator operator : operators) {
            if (operator.getOperator() == op) {
                return true;
            }
        }
        return false;
    }
}
