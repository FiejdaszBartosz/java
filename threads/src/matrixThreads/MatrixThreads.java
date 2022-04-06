package matrixThreads;

public class MatrixThreads implements IMatrixThreads {
    private Runnable[] mTab;
    private Thread[] mThreads;
    private int mSize;

    public MatrixThreads() {
        mSize = calculateAvailableThreads();
        mTab = new Runnable[mSize];
        mThreads = new Thread[mSize];
    }

    @Override
    public void createThreads() {
        int i;
        for(i = 0; i < mSize; i++) {
            //mTab[i] = new Task(i);
            mThreads[i] = new Thread(mTab[i]);
            mThreads[i].start();
        }
    }

    @Override
    public int calculateAvailableThreads() { return Runtime.getRuntime().availableProcessors(); }
}
