package validations;

import java.util.Stack;

public class Validations {
    public static boolean ifNotAllowedSign(String input) {
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
