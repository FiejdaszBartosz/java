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
    public void setRow(int row) { this.mRow = row; }

    @Override
    public void run() {
        for (int i = 0; i < mSecondMatrix.rowCount(); i++) {
            mResult.getData()[mRow][i] = 0;
            for (int j = 0; j < mFirstMatrix.getData()[mRow].length; j++) {
                mResult.getData()[mRow][i] += mFirstMatrix.getData()[mRow][j] * mSecondMatrix.getData()[j][i];
            }
        }
    }

}
