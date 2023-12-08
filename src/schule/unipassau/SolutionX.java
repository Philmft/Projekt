package schule.unipassau;

import java.util.Scanner;

public class SolutionX {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = 1;
        while (m < n) {
            m *= 2;
        }
        int[][] H = hadamard(m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j != n - 1) {
                    System.out.print(H[i][j] == 1 ? "1 " : "0 ");
                } else {
                    System.out.print(H[i][j] == 1 ? "1" : "0");
                }
            }
            System.out.println();
        }
    }

    public static int[][] hadamard(int n) {
        if (n == 1) {
            return new int[][]{{1}};
        } else {
            int[][] temp = hadamard(n / 2);
            int[][] matrix = new int[n][n];
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    int val = temp[i][j];
                    matrix[i][j] = val;
                    matrix[i + n / 2][j] = val;
                    matrix[i][j + n / 2] = val;
                    matrix[i + n / 2][j + n / 2] = 1 - val;
                }
            }
            return matrix;
        }
    }
}
