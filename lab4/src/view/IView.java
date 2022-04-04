package view;

import exceptions.EmptyArrayException;
import point.Point;
import quadrangle.Quadrangle;
import triangle.Triangle;

public interface IView {
    Point createPoint();
    void addTriangle(Triangle triangle);
    void addQuadrangle(Quadrangle quadrangle);
    void createTriangle();
    void createQuadrangle();
    void changePoint();
    void printTriangleArray();
    void printQuadrangleArray();
    void sortArray();
    void printMenu();
}
