package point;

import exceptions.SamePointsException;

public interface IPoint {
    void copy(Point point);
    double getX();
    double getY();
    void setX(double x);
    void setY(double y);
    void changeCoordinates(double newX, double newY) throws SamePointsException;
    boolean checkPoint(double x, double y);
}
