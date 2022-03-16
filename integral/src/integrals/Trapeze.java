package integrals;

public class Trapeze extends IntegralAlgorithm{

    public Trapeze() {
        this.sum = 0;
    }

    @Override
    public void calculateIntegral() {
        double step, temp = a;

        step = Math.abs((b - a) / n);

        while(temp <= b) {
            sum += (function.getValue(temp) + function.getValue(temp + step)) * step * 0.5;
            temp += step;
        }
    }
}
