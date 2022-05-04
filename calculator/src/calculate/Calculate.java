package calculate;

import exceptions.InvalidInputException;
import shuntingYard.ShuntingYard;

import java.util.Stack;

public class Calculate {
    /**
     * Performs a math operation based on the given char
     * @param symbol math operator
     * @param a left side value
     * @param b right side value
     * @return result of the mathematical operation
     */
    public static double compute(char symbol, double a, double b) {
        switch (symbol) {
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            case 'x' -> {
                return a * b;
            }
            case '/' -> {
                return a / b;
            }
            default -> {
                return -1;
            } // error to do
        }
    }

    /**
     * Evaluates the expression in Reverse Polish notation
     * @param input expression in Reverse Polish notation
     * @return computed expression
     */
    public static double calculateExpression(String input) {
        Stack<String> stack = new Stack<>();
        String a, b, temp = "";
        double result = 0;

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);

            if (Character.isLetterOrDigit(character) && character != 'x' || character == '.' || character == ' ') {
                if (character != ' ')
                    temp += character;
                else {
                    stack.push(temp);
                    temp = "";
                }
            } else {
                b = stack.pop();
                a = stack.pop();
                result = compute(character, Double.valueOf(a), Double.valueOf(b));
                stack.push(String.valueOf(result));
                i++;    // Skipping space after sign
            }
        }
        return Double.valueOf(stack.pop());
    }

    public static double solve(String input) throws InvalidInputException{
        try {
            return calculateExpression(ShuntingYard.toPostfixNotation(input));
        } catch (Exception e) {
            throw new InvalidInputException();
        }
    }
}
