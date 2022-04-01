package triangle;

import exceptions.CreateTriangleException;
import point.Point;
import vector.Vector;

public class Triangle implements ITriangle {
    Vector mA, mB, mC;
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
            mA = tempA;
            mB = tempB;
            mC = tempC;
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
        return (a.getDistance() + b.getDistance() < c.getDistance())
                || (a.getDistance() + c.getDistance() < b.getDistance())
                || (b.getDistance() + c.getDistance() < a.getDistance());
    }


    @Override
    public void calculateArea() {
        // Using Heron's formula
        double halfPerimeter = mPerimeter / 2.0;
        mArea = Math.sqrt(halfPerimeter * (halfPerimeter - mA.getDistance()) * (halfPerimeter - mB.getDistance())
                * (halfPerimeter - mC.getDistance()));
    }

    @Override
    public void calculatePerimeter() {
        mPerimeter = mA.getDistance() + mB.getDistance() + mC.getDistance();
    }
}
