package vector;

import exceptions.PointNotFoundException;
import exceptions.SamePointsException;
import point.Point;

public class Vector implements IVector {
    private double distance;
    private Point mA, mB;

    public Vector(Point a, Point b) {
        boolean checkPoints = false;
        try {
            checkPoints = checkCoordinates(a, b);
        } catch (SamePointsException e) {
            System.err.println(e.getMessage());
        }

        if (checkPoints) {
            this.mA = new Point(a);
            this.mB = new Point(b);
            calculateDistance();
        }
    }

    @Override
    public void copy(Vector vector) {
        this.mA = vector.mA;
        this.mB = vector.mB;
        this.distance = vector.distance;
    }

    public Vector(Vector vector) {
        this.copy(vector);
    }

    @Override
    public void setPoints(Point a, Point b) {
        this.mA.copy(a);
        this.mB.copy(b);
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

    /**
     * Calculates distance between point a and b
     */
    @Override
    public void calculateDistance() {
        distance = Math.sqrt(Math.pow((mB.getX() - mA.getX()), 2) + Math.pow((mB.getY() - mA.getY()), 2));
    }

    @Override
    public void changePointA(double newX, double newY) {
        try {
            this.mA.changeCoordinates(newX, newY);
        } catch (SamePointsException e) {
            System.err.println(e.getMessage());
        }
        calculateDistance();
    }

    @Override
    public void changePointB(double newX, double newY) {
        try {
            this.mB.changeCoordinates(newX, newY);
        } catch (SamePointsException e) {
            System.err.println(e.getMessage());
        }
        calculateDistance();
    }

    /**
     * Checks if in this vector is a given point
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return 0 if it is point A; 1 if it is point B; -1 if not found
     */
    @Override
    public int findPoint(double x, double y) {
        if (mA.getX() == x && mA.getY() == y)
            return 0;
        else if (mB.getX() == x && mB.getY() == y)
            return 1;
        else
            return -1;
    }

    /**
     * Checks if the given points are not the same
     *
     * @param a First point
     * @param b Second point
     * @return true if it is possible
     * @throws SamePointsException Given points were the same
     */
    @Override
    public boolean checkCoordinates(Point a, Point b) throws SamePointsException {
        if (a.getX() == b.getX() && a.getY() == b.getY())
            throw new SamePointsException();
        return true;
    }

    /**
     * Changes points in vector
     *
     * @param previousX Previous x coordinate
     * @param previousY Previous y coordinate
     * @param newX x coordinate after change
     * @param newY y coordinate after change
     * @throws PointNotFoundException Given point was not found in this vector
     */
    @Override
    public void changePoint(double previousX, double previousY, double newX, double newY) throws PointNotFoundException {
        int checkPoint;

        // Checks if in this vector is given point
        checkPoint = findPoint(previousX, previousY);

        if (checkPoint == 0) {
            changePointA(newX, newY);
            calculateDistance();
        }
        else if (checkPoint == 1) {
            changePointB(newX, newY);
            calculateDistance();
        }
        else
            throw new PointNotFoundException();
    }
}
