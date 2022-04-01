package triangle;

import exceptions.CreateTriangleException;
import exceptions.PointNotFoundException;
import exceptions.SamePointsException;
import exceptions.TriangleVerticeNotFound;
import point.Point;
import vector.Vector;

public class Triangle implements ITriangle {
    Vector mAB, mBC, mCA;
    Point mA, mB, mC;
    double mArea, mPerimeter;

    /**
     * Creates Triangle from three points, calculates area and perimeter
     *
     * @param a First point
     * @param b Second point
     * @param c Third Point
     */
    public Triangle(Point a, Point b, Point c) throws CreateTriangleException {
        Vector tempA, tempB, tempC;

        // Creating the sides of a triangle
        tempA = new Vector(a, b);
        tempB = new Vector(b, c);
        tempC = new Vector(c, a);

        if (checkTriangle(tempA, tempB, tempC)) {
            // Assign temporary point
            mAB = tempA;
            mBC = tempB;
            mCA = tempC;
            mA = a;
            mB = b;
            mC = c;
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
        mArea = Math.sqrt(halfPerimeter * (halfPerimeter - mAB.getDistance()) * (halfPerimeter - mBC.getDistance())
                * (halfPerimeter - mCA.getDistance()));
    }

    @Override
    public void calculatePerimeter() {
        mPerimeter = mAB.getDistance() + mBC.getDistance() + mCA.getDistance();
    }

    @Override
    public void changeTrianglePoint(double previousX, double previousY, double newX, double newY) throws TriangleVerticeNotFound {
        if (mA.checkPoint(previousX, previousY)) {
            try {
                mA.changeCoordinates(newX, newY);
            } catch (SamePointsException e) {
                System.err.println(e.getMessage());
            }

            calculatePerimeter();
            calculateArea();
        } else if (mB.checkPoint(previousX, previousY)) {
            try {
                mB.changeCoordinates(newX, newY);
            } catch (SamePointsException e) {
                System.err.println(e.getMessage());
            }

            calculatePerimeter();
            calculateArea();
        } else if (mC.checkPoint(previousX, previousY)) {
            try {
                mC.changeCoordinates(newX, newY);
            } catch (SamePointsException e) {
                System.err.println(e.getMessage());
            }

            calculatePerimeter();
            calculateArea();
        } else
            throw new TriangleVerticeNotFound();
    }
}
