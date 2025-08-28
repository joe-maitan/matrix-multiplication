package csx55.threads;

import java.util.Random;

public class Matrix {
    
    private String name;
    private int[][] data;
    private long sum;
    private double timeToCompute;
    
    public Matrix() {} // End default Matrix constructor

    public Matrix(String name, int n) {
        this.name = name;
        this.data = new int[n][n];
        this.sum = 0;
    } // End Matrix constructor

    public Matrix(String name, int n, Random rng) {
        this(name, n);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                this.data[i][j] = 1000 - rng.nextInt(2000);
            }
        }

        System.out.printf("Sum of the elements in input matrix %s = %d\n", getName(), sum());
    } // End Matrix constructor

    public void multiplyMatrices(Matrix one, Matrix two, ThreadPool pool) {
        int n = one.data.length;
        two.transpose();

        int[][] matrixOne = one.data;
        int[][] matrixTwo = two.data;
        
        Job newJob;
        long startTime = System.nanoTime();
        for (int row = 0; row < n; ++row) {
            for (int col = 0; col < n; ++col) {
                newJob = new Job(matrixOne, matrixTwo, row, col, this.data);
                pool.addJob(newJob);
            }
        }

        while (pool.isJobQueueEmpty() != true) {
            Thread.onSpinWait();
        }

        Long endTime = System.nanoTime();

        this.timeToCompute = (endTime - startTime) / 1e9;

        System.out.printf("Calcuation of matrix %s (product of %s and %s) complete - sum of elements in %s is: %d\n", getName(), one.getName(), two.getName(), getName(), sum());
        System.out.printf("Time to compute matrix %s: %.3f seconds.\n", getName(), getTimeToCompute());
    } // End multiplyMatrices method

    public String getName() {
        return this.name;
    } // End getName() method

    public double getTimeToCompute() {
        return this.timeToCompute;
    } // End getTimeToCompute() method

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < this.data.length; ++i) {
            output += "[";
            for (int j = 0; j < this.data[0].length; ++j) {
                if (j == this.data[0].length - 1) {
                    output += data[i][j];
                } else {
                    output += data[i][j] + " ";
                }
            }
            output += "]\n";
        }

        return output;
    } // End toString() method

    public void transpose() {
        for (int row = 0; row < this.data.length; ++row) {
            for (int col = row + 1; col < this.data[0].length; ++col) {
                int temp = this.data[row][col];
                this.data[row][col] = this.data[col][row];
                this.data[col][row] = temp;
            }
        }
    } // End transpose() method
    
    public long sum() {
        if (this.sum != 0) {
            return this.sum;
        }

        int n = this.data.length;
        long sum = 0;

        // long startTime = System.nanoTime();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                sum += this.data[i][j];
            }
        }
        // long endTime = System.nanoTime();

        // this.timeToComputeSum = (endTime - startTime) / 1e9;
        this.sum = sum;
        return sum;
    } // End sum() method

} // End Matrix class
