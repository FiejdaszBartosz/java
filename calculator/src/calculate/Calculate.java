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
        double result = 0;

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);

            if (Character.isLetterOrDigit(character))
                stack.push(String.valueOf(character));
            else {
                String firstNumber = stack.pop();
                if (stack.peek().charAt(0) == '.') {

                    // to do float operations

                } else {
                    String secondNumber = stack.pop();
                    double newNumber = compute(character
                            , Double.valueOf(firstNumber)
                            , Double.valueOf(secondNumber));
                    stack.push(String.valueOf(newNumber));
                }

            }
        }
        return Double.valueOf(stack.pop());
    }
}
