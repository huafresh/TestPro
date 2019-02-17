package com.hua.testpro2;

import java.util.Stack;

/**
 * Created by hua on 2019/2/15.
 */

public class Suffix {

    private String infix;
    private String suffix;

    public Suffix(String infix) {
        this.infix = infix;
        suffix = infixToSuffix(infix);
    }

    public void printSuffix() {
        System.out.println("suffix = " + suffix);
    }

    public void calculateSuffix() {
        System.out.println("calculate suffix = " + calculateSuffix(suffix));
    }

    public void calculateSuffix2(String suffix2) {
        System.out.println("calculate suffix = " + calculateSuffix(suffix2));
    }

    private static int calculateSuffix(String suffix) {
        Stack<Integer> stack = new Stack<>();

        int len = suffix.length();
        for (int i = 0; i < len; i++) {
            char c = suffix.charAt(i);

            if(c == ' '){
                continue;
            }

            if (!isOperator(c)) {
                StringBuilder numBuilder = new StringBuilder();
                do {
                    numBuilder.append(c);
                } while (++i < len && suffix.charAt(i) != ' ');
                i--;
                stack.push(Integer.valueOf(numBuilder.toString()));
            } else {
                if (stack.size() < 2) {
                    throw new IllegalStateException("less than 2 number");
                }

                int num1 = stack.pop();
                int num2 = stack.pop();

                stack.push(arithmetic(num1, num2, c));
            }
        }

        return stack.pop();
    }

    private static int arithmetic(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
        }

        throw new IllegalArgumentException("unSupport operator");
    }

    private static String infixToSuffix(String infix) {

        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        int len = infix.length();
        for (int i = 0; i < len; i++) {
            char c = infix.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (isOperator(c)) {

                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    while (!stack.isEmpty()) {
                        char top = stack.pop();
                        if (top != '(') {
                            appendChar(result, top);
                        } else {
                            break;
                        }
                    }
                } else{
                    while (!stack.isEmpty() &&
                            priority(stack.peek()) >= priority(c)) {
                        appendChar(result, stack.pop());
                    }
                    stack.push(c);
                }
            } else {
                StringBuilder builder = new StringBuilder();
                char temp = c;
                do {
                    builder.append(temp);
                } while (++i < len && (!isOperator(temp = infix.charAt(i))));
                i--;
                result.append(builder.toString());
                result.append(" ");
            }
        }

        while (!stack.isEmpty()) {
            appendChar(result, stack.pop());
        }

        return result.toString();
    }

    private static boolean isTopMorePriority(char cur, char top) {
        return ((top == '+' || top == '-') && (cur == '+' || cur == '-')) || top == '*' || top == '/';

    }

    private static int priority(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            case '(':
                return -1;
        }
        throw new IllegalArgumentException("invalid operator");
    }

    private static void appendChar(StringBuilder result, char c) {
        result.append(c);
        result.append(" ");
    }

    private static boolean isOperator(char c) {
        switch (c) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
            case ')':
                return true;
        }
        return false;
    }

    private static boolean isKuoHao(char c) {
        switch (c) {
            case '(':
            case ')':
                return true;
        }
        return false;
    }

    private static boolean isLeftKuoHao(char c) {
        switch (c) {
            case '(':
                return true;
        }
        return false;
    }

    private static boolean isRightKuoHao(char c) {
        switch (c) {
            case ')':
                return true;
        }
        return false;
    }

}
