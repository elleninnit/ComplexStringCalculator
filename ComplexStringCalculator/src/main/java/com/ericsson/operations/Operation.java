package com.ericsson.operations;

public class Operation {

    public static int doOperation(String val1, String val2, String operator) {

        switch (operator) {
            case "+":
                return Math.addExact(Integer.parseInt(val1), Integer.parseInt(val2));
            case "-":
                return Math.subtractExact(Integer.parseInt(val1), Integer.parseInt(val2));
            case "/":
                return Math.floorDiv(Integer.parseInt(val1), Integer.parseInt(val2));
            case "*":
                return Math.multiplyExact(Integer.parseInt(val1), Integer.parseInt(val2));
            default:
                return 0;
        }
    }
}
