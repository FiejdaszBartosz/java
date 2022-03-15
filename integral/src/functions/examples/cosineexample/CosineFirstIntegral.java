package functions.examples.cosineexample;

import functions.AbstractFunction;

public class CosineFirstIntegral extends AbstractFunction {

    public CosineFirstIntegral(){
        this.formula="f(x) = 2*sqrt(1+sin(x))";
    }
    @Override
    public double getValue(double x) {
        return 2*Math.sqrt(1+Math.sin(x));
    }

}
