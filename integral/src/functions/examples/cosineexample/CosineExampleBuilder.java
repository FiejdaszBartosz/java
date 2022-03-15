package functions.examples.cosineexample;

import functions.AbstractFunction;
import functions.Function;
import functions.examples.builder.ExampleBuilder;

public class CosineExampleBuilder implements ExampleBuilder {
    @Override
    public Function build() {
        Function result = new CosineExampleFunction();

        AbstractFunction res_cast = (AbstractFunction) result;
        Function derivative = new CosineFirstDerivative();
        Function integral = new CosineFirstIntegral();

        ((AbstractFunction)derivative).setFirstIntegral(result);
        ((AbstractFunction)integral).setFirstDerivative(result);

        res_cast.setFirstDerivative(derivative);
        res_cast.setFirstIntegral(integral);
        return result;
    }
}
