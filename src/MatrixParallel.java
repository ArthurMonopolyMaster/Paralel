import java.util.Random;


public class MatrixParallel {
    public static void fillMatrix(int[][] matrix) {
        Random rand = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = rand.nextInt(100);
            }
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
    }
}