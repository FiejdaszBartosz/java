package figures;

import point.Point;
import quadrangle.Quadrangle;
import triangle.Triangle;
import view.View;

public class Figures {
    public static void main(String[] args) {
        Triangle[] triangles = new Triangle[]{};
        Quadrangle[] quadrangles = new Quadrangle[]{};
        Point[] points = new Point[]{};
        View v = new View(triangles, quadrangles, points);
        boolean ifContnue = true;

        while (ifContnue)  {
            v.printMenu(ifContnue);
        }
    }

}
