package schule.unipassau;


import java.util.Scanner;

public class Solution8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[][] cost = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            cost[i][0] = Integer.parseInt(line[0]);
            cost[i][1] = Integer.parseInt(line[1]);
        }
        scanner.close();

        long[][] dp = new long[n][2];
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1] + cost[i][0], dp[i - 1][1] + cost[i][0]);
            dp[i][1] = Math.min(dp[i - 1][0] + cost[i][1], dp[i - 1][1] + cost[i][1]);
        }
        System.out.println(Math.min(dp[n - 1][0], dp[n - 1][1]));
    }
}


