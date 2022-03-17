package integrals;

public class CalculateExactIntegral extends IntegralAlgorithm {

    public CalculateExactIntegral() {
        this.sum = 0.0;
    }

    @Override
    public void calculateIntegral() {
        sum = function.getExactIntegralValue(b) - function.getExactIntegralValue(a);
    }
}
