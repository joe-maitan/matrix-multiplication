package csx55.threads;

import java.util.Random;

public class MatrixThreads {

    public static boolean validMatrixSize(int size) {
        return size > 0 && size <= 9000;
    } // End validMatrixSize() method

    public static boolean validSeed(int seed) {
        return seed > 0;
    } // End validSeed() method

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Error - Invalid # of arguments.");
            System.exit(1);
        } // End if statement

        final int MATRIX_DIMENSIONS = Integer.parseInt(args[0]);
        final int SEED = Integer.parseInt(args[1]);

        if (validMatrixSize(MATRIX_DIMENSIONS) == false) {
            System.err.println("Error: Invalid dimensions for matrix.");
            System.exit(1);
        }

        if (validSeed(SEED) == false) {
            System.err.println("Error: Invalid seed.");
            System.exit(1);
        }

        ThreadPool pool = ThreadPool.getInstance();

        System.out.printf("Dimensionality of the square matrices is: %d\n", MATRIX_DIMENSIONS);
        System.out.printf("The thread pool size has been initialized to: %d\n\n", pool.getThreadCount());

        Random rng = new Random(SEED);
        Matrix a = new Matrix("A", MATRIX_DIMENSIONS, rng);
        Matrix b = new Matrix("B", MATRIX_DIMENSIONS, rng);
        Matrix c = new Matrix("C", MATRIX_DIMENSIONS, rng);
        Matrix d = new Matrix("D", MATRIX_DIMENSIONS, rng);

        System.out.println();

        Matrix x = new Matrix("X", MATRIX_DIMENSIONS);
        Matrix y = new Matrix("Y", MATRIX_DIMENSIONS);
        Matrix z = new Matrix("Z", MATRIX_DIMENSIONS);

        x.multiplyMatrices(a, b, pool);
        y.multiplyMatrices(c, d, pool);
        z.multiplyMatrices(x, y, pool);

        double cumulativeTime = x.getTimeToCompute() + y.getTimeToCompute() + z.getTimeToCompute();
        System.out.printf("Cumulative time to compute matrices X, Y, and Z using a thread pool of size = %d is : %.3f s\n", pool.getThreadCount(), cumulativeTime);
    } // End main method
    
} // End MatrixThreads class
