package point;

import exceptions.SamePointsException;

public class Point implements IPoint {
    double mX, mY;

    public Point() {
        this.mX = 0;
        this.mY = 0;
    }

    public Point(double x, double y) {
        this.mX = x;
        this.mY = y;
    }

    @Override
    public double getX() {
        return this.mX;
    }

    @Override
    public double getY() {
        return this.mY;
    }

    @Override
    public void setX(double x) {
        this.mX = x;
    }

    @Override
    public void setY(double y) {
        this.mY = y;
    }

    @Override
    public void changeCoordinates(double newX, double newY) throws SamePointsException{
        if(this.mX == newX && this.mY == newY)
            throw new SamePointsException();
        else {
            this.mX = newX;
            this.mY = newY;
        }
    }
}
