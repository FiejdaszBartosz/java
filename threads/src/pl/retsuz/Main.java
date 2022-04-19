package pl.retsuz;

import matrix.IMatrix;
import matrix.Matrix;
import matrix.generators.DefaultGenerator;
import matrixThreads.MatrixThreads;
import view.View;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        View newView = new View();
        newView.menu();
    }
}
