package vector;

import point.Point;

public class Vector implements IVector {
    double distance;
    Point mA, mB;

    public Vector(Point a, Point b) {
        this.mA = a;
        this.mB = b;
        calculateDistance();
    }

    public Vector(Vector vector) {
        this.mA = vector.mA;
        this.mB = vector.mB;
        this.distance = vector.distance;
    }

    @Override
    public void setPoints(Point a, Point b) {
        this.mA = a;
        this.mB = b;
        calculateDistance();
    }

    @Override
    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public void calculateDistance() {
        distance = Math.sqrt(Math.pow((mB.getX() - mA.getX()), 2) + Math.pow((mB.getY() - mA.getY()), 2));
    }

    @Override
    public void changePointA(double newX, double newY) {
        this.mA.changeCoordinates(newX, newY);
        calculateDistance();
    }

    @Override
    public void changePointB(double newX, double newY) {
        this.mB.changeCoordinates(newX, newY);
        calculateDistance();
    }

}
