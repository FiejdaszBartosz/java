package matrixThreads;

import matrix.IMatrix;
import multiplication.Multiplication;

public class MatrixThreads implements IMatrixThreads{
    //private Runnable[] mTab;
    private Thread[] mThreads;
    private int mSize;
    private int mCounter;

    private IMatrix mFirstMatrix;
    private IMatrix mSecondMatrix;
    private IMatrix mResult;
    private int mRow;



    public MatrixThreads(IMatrix firstMatrix, IMatrix secondMatrix, IMatrix result, int row) {
        mSize = calculateAvailableThreads();
        //mTab = new Runnable[mSize];
        mThreads = new Thread[mSize];
        mCounter = 0;

        this.mFirstMatrix = firstMatrix;
        this.mSecondMatrix = secondMatrix;
        this.mResult = result;
        this.mRow = row;
    }

    public void createTasks() {
        Multiplication task;
        for (int i = 0; i < mRow; i++) {
            task = new Multiplication(mFirstMatrix, mSecondMatrix, mResult, i);
            addThread(task);
        }
    }

    private void addThread(Multiplication newTask) {
        Thread tempThread = new Thread(newTask);
        tempThread.start();
        mThreads[mCounter] = tempThread;
        mCounter += 1;

        if (mCounter == mSize)
            waitForThreads(mThreads);
    }

    private void waitForThreads(Thread[] table) {
        for (Thread i : table) {
            try {
                i.join();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }

        table = new Thread[mSize];
        mCounter = 0;
    }

    @Override
    public int calculateAvailableThreads() { return Runtime.getRuntime().availableProcessors(); }


}
