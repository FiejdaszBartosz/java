package matrix;

public interface IMatrix {
    /**
     *
     * @return zwraca dane macierzy prostokątnej
     */
    double[][] getData();

    /**
     *
     * @param data dane macierzy prostokątnej
     */
    void setData(double[][] data);

    /**
     *
     * @return zwraca liczbę wierszy
     */
    int rowCount();

    /**
     *
     * @return zwraca liczbę kolumn
     */
    int columnCount();

    /**
     *
     * @return zwraca reprezentację tekstową
     */
    String toString();

    /**
     *
     * @param a macierz A
     * @param b macierz B
     * @return AxB
     */
    static IMatrix multiply(IMatrix a, IMatrix b){
        int resultRows=a.rowCount();
        int resultColumns=b.columnCount();
        IMatrix result = new Matrix(resultRows,resultColumns);
        int colCount = b.columnCount(),i=0,j=0,k=0;
            for(i=0;i<resultRows;i++){
                for(j=0;j<colCount;j++){
                    result.getData()[i][j]=0;
                    for(k=0;k<resultColumns;k++)
                        result.getData()[i][j]+=a.getData()[i][k]*b.getData()[k][j];
                }
            }

        return result;
    }
}
