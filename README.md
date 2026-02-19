# matrix-multiplication
The objectives of this project:
- To get comfortable with using threads and synchronization mechanisms.
- Understand the role that data structures and locking mechanisms play in designing concurrent programs. 

This was the most fun I have had in a coding project because I love seeing software and hardware interact.

My computer averages around 6.5 seconds.

What I learned about:
- Synchronization mechanisms and when to use them.
- Differences between the utilization of a thread pool and creating multiple random object threads.
- The importance of knowing which data structure(s) to use and why.
- How to debug and prevent concurrency issues.

## Running the program
You must have **Java version 8** and **gradle version 8.3** or higher.

You can use the `run.sh` script to run the program. It takes two arguments `size-of-matrix` and `seed`.
- `size-of-matrix`: is going to be the MxM dimensions of the matrices created.
- `seed`: the seed to feed the random number generator which is used to initialize elements in the matrices.
```
./run.sh <size-of-matrix> <seed>
```
```
chmod +x run.sh
./run.sh 3000 31459
```
### Usage
For example, running:
```
./run.sh 3000 31459
``` 

should give you an output of:
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
