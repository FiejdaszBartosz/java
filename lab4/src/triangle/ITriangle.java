package triangle;

import exceptions.PointNotFoundException;
import exceptions.TriangleVerticeNotFound;
import point.Point;
import vector.Vector;

public interface ITriangle {
    void copy(Triangle triangle);
    boolean checkTriangle(Vector a, Vector b, Vector c);
    void calculateArea();
    void calculatePerimeter();
    double getArea();
    double getPerimeter();
    void changeTrianglePoint(double previousX, double previousY, double newX, double newY) throws TriangleVerticeNotFound;
    double calculateHeight(Point x) throws PointNotFoundException;
    void printTriangle();
}
