package com.ericsson.converter;

import java.util.*;

import org.springframework.stereotype.Component;
import com.ericsson.operations.Operator;

@Component
public class InfixToPostfix {

    private static boolean isHigerPrecedence(String ch1, String ch2) {
        return (Operator.isOperator(ch2) && (Operator.getOperatorForChar(ch2).getPrecedence() >= Operator.getOperatorForChar(ch1).getPrecedence()));
    }

    public String convertToPostfix(String input) {

        Deque<String> operatorStack = new ArrayDeque<>();
        StringBuilder postfix = new StringBuilder(input.length());
        String[] tokens = input.split("\\s");

        for (String token : tokens) {

            if (Operator.isOperator(token)) {
                while (!operatorStack.isEmpty() && isHigerPrecedence(token, operatorStack.peek())) {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(token);
            }

            else if ("(".equals(token)) {
                operatorStack.push(token);
            }

            else if (")".equals(token)) {
                while (!operatorStack.isEmpty() && !"(".equals(operatorStack.peek())) {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                operatorStack.pop();
            }

            else {
                postfix.append(token).append(" ");
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop()).append(" ");
        }
        return postfix.toString();
    }
}
