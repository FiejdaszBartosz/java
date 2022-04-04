package triangle;

import exceptions.*;
import point.Point;
import vector.Vector;

public class Triangle implements ITriangle {
    private Point[] mPoints;
    private Vector[] mVectors;
    double mArea, mPerimeter;

    /**
     * Creates Triangle from three points, calculates area and perimeter
     *
     * @param a First point
     * @param b Second point
     * @param c Third Point
     */
    public Triangle(Point a, Point b, Point c) throws CreateTriangleException {
        Vector tempA = new Vector(a, b);
        Vector tempB = new Vector(b, c);
        Vector tempC = new Vector(c, a);

        if (checkTriangle(tempA, tempB, tempC)) {
            mPoints = new Point[]{a, b, c};
            mVectors = new Vector[]{tempA, tempB, tempC};

            calculatePerimeter();
            calculateArea();
        } else
            throw new CreateTriangleException();
    }

    /**
     * Checks if it is possible to create a triangle from the given points
     *
     * @param a First point
     * @param b Second point
     * @param c Third point
     * @return True/False if it is possible or not
     */
    @Override
    public boolean checkTriangle(Vector a, Vector b, Vector c) {
        return (a.getDistance() + b.getDistance() > c.getDistance())
                || (a.getDistance() + c.getDistance() > b.getDistance())
                || (b.getDistance() + c.getDistance() > a.getDistance());
    }


    @Override
    public void calculateArea() {
        // Using Heron's formula
        double halfPerimeter = mPerimeter / 2.0;
        mArea = Math.sqrt(halfPerimeter
                * (halfPerimeter - mVectors[0].getDistance())
                * (halfPerimeter - mVectors[1].getDistance())
                * (halfPerimeter - mVectors[2].getDistance()));
    }

    @Override
    public void calculatePerimeter() {
        mPerimeter = mVectors[0].getDistance()
                + mVectors[1].getDistance()
                + mVectors[2].getDistance();
    }

    @Override
    public double getArea() { return mArea; }

    @Override
    public double getPerimeter() { return mPerimeter; }

    @Override
    public void changeTrianglePoint(double previousX, double previousY, double newX, double newY) throws TriangleVerticeNotFound {
        boolean isChanged = false;
        for (Point i : mPoints) {
            if (i.getX() == previousX && i.getY() == previousY) {
                try {
                    i.changeCoordinates(newX, newY);
                    for (Vector j : mVectors) {
                        j.changePointNoException(previousX, previousY, newX, newY);
                    }
                } catch (SamePointsException e) {
                    System.err.println(e.getMessage());
                }
                isChanged = true;
            }
        }
        if (!isChanged)
            throw new TriangleVerticeNotFound();
        else {
            calculatePerimeter();
            calculateArea();
        }
    }
}
