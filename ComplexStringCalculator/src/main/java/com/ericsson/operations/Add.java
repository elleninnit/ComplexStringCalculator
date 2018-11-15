package com.ericsson.operations;

public class Add implements Operation {

    @Override
    public int calc(int val1, int val2) {
        return Math.addExact(val1, val2);
    }

}
