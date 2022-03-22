package view;

import integrals.CalculateExactIntegral;
import integrals.IntegralAlgorithm;
import integrals.CalculateError;
import integrals.exceptions.InputExceptions;
import integrals.exceptions.IntervalExceptions;

import java.util.Scanner;

public class IntegralEquationConsoleView implements IntegralEquationView{
    private IntegralAlgorithm solver;
    private IntegralAlgorithm exactSolver;
    private CalculateError err;
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

    protected void parseVariables(){
        double a, b;
        int n;

        a = parseWithMessageDouble("Wprowadz współczynnik a: ");
        b = parseWithMessageDouble("Wprowadz współczynnik b: ");
        n = parseWithMessageInt("Wprowadz liczbę podziałów n: ");

        if(a > b) {
            try {
                throw new IntervalExceptions();
            } catch (IntervalExceptions e) {
                System.err.println(e.getMessage());
                parseVariables();
            }
        } else if (n <= 0) {
            try {
                throw new InputExceptions();
            } catch (InputExceptions e) {
                System.err.println(e.getMessage());
                parseVariables();
            }
        }

        this.solver.setA(a);
        this.solver.setB(b);
        this.solver.setN(n);

        this.exactSolver.setA(a);
        this.exactSolver.setB(b);
    }

    protected void displaySolutions(double res) {
        System.out.println("Numeryczna\t"+res);
    }

    protected void displayExactSolution(double res) { System.out.println("Dokładna\t"+res); }

    protected void displayError(double error) { System.out.println("Błąd\t"+error); }

    protected void getSolution() {
        solver.calculateIntegral();
        exactSolver.calculateIntegral();

        err.setApproximatio(solver.getIntegral());
        err.setExact(exactSolver.getIntegral());

        err.calculate();

        displaySolutions(solver.getIntegral());
        displayExactSolution(exactSolver.getIntegral());
        displayError(err.getError());
    }

    public void Init(IntegralAlgorithm solver) {
        this.solver = solver;
        this.exactSolver = new CalculateExactIntegral();
        exactSolver.setFunction(solver.getFunction());
        this.err = new CalculateError();
        this.sc = new Scanner(System.in);
    }

    public void View() {
        while (true) {
            parseVariables();
            getSolution();
        }
    }
}
