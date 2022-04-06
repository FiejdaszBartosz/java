package quadrangle;

import exceptions.CreateTriangleException;
import exceptions.SamePointsException;
import exceptions.QuadrangleVerticeNotFound;
import exceptions.TriangleVerticeNotFound;
import point.Point;
import triangle.Triangle;
import vector.Vector;

public class Quadrangle implements IQuadrangle {
    private Point[] mPoints;
    private Vector[] mVectors;
    double mPerimeter, mArea;

    /**
     * Creates Quadrangle from three points, calculates area and perimeter
     *
     * @param a First point
     * @param b Second point
     * @param c Third Point
     */
    public Quadrangle(Point a, Point b, Point c, Point d) {
        mPoints = new Point[]{a, b, c, d};

        Vector tempA = new Vector(a, b);
        Vector tempB = new Vector(b, c);
        Vector tempC = new Vector(c, d);
        Vector tempD = new Vector(d, a);

        mVectors = new Vector[]{tempA, tempB, tempC, tempD};

        calculatePerimeter();
        calculateArea();
    }

    public Quadrangle(Quadrangle quadrangle) { this.copy(quadrangle); }

    @Override
    public void copy(Quadrangle quadrangle) {
        this.mPoints = quadrangle.mPoints;
        this.mVectors = quadrangle.mVectors;
        this.mArea = quadrangle.mArea;
        this.mPerimeter = quadrangle.mPerimeter;
    }

    @Override
    public double getArea() { return mArea; }

    /**
     * Calculates quadrangle's perimeter
     */
    @Override
    public void calculatePerimeter() {
        mPerimeter = 0;
        for (Vector i : mVectors)
            mPerimeter += i.getDistance();
    }

    /**
     * Calculates quadrangle's area
     */
    @Override
    public void calculateArea() {
        Triangle tempTriangleABC, tempTriangleCDA;
        mArea = 0;
        try {
            tempTriangleABC = new Triangle(mPoints[0], mPoints[1], mPoints[2]);
            tempTriangleCDA = new Triangle(mPoints[2], mPoints[3], mPoints[0]);
            mArea = tempTriangleABC.getArea() + tempTriangleCDA.getPerimeter();
        } catch (CreateTriangleException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Changes given quadrangle's vertex
     *
     * @param previousX previous x coordinate
     * @param previousY previous y coordinate
     * @param newX new x coordinate
     * @param newY new y coordinate
     * @throws QuadrangleVerticeNotFound if not found the given vertex
     */
    @Override
    public void changeQuadranglePoint(double previousX, double previousY, double newX, double newY) throws QuadrangleVerticeNotFound {
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
            throw new QuadrangleVerticeNotFound();
        else {
            calculatePerimeter();
            calculateArea();
        }
    }

    @Override
    public void printQuadrangle() {
        String msg = "";
        int count = 0;

        for(Point i : mPoints) {
            msg += count + " - (" + i.getX() + "," + i.getY() + ")\t";
            count += 1;
        }

        System.out.println(msg);
    }
}

