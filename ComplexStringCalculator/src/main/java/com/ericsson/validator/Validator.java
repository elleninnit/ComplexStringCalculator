package com.ericsson.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.ericsson.operations.Operator;

@Component
public class Validator {

    public boolean validate(String input) {
        return (isValidString(input) && isValidInfix(input));
    }

    private boolean isValidString(String input) {

        final Pattern pattern = Pattern.compile("[\\s0-9+\\-\\()/*]+");
        final Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public boolean isValidInfix(String input) {

        String[] tokens = input.split("\\s");

        if (Operator.isOperator(tokens[0]) || Operator.isOperator(tokens[tokens.length - 1]) || ")".equals(tokens[0])) {
            return false;
        }

        int unclosedParenthesis = 0;
        for (int i = 0; i < tokens.length; i++) {

            if ("(".equals(tokens[i])) {
                unclosedParenthesis++;

            } else if ("(".equals(tokens[tokens.length - 1])) {
                return false;

            } else if (")".equals(tokens[i])) {
                unclosedParenthesis--;

            } else if (Operator.isOperator(tokens[i])) {

                if ("(".equals(tokens[i - 1]) || ")".equals(tokens[i + 1]) || Operator.isOperator(tokens[i + 1])) {
                    return false;
                }
            }
        }
        return (unclosedParenthesis == 0);
    }
}
