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
            this.mAB = new Vector(tempA);
            this.mBC = new Vector(tempB);
            this.mCA = new Vector(tempC);
            this.mA = new Point(a);
            this.mB = new Point(b);
            this.mC = new Point(c);
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
    public double getArea() {
        return mArea;
    }

    @Override
    public double getPerimeter() {
        return mPerimeter;
    }

    @Override
    public void changeTrianglePoint(double previousX, double previousY, double newX, double newY) throws TriangleVerticeNotFound {
        if (mA.checkPoint(previousX, previousY))
            changeA(previousX, previousY, newX, newY);
        else if (mB.checkPoint(previousX, previousY))
            changeB(previousX, previousY, newX, newY);
        else if (mC.checkPoint(previousX, previousY))
            changeC(previousX, previousY, newX, newY);
        else
            throw new TriangleVerticeNotFound();
    }

    @Override
    public void changeA(double previousX, double previousY, double newX, double newY) {
        try {
            mA.changeCoordinates(newX, newY);
        } catch (SamePointsException e) {
            System.err.println(e.getMessage());
        }

        try {
            mAB.changePoint(previousX, previousY, newX, newY);
            mCA.changePoint(previousX, previousY, newX, newY);
        } catch (PointNotFoundException e) {
            System.err.println(e.getMessage());
        }

        calculatePerimeter();
        calculateArea();
    }

    @Override
    public void changeB(double previousX, double previousY, double newX, double newY) {
        try {
            mB.changeCoordinates(newX, newY);
        } catch (SamePointsException e) {
            System.err.println(e.getMessage());
        }

        try {
            mAB.changePoint(previousX, previousY, newX, newY);
            mBC.changePoint(previousX, previousY, newX, newY);
        } catch (PointNotFoundException e) {
            System.err.println(e.getMessage());
        }

        calculatePerimeter();
        calculateArea();
    }

    @Override
    public void changeC(double previousX, double previousY, double newX, double newY) {
        try {
            mC.changeCoordinates(newX, newY);
        } catch (SamePointsException e) {
            System.err.println(e.getMessage());
        }

        try {
            mBC.changePoint(previousX, previousY, newX, newY);
            mCA.changePoint(previousX, previousY, newX, newY);
        } catch (PointNotFoundException e) {
            System.err.println(e.getMessage());
        }

        calculatePerimeter();
        calculateArea();
    }
}
