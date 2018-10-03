package com.ericsson.operations;

public class Subtract implements Operation {

    @Override
    public String calc(String val1, String val2) {
        int result = Math.subtractExact(Integer.parseInt(val1), Integer.parseInt(val2));
        return Integer.toString(result);
    }

}
