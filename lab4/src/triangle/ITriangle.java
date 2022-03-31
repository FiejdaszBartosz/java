package triangle;

import point.Point;
import vector.Vector;

public interface ITriangle {
    boolean checkTriangle(Vector a, Vector b, Vector c);
    void calculateArea();
    void calculatePerimeter();
}
