package pl.retsuz;

import matrix.IMatrix;
import matrix.generators.DefaultGenerator;

import java.util.Date;

public class Main {
    static IMatrix a;
    static IMatrix b;
    static IMatrix c;
    static IMatrix d;
    public static void main(String[] args) {

        int n=50,m=50;
        System.out.println("Generuję macierze...");
        a= DefaultGenerator.generateRandomMatrix(m,n,0,3);
        b= DefaultGenerator.generateRandomMatrix(m,n,0,3);
        System.out.println("Mnożę macierze klasycznie...");
        Date start = new Date();
        c=IMatrix.multiply(a,b);
        Date end = new Date();
        System.out.println("Czas mnożenia w milisekundach: " + (end.getTime() - start.getTime()));
        /*
        Kod wykorzystujący klasę mnożenia współbieżnego
         */
    }
}
