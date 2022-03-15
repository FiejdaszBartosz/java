package functions;

public interface Function {
    double getValue(double x);
    double getExactIntegralValue(double x);
    double getExactDerivativeValue(double x);
    String ToString();
}
