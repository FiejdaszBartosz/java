package pl.retsuz;

import functions.Function;
import functions.examples.builder.ExampleBuilder;
import functions.examples.cosineexample.CosineExampleBuilder;
import integrals.IntegralAlgorithm;
import integrals.MonteCarlo;

public class Main {

    protected static ExampleBuilder functionBuilder;
    protected static Function givenExample;
    protected static IntegralAlgorithm algorithm;
    public static void main(String[] args) {
        double a = 0;
        double b = Math.PI/2;
        int n= 9999999;

        functionBuilder = new CosineExampleBuilder();
        givenExample = functionBuilder.build();
        algorithm = new MonteCarlo();

        algorithm.setFunction(givenExample);
        algorithm.setA(a);
        algorithm.setB(b);
        algorithm.setN(n);
        algorithm.calculateIntegral();

        double monteIntegral = algorithm.getIntegral();
        double exactIntegral = givenExample.getExactIntegralValue(b)-givenExample.getExactIntegralValue(a);
        double error = Math.abs(monteIntegral-exactIntegral);

        System.out.println("Numeryczna\t"+monteIntegral);
        System.out.println("Dokładna\t"+exactIntegral);
        System.out.println("Błąd\t\t"+error);
    }
}
