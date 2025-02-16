import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MatrixParallel {
    public static void fillMatrix(int[][] matrix) {
        Random rand = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = rand.nextInt(100);
            }
        }
    }

    public static void addMatrixSequential(int[][] A, int[][] B, int[][] C, int k) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                C[i][j] = A[i][j] + k * B[i][j];
            }
        }
    }

    public static void addMatrixParallel(int[][] A, int[][] B, int[][] C, int k, int threadsNum) {
        ExecutorService executor = Executors.newFixedThreadPool(threadsNum);
        for (int i = 0; i < A.length; i++) {
            int row = i;
            executor.execute(() -> {
                for (int j = 0; j < A[0].length; j++) {
                    C[row][j] = A[row][j] + k * B[row][j];
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int n = 10000;
        int m = 10000;
        int k = 2;
        int threadsNum = 256;

        int[][] A = new int[n][m];
        int[][] B = new int[n][m];
        int[][] C = new int[n][m];

        fillMatrix(A);
        fillMatrix(B);
        long startTime, endTime;

        // ВИКОНАННЯ БЕЗ ПАРАЛЕЛІЗАЦІЇ
        startTime = System.nanoTime();
        addMatrixSequential(A, B, C, k);
        endTime = System.nanoTime();
        System.out.printf("Sequential Time: %.5f seconds.\n", (endTime - startTime) / 1e9);

        // ВИКОНАННЯ З ПАРАЛЕЛІЗАЦІЄЮ
   /*     startTime = System.nanoTime();
        addMatrixParallel(A, B, C, k, threadsNum);
        endTime = System.nanoTime();
        System.out.printf("Parallel Time (%d threads): %.5f seconds.\n", threadsNum, (endTime - startTime) / 1e9);
*/
    }
}