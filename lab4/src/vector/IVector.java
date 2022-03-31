package vector;

import point.IPoint;
import point.Point;

public interface IVector {
    public void setPoints(Point a, Point b)
    public void calculateDistance();
    public double getDistance();
    public void setDistance(double distance);
    public void changePointA(double newX, double newY);
    public void changePointB(double newX, double newY);
}
