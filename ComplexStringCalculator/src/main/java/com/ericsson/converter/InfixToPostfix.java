package com.ericsson.converter;

import java.util.*;

import org.springframework.stereotype.Component;

import com.ericsson.operations.Operator;

@Component
public class InfixToPostfix {

    /**
     *   Used to split string on certain characters but also preserve those characters as tokens
     *   https://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
     */
    private static final String SPLIT_STRING = "((?<=%1$s)|(?=%1$s))";
    
    public String convertToPostfix(String input) {

        Deque<String> operatorStack = new ArrayDeque<>();
        StringBuilder postfix = new StringBuilder(input.length());
        String[] tokens = input.split(String.format(SPLIT_STRING, "[+-/*()]"));

        for(int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (Operator.isOperator(token)) {
                popOperatorsUntilLowerOrEqualPrecedenceIsFound(token, operatorStack, postfix);
                operatorStack.push(token);
            } else if ("(".equals(token)) {
                operatorStack.push(token);
            } else if (")".equals(token)) {
                popOperatorsUntilOpenParenthesisIsFound(operatorStack, postfix);
            } else {
                postfix.append(token).append(" ");
            }
        }
        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop()).append(" ");
        }
        return postfix.toString();
    }

    private StringBuilder popOperatorsUntilLowerOrEqualPrecedenceIsFound(String token, Deque<String> operatorStack, StringBuilder postfix) {

        while (!operatorStack.isEmpty() && isHigerPrecedence(token, operatorStack.peek())) {
            postfix.append(operatorStack.pop()).append(" ");
        }

        return postfix;
    }
    
    private boolean isHigerPrecedence(String token, String tokenOnStack) {
        return (Operator.isOperator(tokenOnStack)
                && (Operator.getOperatorForChar(tokenOnStack).getPrecedence() >= Operator.getOperatorForChar(token).getPrecedence()));
    }

    private void popOperatorsUntilOpenParenthesisIsFound(Deque<String> operatorStack, StringBuilder postfix) {

        while (!operatorStack.isEmpty() && !"(".equals(operatorStack.peek())) {
            postfix.append(operatorStack.pop()).append(" ");
        }
        operatorStack.pop();
    }
}
