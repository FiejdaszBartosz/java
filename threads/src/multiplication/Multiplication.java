package multiplication;

import matrix.IMatrix;

public class Multiplication implements IMultiplication {
    private IMatrix mFirstMatrix;
    private IMatrix mSecondMatrix;
    private IMatrix mResult;
    private int mRow;

    public Multiplication(IMatrix firstMatrix, IMatrix secondMatrix, IMatrix result, int row) {
        this.mFirstMatrix = firstMatrix;
        this.mSecondMatrix = secondMatrix;
        this.mResult = result;
        this.mRow = row;
    }

    @Override
    public void run() {
        mResult = IMatrix.multiply(mFirstMatrix, mSecondMatrix);
    }

}
