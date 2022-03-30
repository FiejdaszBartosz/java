package vector;

import point.Point;

public class Vector implements IVector{
    double distance;
    Point a, b;

    public Vector(Point a, Point b) {
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
        distance =  Math.sqrt(Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2));
    }
}
