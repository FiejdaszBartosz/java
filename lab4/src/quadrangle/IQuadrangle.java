package quadrangle;

import exceptions.QuadrangleVerticeNotFound;
import exceptions.TriangleVerticeNotFound;

public interface IQuadrangle {
    void calculatePerimeter();
    void calculateArea();
    void changeQuadranglePoint(double previousX, double previousY, double newX, double newY) throws QuadrangleVerticeNotFound;
}
