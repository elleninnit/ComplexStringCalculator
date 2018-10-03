package com.ericsson.operations;

public class Multiply implements Operation {

    @Override
    public String calc(String val1, String val2) {
        int result = Math.multiplyExact(Integer.parseInt(val1), Integer.parseInt(val2));
        return Integer.toString(result);
    }

}
