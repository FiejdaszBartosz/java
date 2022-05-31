package exceptions;

/**
 * Throwing when given amount is incorrect. E.g. amount = -100 type = INCOME
 */
public class AmountTypeException extends PresentException {
    public AmountTypeException() { super("Invalid amount"); }
}
