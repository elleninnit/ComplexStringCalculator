package com.ericsson.operations;

public class Divide implements Operation {

    public String calc(String val1, String val2) {
        int result = Math.floorDiv(Integer.parseInt(val1), Integer.parseInt(val2));
        return Integer.toString(result);
    }

}
