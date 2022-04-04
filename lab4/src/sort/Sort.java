package sort;

import quadrangle.Quadrangle;
import triangle.Triangle;

public class Sort implements ISort{
    /**
     * Sorts given array of triangles by area. Using bubble sort
     *
     * @param triangleArray given array
     */
    @Override
    public void sortTriangleArray(Triangle[] triangleArray) {
        int n = triangleArray.length;
        Triangle tempTriangle;

        for (int i = 0; i < n - 1; i++)
            for(int j = 0; j < n - i - 1; j++)
                if (triangleArray[j].getArea() >  triangleArray[j + 1].getArea()) {
                    tempTriangle = triangleArray[j];
                    triangleArray[j] = triangleArray[j + 1];
                    triangleArray[j + 1] = tempTriangle;
                }
    }

    /**
     * Sorts given array of quadrangle by area. Using bubble sort
     *
     * @param quadrangleArray given array
     */
    @Override
    public void sortQuadrangleArray(Quadrangle[] quadrangleArray) {
        int n = quadrangleArray.length;
        Quadrangle tempQuadrangle;

        for (int i = 0; i < n - 1; i++)
            for(int j = 0; j < n - i - 1; j++)
                if (quadrangleArray[j].getArea() >  quadrangleArray[j + 1].getArea()) {
                    tempQuadrangle = quadrangleArray[j];
                    quadrangleArray[j] = quadrangleArray[j + 1];
                    quadrangleArray[j + 1] = tempQuadrangle;
                }
    }
}
