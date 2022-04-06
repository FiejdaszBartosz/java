package matrix;

public class Matrix implements IMatrix{
    protected double[][] data;

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public int rowCount(){
        return this.data.length;
    }

    public int columnCount(){
        return this.data[0].length;
    }
    public Matrix(int rowCount, int columnCount){
        this.data = new double[rowCount][columnCount];
    }

    public String toString(){
        String result="";
        int rows = this.rowCount();
        int cols = this.columnCount();
        int i=0,j=0;
        for(i=0;i<rows;i++){
            for(j=0;j<cols;j++)result+=data[i][j] + " ";
            result+="\n";
        }
        return result;
    }


}
