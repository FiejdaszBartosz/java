package vector;

import point.IPoint;
import point.Point;

public interface IVector {
    void setPoints(Point a, Point b);
    void calculateDistance();
    double getDistance();
    void setDistance(double distance);
    void changePointA(double newX, double newY);
    void changePointB(double newX, double newY);
    int findPoint(double x, double y);
    void changePoint(double previousX, double previousY, double newX, double newY);
}
