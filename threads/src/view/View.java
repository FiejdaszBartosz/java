package view;

import matrix.IMatrix;
import matrix.Matrix;
import matrix.generators.DefaultGenerator;
import matrixThreads.MatrixThreads;

import java.util.Date;
import java.util.Scanner;

public class View implements IView {
    private Scanner sc = new Scanner(System.in);

    protected int parseWithMessageInt(String message) {
        System.out.println(message);
        String line;
        int res;

        try {
            line = sc.nextLine();
            res = Integer.parseUnsignedInt(line);
        } catch (Exception ex) {
            System.err.println("Wprowadzono niepoprawne dane");
            res = parseWithMessageInt(message);
        }

        return res;
    }

    protected void calculate() {
        IMatrix a;
        IMatrix b;
        IMatrix c;
        IMatrix d;

        int n, m;

        System.out.println("Podaj wymiary macierzy:");
        m = parseWithMessageInt("Podaj m:");
        n = parseWithMessageInt("Podaj n:");

        if (n == m) {
            a = DefaultGenerator.generateRandomMatrix(m, n, 0, 3);
            b = DefaultGenerator.generateRandomMatrix(m, n, 0, 3);

            System.out.println("Mnożę macierze klasycznie...");
            Date start = new Date();
            c = IMatrix.multiply(a, b);
            Date end = new Date();
            System.out.println("Czas mnożenia w milisekundach: " + (end.getTime() - start.getTime()));

            d = new Matrix(m, n);
            MatrixThreads test = new MatrixThreads(a, b, d, m);

            start = new Date();
            test.createTasks();
            end = new Date();
            System.out.println("Czas mnożenia w milisekundach przy uzyciu watkow: " + (end.getTime() - start.getTime()));
        } else {
            System.out.println("Nieprawidlowe dane!");
        }

    }

    @Override
    public void menu() {
        String line;
        int choice;
        boolean ifContinue = true;

        while (ifContinue) {
            System.out.println("""
                    1) Multiply and compare the times
                    2) Exit""");

            choice = parseWithMessageInt("");

            if (choice == 1)
                calculate();
            else if (choice == 2)
                ifContinue = false;
            else
                System.out.println("Podano nieprawidlowe dane");
        }
    }


}
