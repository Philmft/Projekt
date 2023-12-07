package schule.unipassau;

import java.util.Scanner;
import java.lang.Math;

public class SolutionX {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] H = generateHadamard(n);
        printMatrix(H);
    }

    public static int[][] generateHadamard(int n) {
        if (n == 1) {
            return new int[][]{{1}};
        } else if (isPowerOfTwo(n)) {
            int[][] temp = generateHadamard(n / 2);

            int[][] H = new int[n][n];
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    int val = temp[i][j];
                    H[i][j] = val;
                    H[i + n / 2][j] = val;
                    H[i][j + n / 2] = val;
                    H[i + n / 2][j + n / 2] = 1 - val;
                }
            }
            return H;
        } else {
            int[][] H = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        H[i][j] = 1;
                    } else {
                        int diff = (i - j + n) % n;
                        if (diff == 1 || (diff > 1 && diff <= n / 2 && isQuadraticResidue(diff, n))) {
                            H[i][j] = 1;
                        } else {
                            H[i][j] = 0;
                        }
                    }
                }
            }
            return H;
        }
    }

    public static boolean isQuadraticResidue(int num, int mod) {
        for (int i = 1; i <= mod / 2; i++) {
            if ((i * i) % mod == num) {
                return true;
            }
        }
        return false;
    }

    public static void printMatrix(int[][] H) {
        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[0].length; j++) {
                System.out.print(H[i][j]);
                if (j != H[0].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }



        /* Function to check if x is power of 2*/
        public static boolean isPowerOfTwo(int n) {
            if (n == 0)
                return false;

            return (int)(Math.ceil((Math.log(n) / Math.log(2))))
                    == (int)(Math.floor(
                    ((Math.log(n) / Math.log(2)))));
        }
}


