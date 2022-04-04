package view;

import exceptions.CreateTriangleException;
import exceptions.QuadrangleVerticeNotFound;
import exceptions.TriangleVerticeNotFound;
import exceptions.EmptyArrayException;
import point.Point;
import quadrangle.Quadrangle;
import triangle.Triangle;

import java.util.Scanner;

public class View implements IView {
    private Scanner sc;
    private Triangle[] mTrianglesArray;
    private Quadrangle[] mQuadranglesArray;
    int mTriangleArraySize, mQuadranglesArraySize;

    /**
     * default constructor arrays from parameters are needed for sorting
     *
     * @param arrTraingle array of triangles
     * @param arrQuadrangle array of quadrangle
     */
    public View(Triangle[] arrTraingle, Quadrangle[] arrQuadrangle) {
        this.sc = new Scanner(System.in);
        this.mTrianglesArray = arrTraingle;
        this.mQuadranglesArray = arrQuadrangle;
        mTriangleArraySize = 0;
        mQuadranglesArraySize = 0;
    }

    protected double parseWithMessageDouble(String message) {
        System.out.println(message);
        String line;
        double res;

        try {
            line = sc.nextLine();
            res = Double.parseDouble(line);
        } catch (Exception ex) {
            System.err.println("Wprowadzono niepoprawne dane");
            res = parseWithMessageDouble(message);
        }

        return res;
    }

    protected int parseWithMessageInt(String message) {
        System.out.println(message);
        String line;
        int res;

        try {
            line = sc.nextLine();
            res = Integer.parseInt(line);
        } catch (Exception ex) {
            System.err.println("Wprowadzono niepoprawne dane");
            res = parseWithMessageInt(message);
        }

        return res;
    }

    /**
     * Adds given triangle to mTrianglesArray
     *
     * @param triangle given triangle
     */
    @Override
    public void addTriangle(Triangle triangle) {
        int i;

        if (mTriangleArraySize != 0) {
            Triangle[] newArray = new Triangle[mTriangleArraySize + 1];

            for (i = 0; i < mTriangleArraySize; i++)
                newArray[i] = mTrianglesArray[i];

            mTriangleArraySize += 1;

            mTrianglesArray[mTriangleArraySize] = triangle;
        } else {
            mTrianglesArray = new Triangle[]{triangle};
        }
    }

    /**
     * Adds given triangle to mQuadrangleArray
     *
     * @param quadrangle given quadrangle
     */
    @Override
    public void addQuadrangle(Quadrangle quadrangle) {
        int i;

        if (mQuadranglesArraySize != 0) {
            Quadrangle[] newArray = new Quadrangle[mQuadranglesArraySize + 1];

            for (i = 0; i < mQuadranglesArraySize; i++)
                newArray[i] = mQuadranglesArray[i];

            mQuadranglesArraySize += 1;

            mQuadranglesArray[mQuadranglesArraySize] = quadrangle;
        } else {
            mQuadranglesArray = new Quadrangle[]{quadrangle};
        }
    }

    /**
     * Creates point by asking the user for coordinates
     *
     * @return created point
     */
    @Override
    public Point createPoint() {
        Point tempPoint;
        double x, y;

        x = parseWithMessageDouble("Wprowadź współrzędną x: ");
        y = parseWithMessageDouble("Wprowadź współrzędną y: ");

        tempPoint = new Point(x, y);
        return tempPoint;
    }

    /**
     * Creates Triangle by asking the user for necessary parameters
     */
    @Override
    public void createTriangle() {
        Point a, b, c;
        Triangle tempTriangle;

        System.out.println("Podaj punkt A:");
        a = createPoint();
        System.out.println("Podaj punkt B:");
        b = createPoint();
        System.out.println("Podaj punkt C:");
        c = createPoint();

        try {
            tempTriangle = new Triangle(a, b, c);
            addTriangle(tempTriangle);
        } catch (CreateTriangleException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Creates Quadrangle by asking the user for necessary parameters
     */
    @Override
    public void createQuadrangle() {
        Point a, b, c, d;
        Quadrangle tempQuadrangle;

        System.out.println("Podaj punkt A:");
        a = createPoint();
        System.out.println("Podaj punkt B:");
        b = createPoint();
        System.out.println("Podaj punkt C:");
        c = createPoint();
        System.out.println("Podaj punkt D:");
        d = createPoint();

        tempQuadrangle = new Quadrangle(a, b, c, d);
        addQuadrangle(tempQuadrangle);
    }

    /**
     * Asks the user for a triangle and points in which he wants to make changes
     *
     * @throws EmptyArrayException if array is empty
     */
    private void changePointTriangle() throws EmptyArrayException {
        Point previous, current;
        int position;

        if (mTriangleArraySize == 0)
            throw new EmptyArrayException();

        printTriangleArray();
        position = parseWithMessageInt("Wybierz w którym chcesz dokonać zmian");

        if (position > mTriangleArraySize || position < 0) {
            System.err.println("Arr size error");
            changePointTriangle();
        } else {
            System.out.println("Podaj stary punkt :");
            previous = createPoint();
            System.out.println("Podaj nowy punkt :");
            current = createPoint();

            try {
                mTrianglesArray[position].changeTrianglePoint(
                        previous.getX(),
                        previous.getY(),
                        current.getX(),
                        current.getY());
            } catch (TriangleVerticeNotFound e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Asks the user for a quadrangle and points in which he wants to make changes
     *
     * @throws EmptyArrayException if array is empty
     */
    private void changePointQuadrangle() throws EmptyArrayException {
        Point previous, current;
        int position;

        if (mQuadranglesArraySize == 0)
            throw new EmptyArrayException();

        printQuadrangleArray();
        position = parseWithMessageInt("Wybierz w którym chcesz dokonać zmian");

        if (position > mQuadranglesArraySize || position < 0) {
            System.err.println("Arr size error");
            changePointQuadrangle();
        }
        else {
            System.out.println("Podaj stary punkt :");
            previous = createPoint();
            System.out.println("Podaj nowy punkt :");
            current = createPoint();

            try {
                mQuadranglesArray[position].changeQuadranglePoint(
                        previous.getX(),
                        previous.getY(),
                        current.getX(),
                        current.getY());
            } catch (QuadrangleVerticeNotFound e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Changes point. User have to choose whether he wants to make changes to a triangle or a quadrangle
     */
    @Override
    public void changePoint() {
        int choice;

        System.out.println("""
                Zmień punkt w:
                0 - trójkat
                1 - czworobok""");
        choice = parseWithMessageInt("");

        if (choice == 0)
            try {
                changePointTriangle();
            } catch (EmptyArrayException e) {
                System.err.println(e.getMessage());
            }
        else if (choice == 1) {
            try {
                changePointQuadrangle();
            } catch (EmptyArrayException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("Nieprawidłowy wybór");
            changePoint();
        }
    }

    /**
     * Prints triangle array
     */
    @Override
    public void printTriangleArray() {
        int count = 0;

        System.out.println("Dostępne trójkąty:");

        for (Triangle i : mTrianglesArray) {
            System.out.println(count + ") ");
            i.printTriangle();
            count += 1;
        }
    }

    /**
     * Print quadrangle array
     */
    @Override
    public void printQuadrangleArray() {
        int count = 0;

        System.out.println("Dostępne czworokąty:");

        for (Quadrangle i : mQuadranglesArray) {
            System.out.println(count + ") ");
            i.printQuadrangle();
            count += 1;
        }
    }
}
