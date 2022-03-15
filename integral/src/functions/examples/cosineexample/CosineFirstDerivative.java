package functions.examples.cosineexample;

import functions.AbstractFunction;

public class CosineFirstDerivative extends AbstractFunction {

    public CosineFirstDerivative()
    {
        this.formula="f(x) = (-2*(1+sin(x))*sin(x)-cos(x)^2)/(2*(1+Math.sin(x))^1.5)";
    }

    @Override
    public double getValue(double x) {
       return (-2*(1+Math.sin(x))*Math.sin(x)-Math.cos(x)*Math.cos(x))/(2*Math.pow(1+Math.sin(x),1.5));
    }
}
