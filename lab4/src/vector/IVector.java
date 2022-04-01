package vector;

import exceptions.PointNotFoundException;
import exceptions.SamePointsException;
import point.Point;

public interface IVector {
    void setPoints(Point a, Point b);
    void calculateDistance();
    double getDistance();
    void setDistance(double distance);
    void changePointA(double newX, double newY);
    void changePointB(double newX, double newY);
    int findPoint(double x, double y);
    boolean checkCoordinates(Point a, Point b) throws SamePointsException;
    void changePoint(double previousX, double previousY, double newX, double newY) throws PointNotFoundException;
}
