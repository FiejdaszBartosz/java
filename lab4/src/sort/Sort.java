package sort;

import quadrangle.Quadrangle;
import triangle.Triangle;

public class Sort implements ISort{
    private Triangle[] mTrianglesArray;
    private Quadrangle[] mQuadranglesArray;

    public Sort(Triangle[] triangles, Quadrangle[] quadrangles) {
        this.mTrianglesArray = triangles;
        this.mQuadranglesArray = quadrangles;
    }

    /**
     * Sorts given array of triangles by area. Using bubble sort
     */
    @Override
    public void sortTriangleArray() {
        int n = mTrianglesArray.length;
        Triangle tempTriangle;

        for (int i = 0; i < n - 1; i++)
            for(int j = 0; j < n - i - 1; j++)
                if (mTrianglesArray[j].getArea() >  mTrianglesArray[j + 1].getArea()) {
                    tempTriangle = mTrianglesArray[j];
                    mTrianglesArray[j] = mTrianglesArray[j + 1];
                    mTrianglesArray[j + 1] = tempTriangle;
                }
    }

    /**
     * Sorts given array of quadrangle by area. Using bubble sort
     */
    @Override
    public void sortQuadrangleArray() {
        int n = mQuadranglesArray.length;
        Quadrangle tempQuadrangle;

        for (int i = 0; i < n - 1; i++)
            for(int j = 0; j < n - i - 1; j++)
                if (mQuadranglesArray[j].getArea() >  mQuadranglesArray[j + 1].getArea()) {
                    tempQuadrangle = mQuadranglesArray[j];
                    mQuadranglesArray[j] = mQuadranglesArray[j + 1];
                    mQuadranglesArray[j + 1] = tempQuadrangle;
                }
    }
}
