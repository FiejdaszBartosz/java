package integrals;

public class Trapezoidal extends IntegralAlgorithm {

    public Trapezoidal() {
        this.sum = 0;
    }

    /* First concept of function
    @Override
    public void calculateIntegral() {
        double step, temp = a;

        step = Math.abs((b - a) / n);

        while(temp <= b) {
            sum = sum + ((function.getValue(temp) + function.getValue(temp + step)) * step * 0.5);
            temp += step;
        }
    }
    */

    // Optimization
    @Override
    public void calculateIntegral() {
        double step, temp;
        int i = 1;

        // integral = step * ( 0.5 * (f(a) + f(b)) + sum from i = 1 to i < n - 1 of f(temp))
        // temp = a + step * i
        step = (b - a) / n;

        // Add to sum 0.5 * (f(a) + f(b)) part
        sum = 0.5 * (function.getValue(a) + function.getValue(b));

        while (i < n - 1) {
            temp = a + step * i;
            sum += function.getValue(temp);
            i++;
        }

        sum *= step;

    }

}
