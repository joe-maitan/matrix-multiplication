package csx55.threads;

public class Job {

    private int[][] m1;
    private int[][] m2;
    private int[][] productMatrix;
    private int rowIndex;
    private int colIndex;

    public Job() {} // End Job() constructor

    public Job(int[][] matrixOne, int[][] matrixTwo, int rowIndex, int colIndex, int[][] productMatrix) {
        this.m1 = matrixOne;
        this.m2 = matrixTwo;
        this.productMatrix = productMatrix;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    } // End Job() constructor
    
    public int[][] getMatrixOne() {
        return this.m1;
    }

    public int[][] getMatrixTwo() {
        return this.m2;
    }

    public int[][] getProductMatrix() {
        return this.productMatrix;
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public int getColIndex() {
        return this.colIndex;
    }

} // End Job class
