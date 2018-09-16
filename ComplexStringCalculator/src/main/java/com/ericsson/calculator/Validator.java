package com.ericsson.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public boolean validate(String input) {
		return (isValidString(input) && isValidInfix(input));
	}
	
	private boolean isValidString(String input) {

		final Pattern pattern = Pattern.compile("[0-9+/()*]+");
		final Matcher matcher = pattern.matcher(input);

		return matcher.matches();
	}

	private boolean isValidInfix(String input) {
		input = input.replaceAll("\\s+", "");

		if (OperatorMap.operators.containsKey(input.charAt(0))
				|| OperatorMap.operators.containsKey(input.charAt(input.length() - 1))) {
			return false;
		}

		int unclosedParenthesis = 0;

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '(') {
				unclosedParenthesis++;

				if (i == input.length() - 1) {
					return false;
				}
			}
			if (input.charAt(i) == ')') {
				unclosedParenthesis--;
				if (i == 0) {
					return false;
				}
			}
			if (OperatorMap.operators.containsKey(input.charAt(i))) {

				if (input.charAt(i - 1) == '(' || input.charAt(i + 1) == ')'
						|| OperatorMap.operators.containsKey(input.charAt(i + 1))) {

					return false;
				}

			}

		}
		return (unclosedParenthesis == 0);
	}
}
