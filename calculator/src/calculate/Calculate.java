package calculate;

import java.util.Stack;

public class Calculate {
    public static double compute(char symbol, double a, double b) {
        switch (symbol) {
            case '+' -> { return a + b; }
            case '-' -> { return a - b; }
            case 'x' -> { return a * b; }
            case '/' -> { return a / b; }
            default -> { return -1; } // error to do
        }
    }

    public static double calculateExpression(String input) {
        Stack<Character> stack = new Stack<>();
        double result = 0;

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);

            if (Character.isLetterOrDigit(character))
                stack.push(character);
            else {
                char x = 'x';
                double temp = (double) x;
                //stack.push();
            }
        }
        return 0;
    }
}
