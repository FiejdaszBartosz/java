package view;

import exceptions.CreateTriangleException;
import point.Point;
import quadrangle.Quadrangle;
import triangle.Triangle;

import java.util.Scanner;

public class View implements IView {
    private Scanner sc;
    private Triangle[] mTrianglesArray;
    private Quadrangle[] mQuadranglesArray;
    int mTriangleArraySize, mQuadranglesArraySize;

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

    @Override
    public Point createPoint() {
        Point tempPoint;
        double x, y;

        x = parseWithMessageDouble("Wprowadź współrzędną x: ");
        y = parseWithMessageDouble("Wprowadź współrzędną y: ");

        tempPoint = new Point(x, y);
        return tempPoint;
    }

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

    @Override
    public void changePoint() {
        Point previous, current;
        int choice;

        System.out.println("""
                Zmień punkt w:
                0 - trójkat
                1 - czworobok""");
        choice = parseWithMessageInt("");

        if (choice == 0) {

        } else if (choice == 1) {

        } else {
            System.err.println("Nieprawidłowy wybór");
            changePoint();
        }

        System.out.println("Podaj stary punkt :");
        previous = createPoint();
        System.out.println("Podaj nowy punkt :");
        current = createPoint();


    }

    @Override
    public void printTriangleArray() {

    }
}
