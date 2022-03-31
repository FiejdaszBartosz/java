package triangle;

import point.Point;
import vector.Vector;

public class Triangle implements ITriangle {
    Vector mA, mB, mC;

    /**
     * Creating Triangle from three points
     *
     * @param a First point
     * @param b Second point
     * @param c Third Point
     */
    public Triangle(Point a, Point b, Point c) {
        Vector tempA, tempB, tempC;

        // Creating the sides of a triangle
        tempA = new Vector(a, b);
        tempB = new Vector(b, c);
        tempC = new Vector(c, a);

        if (!checkTriangle(tempA, tempB, tempC))
            System.out.println("Nie mozna utworzyc trojkata z podanych punktow");

        // Assign temporary point
        mA = tempA;
        mB = tempB;
        mC = tempC;
    }

    /**
     * Checking if it is possible to create a triangle from the given points
     * @param a First point
     * @param b Second point
     * @param c Third point
     * @return True/False if it is possible or not
     */
    @Override
    public boolean checkTriangle(Vector a, Vector b, Vector c){
        return (a.getDistance() + b.getDistance() > c.getDistance())
                || (a.getDistance() + c.getDistance() > b.getDistance())
                || (b.getDistance() + c.getDistance() > a.getDistance());
    }

}
