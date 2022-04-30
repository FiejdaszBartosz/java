package calculate;

import java.util.Stack;

public class Calculate {
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
}
