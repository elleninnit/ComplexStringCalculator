package com.ericsson.calculator;

import java.util.Stack;

import com.ericsson.calculator.Validator;

public class InfixToPostfix {

	private static Validator validator = new Validator();

	private static boolean isHigerPrecedence(char ch1, char ch2) {
		return (OperatorMap.operators.containsKey(ch2)
				&& OperatorMap.operators.get(ch2) >= OperatorMap.operators.get(ch1));
	}

	private static String convertToPostfix(String input) {

		Stack<Character> operatorStack = new Stack<Character>();
		StringBuilder postfix = new StringBuilder(input.length());

		char c;

		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);

			if (OperatorMap.operators.containsKey(c)) {
				while (!operatorStack.isEmpty() && isHigerPrecedence(c, operatorStack.peek())) {
					postfix.append(operatorStack.pop());
				}
				operatorStack.push(c);
			}

			else if (c == '(') {
				operatorStack.push(c);
			}

			else if (c == ')') {
				while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
					postfix.append(operatorStack.pop());
				}
				operatorStack.pop();
			}

			else {
				postfix.append(c);
			}
		}

		while (!operatorStack.isEmpty()) {
			postfix.append(operatorStack.pop());
		}

		return postfix.toString();
	}

	public static void main(String[] args) {

		String input = "1+((2*3)+2)";

		if (validator.validate(input)) {
			System.out.println(convertToPostfix(input));
		} else {
			System.out.println("Incorrect input!");
		}
	}
}
