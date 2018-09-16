package com.ericsson.calculator;

import java.util.HashMap;
import java.util.Map;

public class OperatorMap {

	public static Map<Character, Integer> operators = new HashMap<Character, Integer>();

	static {
		operators.put('+', 2);
		operators.put('-', 2);
		operators.put('/', 4);
		operators.put('*', 4);
	}
	
}
