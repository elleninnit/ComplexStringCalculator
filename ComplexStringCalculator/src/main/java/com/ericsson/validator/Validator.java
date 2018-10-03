package com.ericsson.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.ericsson.operations.Operator;

@Component
public class Validator {

    /**
    *   Used to split string on certain characters but also preserve those characters as tokens
    *   https://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
    */
    private static final String SPLIT_STRING = "((?<=%1$s)|(?=%1$s))";
    private static final String VALID_INFIX_REGEX = "^[0-9(]([\\s0-9+*/()-])*[0-9)]$";

    public boolean validate(String input) {
        return (isValidInfixExpression(input) && checkIfOperatorsAppearConsecutively(input) && hasBalancedParenthesis(input));
    }

    private boolean isValidInfixExpression(String input) {

        final Pattern pattern = Pattern.compile(VALID_INFIX_REGEX);
        final Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid input detected. Argument at position: ["
                    + Integer.toString(getIndexOfFailedRegexMatcher(pattern, input)) + "] is not valid operator or in illegal position.");
        } else {
            return matcher.matches();
        }
    }

    /**
     *  taken from https://stackoverflow.com/questions/5666486/how-to-determine-where-a-regex-failed-to-match-using-java-apis
     */
    private int getIndexOfFailedRegexMatcher(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        for (int i = input.length(); i > 0; --i) {
            Matcher region = matcher.region(0, i);
            if (region.matches() || region.hitEnd()) {
                return i;
            }
        }
        return 0;
    }

    private boolean hasBalancedParenthesis(String input) {

        String[] tokens = input.split(String.format(SPLIT_STRING, "[+-/*()]"));

        int unclosedParenthesis = 0;
        for (int i = 0; i < tokens.length; i++) {

            if ("(".equals(tokens[i])) {
                unclosedParenthesis++;
            } else if (")".equals(tokens[i])) {
                unclosedParenthesis--;
            }

            if (unclosedParenthesis == -1) {
                throw new IllegalArgumentException("Closing bracket found with no opening bracket at position: [" + Integer.toString(i) + "]");
            }
        }
        if (unclosedParenthesis != 0) {
            throw new IllegalArgumentException("Opening bracket found with no closed bracket");
        }
        return (unclosedParenthesis == 0);
    }

    private boolean checkIfOperatorsAppearConsecutively(String input) {

        String[] tokens = input.split(String.format(SPLIT_STRING, "[+-/*()]"));

        for (int i = 0; i < tokens.length; i++) {
            if (Operator.isOperator(tokens[i])) {

                if ("(".equals(tokens[i - 1]) || ")".equals(tokens[i + 1]) || Operator.isOperator(tokens[i + 1])) {
                    throw new IllegalArgumentException("Double operators found: [" + tokens[i] + tokens[i + 1] + "] at postition ["
                            + Integer.toString(i) + "]. Double operators not supported!");
                }
            }
        }
        return true;
    }
}
