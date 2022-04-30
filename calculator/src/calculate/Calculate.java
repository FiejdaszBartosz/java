package calculate;

import java.util.Stack;

public class Calculate {
    private Stack<String> mStack;
    public String mExpression;

    public Calculate() {
        this.mStack = new Stack<>();
    }

    private void addToStack(char input) {
        if (mStack.isEmpty())
            mStack.push(String.valueOf(input));
        else {
            if (mStack.peek().charAt(0) == '.') {
                mStack.pop();
                String temp = mStack.pop() + "." + input;
                mStack.push(temp);
            } else
                mStack.push(String.valueOf(input));
        }
    }

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

    public double calculateExpression(String input) {
        String a, b;
        double temp = 0;

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);

            if (Character.isLetterOrDigit(character) || character == '.')
                addToStack(character);
            else {
                b = mStack.pop();
                a = mStack.pop();
                temp = compute(character, Double.valueOf(a), Double.valueOf(b));
                mStack.push(String.valueOf(temp));
            }
        }
        return Double.valueOf(mStack.pop());
    }
}
