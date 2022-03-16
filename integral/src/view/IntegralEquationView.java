package view;

import integrals.IntegralAlgorithm;
import integrals.Trapeze;

public interface IntegralEquationView {
    public void View();
    public void Init(IntegralAlgorithm solver);
}
