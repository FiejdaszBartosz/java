package integrals;

public class CalculateError {
    double error, exact, approximation;

    public CalculateError() {
        this.error = 0.0;
        this.exact = 0.0;
        this.approximation = 0.0;
    }

    public void calculate() {
        error = Math.abs(approximation - exact);
    }

    public double getError() { return this.error; }

    public void setExact(double exa) {
        this.exact = exa;
    }

    public void setApproximatio(double approx) {
        this.approximation = approx;
    }

}
