package csx55.threads;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPool implements Runnable {

    private static ThreadPool pool = null;

    private Thread[] threads;
    private ConcurrentLinkedQueue<Job> jobQueue;
    private int threadCount;

    private ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        threads = new Thread[size];
        jobQueue = new ConcurrentLinkedQueue<>();
        threadCount = size;

        for (int i = 0; i < size; ++i) {
            threads[i] = new Thread(this, ""+i);
            threads[i].setDaemon(true);
            threads[i].start();
        }
    } // End ThreadPool() constructor

    public static synchronized ThreadPool getInstance() {
        if (pool == null) {
            pool = new ThreadPool();
        }

        return pool;
    } // End getInstance() method

    public int getThreadCount() {
        return getInstance().threadCount;
    } // End getThreadCount() method

    public void addJob(Job newJob) {
        jobQueue.add(newJob);
    } // End addJob() method

    public boolean isJobQueueEmpty() {
        return jobQueue.isEmpty();
    }

    @Override
    public void run() {
        while (true) {
            Job newJob = jobQueue.poll();

            if (newJob != null) {
                // System.out.println("Thread " + Thread.currentThread().getName() + " pulled a job off the queue!");
                
                // Actually process the job
                int[][] m1 = newJob.getMatrixOne();
                int[][] m2 = newJob.getMatrixTwo();
                int[][] productMatrix = newJob.getProductMatrix();

                int row = newJob.getRowIndex();
                int col = newJob.getColIndex();

                int[] m1Row = m1[row];
                int[] m2Row = m2[col];
                
                // Perform matrix multiplication for assigned rows
                int product = 0;
                for (int i = 0; i < m1.length; ++i) {
                    product += m1Row[i] * m2Row[i];
                }

                productMatrix[row][col] = product;
                
                // System.out.printf("Thread %s completed rows: %d to %d", Thread.currentThread().getName(), startRow, endRow-1);
            } else {
                Thread.onSpinWait();
            }
        } // End while loop
    } // End run() method

} // End ThreadPool class
