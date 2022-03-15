package functions;

public abstract class AbstractFunction implements Function{
    protected Function firstDerivative;
    protected Function firstIntegral;
    protected String formula;

    public void setFirstDerivative(Function firstDerivative) {
        this.firstDerivative = firstDerivative;
    }

    public void setFirstIntegral(Function firstIntegral) {
        this.firstIntegral = firstIntegral;
    }

    public String ToString(){
        return formula;
    }

    public double getExactIntegralValue(double x){
        return this.firstIntegral.getValue(x);
    }
    public double getExactDerivativeValue(double x){
        return this.firstDerivative.getValue(x);
    }

}
