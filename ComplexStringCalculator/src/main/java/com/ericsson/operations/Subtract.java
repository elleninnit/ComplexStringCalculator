package com.ericsson.operations;

public class Subtract implements Operation {

    @Override
    public int calc(int val1, int val2) {
        return val1 - val2;
    }

}
