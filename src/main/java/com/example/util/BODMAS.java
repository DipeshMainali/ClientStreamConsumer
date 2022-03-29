package com.example.util;

public class BODMAS {
    // A utility function to check if a given character is operand
    private static boolean isOperand(char c) {
        return (c >= '0' && c <= '9');
    }

    // utility function to find value of and operand
    private static int value(char c) {
        return (int) (c - '0');
    }

    // This function evaluates simple expressions.
    // It returns -1111111 if the given expression is invalid.
    public static int evaluate(String exp) {
        // Base Case: Given expression is empty
        if (exp.length() == 0) return -1111111;

        // The first character must be an operand, find its value
        int res = value(exp.charAt(0));

        // Traverse the remaining characters in pairs
        for (int i = 1; i < exp.length(); i += 2) {
            // The next character must be an operator, and next to next an operand
            char opr = exp.charAt(i), opd = exp.charAt(i + 1);

            // If next to next character is not an operand
            if (!isOperand(opd)) return -1111111;

            // Update result according to the operator
            if (opr == '+') {
                res += value(opd);
            }
            else if (opr == '-') {
                res -= value(opd);
            }
            else if (opr == '*') {
                res *= value(opd);
            }
            else if (opr == '/') {
                res /= value(opd);
            }
            else {
                // If not a valid operator
                return -1111111;
            }
        }
        return res;
    }
}
