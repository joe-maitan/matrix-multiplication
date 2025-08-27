# matrix-multiplication
The objective of this project was to get comfortable with using threads and synchronization mechanisms. 

This project was the most fun I have had in a coding project in a while because I love seeing software and hardware interact. This project has also become one of my favorite benchmarking tools.

My computer (Ryzen 7 9800X3D and 32GB DDR5 @6000Mhz) averages around 6.5 seconds. My girlfriends M4 MacBook Pro averages around 10 seconds. See if you can beat our times!

What I learned about:
- Synchronization mechanisms and when to use them.
- Differences between the utilization of a thread pool and just creating multiple random object threads.
- The role data structures and locking mechanisms play when designing concurrent programs.
- How to debug and prevent concurrency issues.

# How to run the program.
## Docker
1. Build the Containerfile:
  ```
  docker build -f Containerfile -t matrix-multiplication:latest
  ```

2. Run the image
  ```
  docker run matrix-multiplication
  ```

## Podman
1. Build the Containerfile:
  ```
  podman build --manifest matirx-multiplication .
  ```

2. Run the image
  ```
  podman start matrix-multiplication
  ```

## On your bare metal
You must have **Java version 8** and **gradle version 8.3** or higher.

The main file for this program is `MatrixThreads.java`

You can use the `run.sh` script to run the program.
```
chmod +x run.sh
./run.sh <size-of-matrix> <seed>
```
- The seed arugment was used to ensure we calcuated the correct sum for each matrix.

For example, running ```./run.sh 3000 31459``` should give you an output of:
```
Dimensionality of the square matrices is: 3000
The thread pool size has been initialized to: 16

Sum of the elements in input matrix A = 3409964
Sum of the elements in input matrix B = 3799344
Sum of the elements in input matrix C = 4095260
Sum of the elements in input matrix D = 626540

Calcuation of matrix X (product of A and B) complete - sum of elements in X is: -37432324759
Time to compute matrix X: 2.412 seconds.
Calcuation of matrix Y (product of C and D) complete - sum of elements in Y is: -79329110607
Time to compute matrix Y: 2.182 seconds.
Calcuation of matrix Z (product of X and Y) complete - sum of elements in Z is: -3447947929874
Time to compute matrix Z: 2.077 seconds.
Cumulative time to compute matrices X, Y, and Z using a thread pool of size = 16 is : 6.671 s
```
