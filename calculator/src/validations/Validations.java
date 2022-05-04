package validations;

import java.util.Stack;

public class Validations {
    /**
     * Checks if before last character is sign or digit. If it is sign it will be ignored.
     * @param input expression
     * @return true when character should be ignored, false in other case
     */
    private static boolean ifNotAllowedSign(String input) {
        char[] signs = {'+', '-', 'x', '/', '.'};

        char last = input.charAt(input.length() - 1);
        char possibleSign = input.charAt(input.length() - 2);

        if (!Character.isLetterOrDigit(last) || last == 'x') {
            for (char i : signs) {
                if (possibleSign == i)
                    return true;
            }
        }

        return false;
    }

    /**
     * Validates the entered text. Checks if the first character is correct.
     * @param input expression
     * @return
     */
    public static boolean ifIgnoreSign(String input) {
        if(input.length() != 0) {
            char last = input.charAt(input.length() - 1);
            if ((Character.isLetterOrDigit(last) && last != 'x') || last == '(') {
                return false;
            } else {
                if (input.length() <= 1)
                    return true;
                else {
                    return ifNotAllowedSign(input);
                }
            }
        } else
            return false;
    }

    public static boolean balancedParenthesizes(char previous) {
        char[] signs = {'+', '-', 'x', '/', '.', '('};
        for (char i : signs) {
            if (previous == i)
                return false;
        }

        return true;
    }
}
