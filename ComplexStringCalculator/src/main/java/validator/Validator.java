package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import operations.Operator;

@Component
public class Validator {

    public boolean validate(String input) {
        return (isValidString(input) && isValidInfix(input));
    }

    private boolean isValidString(String input) {

        final Pattern pattern = Pattern.compile("[0-9+\\-\\/()*]+");
        final Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    private static boolean isValidInfix(String input) {

        if (Operator.isOperator(input.charAt(0)) || Operator.isOperator(input.charAt(input.length() - 1))) {
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
            if (Operator.isOperator(input.charAt(i))) {

                if (input.charAt(i - 1) == '(' || input.charAt(i + 1) == ')' || Operator.isOperator(input.charAt(i + 1))) {
                    return false;
                }
            }
        }
        return (unclosedParenthesis == 0);
    }
}
