package com.fiejdasz;

import com.fiejdasz.solver.QFormulaSolver;
import com.fiejdasz.solver.QuadraticFormulaSolver;
import com.fiejdasz.view.QuadraticEquationSolverConsoleView;
import com.fiejdasz.view.QuadraticEquationSolverView;

public class Main {
    static QFormulaSolver solver;
    static QuadraticEquationSolverView view;

    public static void main(String[] args) {
	    solver = new QuadraticFormulaSolver();
        view = new QuadraticEquationSolverConsoleView();
        view.Init(solver);
        view.View();
    }
}
