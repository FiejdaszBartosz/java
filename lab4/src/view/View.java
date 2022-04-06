package view;

import exceptions.*;
import point.Point;
import quadrangle.Quadrangle;
import triangle.Triangle;
import vector.Vector;
import sort.Sort;

import java.util.Scanner;

public class View implements IView {
    private Scanner sc;
    private Triangle[] mTrianglesArray;
    private Quadrangle[] mQuadranglesArray;
    private Point[] mPointsArray;
    private Sort sort;
    int mTriangleArraySize, mQuadranglesArraySize, mPointArraySize;

    /**
     * default constructor arrays from parameters are needed for sorting
     *
     * @param arrTraingle   array of triangles
     * @param arrQuadrangle array of quadrangle
     * @param arrPoint      array of points
     */
    public View(Triangle[] arrTraingle, Quadrangle[] arrQuadrangle, Point[] arrPoint) {
        this.sc = new Scanner(System.in);
        this.mTrianglesArray = arrTraingle;
        this.mQuadranglesArray = arrQuadrangle;
        this.mPointsArray = arrPoint;
        mTriangleArraySize = 0;
        mQuadranglesArraySize = 0;
        mPointArraySize = 0;
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
     * Adds points to mPointsArray
     *
     * @param point given point
     */
    private void addPointToArray(Point point) {
        int i;

        if (mPointArraySize != 0) {
            Point[] newArray = new Point[mPointArraySize + 1];

            for (i = 0; i < mPointArraySize; i++)
                newArray[i] = mPointsArray[i];

            newArray[mPointArraySize] = point;
            mPointsArray = newArray;
            mPointArraySize += 1;
        } else {
            mPointsArray = new Point[]{point};
            mPointArraySize += 1;
        }
    }

    /**
     * Creates point
     */
    @Override
    public void addPoint() {
        Point a;

        System.out.println("Podaj punkt A:");
        a = createPoint();

        addPointToArray(a);
    }

    /**
     * Calculate distance between two points provided by the user
     *
     * @throws EmptyArrayException if array is empty
     */
    @Override
    public void countDistance() throws EmptyArrayException {
        int firstPoint, secondPoint;

        if (mPointArraySize == 0)
            throw new EmptyArrayException();

        printPointsArray();
        System.out.println("Wybierz dwa punkt pomiedzy którymi chcesz obliczyć dystans:");
        firstPoint = parseWithMessageInt("Wybierz 1 punkt");
        secondPoint = parseWithMessageInt("Wybierz 2 punkt");

        if ((firstPoint > mPointArraySize || firstPoint < 0) && (secondPoint > mPointArraySize || secondPoint < 0)) {
            System.err.println("Arr size error");
            countDistance();
        } else {
            Vector tempVector = new Vector(mPointsArray[firstPoint], mPointsArray[secondPoint]);
            System.out.println("Dystans wynosi: " + tempVector.getDistance());
        }

    }

    /**
     * Prints points array
     */
    @Override
    public void printPointsArray() {
        int count = 0;

        System.out.println("Dostępne punkty:");

        for (Point i : mPointsArray) {
            System.out.println(count + ") ");
            i.printPoint();
            count += 1;
        }
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

            //mTrianglesArray[mTriangleArraySize - 1] = triangle;
            newArray[mTriangleArraySize] = triangle;
            mTrianglesArray = newArray;
            mTriangleArraySize += 1;

        } else {
            mTrianglesArray = new Triangle[]{triangle};
            mTriangleArraySize += 1;
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

            newArray[mQuadranglesArraySize] = quadrangle;
            mQuadranglesArray = newArray;
            mQuadranglesArraySize += 1;
        } else {
            mQuadranglesArray = new Quadrangle[]{quadrangle};
            mQuadranglesArraySize += 1;
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
        } else {
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
     * Changes coordinate of the point provided by the user
     *
     * @throws EmptyArrayException if array is empty
     */
    private void changePoint() throws EmptyArrayException {
        Point current;
        int position;

        if (mPointArraySize == 0)
            throw new EmptyArrayException();

        printPointsArray();
        position = parseWithMessageInt("Wybierz w którym chcesz dokonać zmian");

        if (position > mPointArraySize || position < 0) {
            System.err.println("Arr size error");
            changePoint();
        } else {
            System.out.println("Podaj nowy punkt :");
            current = createPoint();

            try {
                mPointsArray[position].changeCoordinates(current.getX(), current.getY());
            } catch (SamePointsException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Changes point. User have to choose whether he wants to make changes to: triangle, quadrangle or point
     */
    @Override
    public void changeCoordinate() {
        int choice;

        System.out.println("""
                Zmień punkt w:
                0 - trójkat
                1 - czworobok
                2 - punkt""");
        choice = parseWithMessageInt("");

        switch (choice) {
            case 0:
                try {
                    changePointTriangle();
                } catch (EmptyArrayException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 1:
                try {
                    changePointQuadrangle();
                } catch (EmptyArrayException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    changePoint();
                } catch (EmptyArrayException e) {
                    System.err.println(e.getMessage());
                }
                break;
            default:
                System.err.println("Nieprawidłowy wybór");
                changeCoordinate();
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

    @Override
    public void sortArray() {
        int choice;

        System.out.println("""
                Wybierz którą tablicę chcesz posortować:
                0 - trójkaty
                1 - czworoboki""");
        choice = parseWithMessageInt("");

        if (choice == 0)
            sort.sortTriangleArray(mTrianglesArray);
        else if (choice == 1) {
            sort.sortQuadrangleArray(mQuadranglesArray);
        } else {
            System.err.println("Nieprawidłowy wybór");
            sortArray();
        }
    }

    @Override
    public void printMenu(boolean ifContinue) {
        int choice;

        System.out.println("""
                Wybierz co chcesz zrobic:
                1 - Dodaj punkt
                2 - Dodaj trójkąt
                3 - Dodaj czworobok
                4 - Zmień punkt
                5 - Sortuj figury
                6 - Wyświetl trójkąty
                7 - Wyświetl czworoboki
                8 - Wyświetl punkty
                9 - Oblicz dystans miedzy punktami
                10 - Wyjście
                """);

        choice = parseWithMessageInt("");
        switch (choice) {
            case 1:
                addPoint();

                break;
            case 2:
                createTriangle();

                break;
            case 3:
                createQuadrangle();

                break;
            case 4:
                changeCoordinate();

                break;
            case 5:
                sortArray();

                break;
            case 6:
                printTriangleArray();

                break;
            case 7:
                printQuadrangleArray();
                break;
            case 8:
                printPointsArray();
                break;
            case 9:
                try {
                    countDistance();
                } catch (EmptyArrayException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 10:
                ifContinue = false;
                break;
            default:
                System.err.println("Incorrect choice");
                printMenu(ifContinue);
                break;
        }
    }
}
