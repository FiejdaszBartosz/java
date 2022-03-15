package functions.examples.cosineexample;

import functions.AbstractFunction;

public class CosineExampleFunction extends AbstractFunction {
    public CosineExampleFunction(){
        super.formula="f(x) = cos(x))/sqrt(1+sin(x)";
    }
    @Override
    public double getValue(double x) {
        return (Math.cos(x))/Math.sqrt(1+Math.sin(x));
    }
}
