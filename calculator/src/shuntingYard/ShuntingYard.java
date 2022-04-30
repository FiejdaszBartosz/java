package shuntingYard;

import java.util.Stack;

public class ShuntingYard {
    /**
     * Sets the priority for symbol (Using in toPostfixNotation)
     * @param symbol math symbol
     * @return priority
     */
    private static int getPriority(char symbol) {
        if (symbol == '+' || symbol == '-')
            return 1;
        else if (symbol == 'x' || symbol == '/')
            return 2;
        else
            return -1;
    }

    /**
     * Converts expression to postfix notation
     * @param input expression
     * @return expression in postfix notation
     */
    public static String toPostfixNotation(String input) {
        Stack<Character> stack = new Stack<>();
        String result = "";

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);

            if (Character.isLetterOrDigit(character) && character != 'x' ||  character == '.') // digit or .
                result += character;
            else if (character == '(')
                stack.push(character);
            else if (character == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result += " " + stack.pop();
                stack.pop();    // Delete (
            } else {
                while (!stack.isEmpty()
                        && getPriority(character) <= getPriority(stack.peek())) {
                    result += " " + stack.pop();
                }
                result += " ";
                stack.push(character);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "This expression is invalid";
            result += " " + stack.pop();
        }
        return result;
    }
}
