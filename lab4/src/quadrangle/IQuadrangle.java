package quadrangle;

import exceptions.QuadrangleVerticeNotFound;

public interface IQuadrangle {
    void copy(Quadrangle quadrangle);
    void calculatePerimeter();
    void calculateArea();
    void changeQuadranglePoint(double previousX, double previousY, double newX, double newY) throws QuadrangleVerticeNotFound;
}
