package view;

import exceptions.EmptyArrayException;
import point.Point;
import quadrangle.Quadrangle;
import triangle.Triangle;

public interface IView {
    Point createPoint();
    void addPoint();
    void countDistance() throws EmptyArrayException;
    void printPointsArray();
    void addTriangle(Triangle triangle);
    void addQuadrangle(Quadrangle quadrangle);
    void createTriangle();
    void createQuadrangle();
    void changeCoordinate();
    void printTriangleArray();
    void printQuadrangleArray();
    void countHeight() throws EmptyArrayException;
    void sortArray();
    void printMenu();
}
