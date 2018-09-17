package converter;

import java.util.Stack;

import org.springframework.stereotype.Component;

import operations.Operator;

@Component
public class InfixToPostfix {

    private static boolean isHigerPrecedence(char ch1, char ch2) {
        return (Operator.isOperator(ch2) && (Operator.getOperatorForChar(ch2).getPrecedence() >= Operator.getOperatorForChar(ch1).getPrecedence()));
    }

    public String convertToPostfix(String input) {

        Stack<Character> operatorStack = new Stack<>();
        StringBuilder postfix = new StringBuilder(input.length());

        char c;

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);

            if (Operator.isOperator(c)) {
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
}
