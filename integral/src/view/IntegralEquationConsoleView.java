package view;

import integrals.IntegralAlgorithm;
import integrals.Trapeze;
import integrals.exceptions.IntegralExceptions;

import java.util.Scanner;

public class IntegralEquationConsoleView implements IntegralEquationView{
    private IntegralAlgorithm solver;
    private Scanner sc;

    protected double parseWithMessageDouble(String message) {
        System.out.println(message);
        String line;
        double res;

        try {
            line = sc.nextLine();
            res = Double.parseDouble(line);
        } catch (Exception ex) {
            System.err.println("Wprowadzono niepoprawne dane");
            res = parseWithMessageDouble(message);
        }

        return res;
    }

    protected int parseWithMessageInt(String message) {
        System.out.println(message);
        String line;
        int res;

        try {
            line = sc.nextLine();
            res = Integer.parseInt(line);
        } catch (Exception ex) {
            System.err.println("Wprowadzono niepoprawne dane");
            res = parseWithMessageInt(message);
        }

        return res;
    }

    protected void parseFactor() {
        double a, b;
        int n;

        a = parseWithMessageDouble("Wprowadz współczynnik a: ");
        b = parseWithMessageDouble("Wprowadz współczynnik b: ");

        if (a > b) {
            double temp;
            temp = a;
            a = b;
            b = temp;
        }

        n = parseWithMessageInt("Wprowadz liczbę podziałów n: ");

        this.solver.setA(a);
        this.solver.setB(b);
        this.solver.setN(n);
    }

    protected void displaySolutions(double res) {
        System.out.println("Numeryczna\t"+res);
    }

    protected void getSolution() {
        solver.calculateIntegral();
        displaySolutions(solver.getIntegral());
    }

    public void Init(IntegralAlgorithm solver) {
        this.solver = solver;
        this.sc = new Scanner(System.in);
    }

    public void View() {
        while (true) {
            parseFactor();
            getSolution();
        }
    }
}
