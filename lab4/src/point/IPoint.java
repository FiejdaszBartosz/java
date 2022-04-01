package point;

import exceptions.SamePointsException;

public interface IPoint {
    double getX();
    double getY();
    void setX(double x);
    void setY(double y);
    void changeCoordinates(double newX, double newY) throws SamePointsException;
}
