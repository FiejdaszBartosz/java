package com.fiejdasz.solver;

import com.fiejdasz.solver.exceptions.ContradictoryEquationException;
import com.fiejdasz.solver.exceptions.SolverException;
import com.fiejdasz.solver.exceptions.InfiniteRootsException;

public class QuadraticFormulaSolver implements QFormulaSolver{
    private double a;
    private double b;
    private double c;
    private boolean isComplex;

    public QuadraticFormulaSolver() {
        a = 0;
        b = 0;
        c = 0;
    }

    public void setInitialParameters(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean IsComplex() { return isComplex; }

    protected double delta() { return b * b - 4 * a * c; }

    protected boolean isQuadratic() { return a != 0; }

    protected double[] twoRoots(double d) {
        double sd = Math.sqrt(d);
        double[] result = new double[2];
        result[0] = (-b - sd) / 2 * a;
        result[1] = (-b + sd) / 2 * a;
        return result;
    }

    protected double[] singleRoot() {
        double[] result = new double[1];
        result[0] = -b / 2 * a;
        return result;
    }

    protected double[] solveQuadratic() {
        isComplex = false;
        double d = this.delta();
        if (d > 0)
            return twoRoots(d);
        else if (d == 0)
            return singleRoot();
        else {
            isComplex = true;
            return twoRoots(-d);
        }
    }

    protected double solveLinear() throws SolverException {
        if(b != 0)
            return -c / b;
        else {
            if(c == 0)
                throw new InfiniteRootsException();
            else
                throw new ContradictoryEquationException();
        }
    }

    public double[] solve() throws SolverException {
        if(isQuadratic())
            return solveQuadratic();
        else {
            double[] result = new double[1];
            result[0] = solveLinear();
            return result;
        }
    }
}
