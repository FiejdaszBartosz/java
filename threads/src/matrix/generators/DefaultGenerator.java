package matrix.generators;

import matrix.IMatrix;
import matrix.Matrix;

import java.util.Random;

public class DefaultGenerator {
    /**
     *
     * @param rowCount liczba wierszy m
     * @param columnCount liczba kolumn n
     * @param from początek zakresu
     * @param to koniec zakresu
     * @return macierz wymiaru mxn z losowymi wartościami z podanego zakresu
     */
    public static IMatrix generateRandomMatrix(int rowCount, int columnCount, double from, double to){
        IMatrix result=new Matrix(rowCount, columnCount);
        int i=0,j=0;
        double range = to-from;
        Random random = new Random();
        for(i=0;i<rowCount;i++){
            for(j=0;j<rowCount;j++)result.getData()[i][j]=from + random.nextDouble()*range;
        }

        return result;
    }
}
