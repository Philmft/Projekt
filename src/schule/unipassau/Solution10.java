package schule.unipassau;


import java.util.Scanner;
import java.util.Stack;

public class Solution10 {
    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();
        char[][] grid = new char[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        int startX = -1, startY = -1;
        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine().replace(" ", "");
            for (int j = 0; j < cols; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }
        markPath(grid, visited, startX, startY);
        printGrid(grid);
    }

    static void markPath(char[][] grid, boolean[][] visited, int x, int y) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});
        while (!stack.isEmpty()) {
            int[] point = stack.pop();
            int px = point[0], py = point[1];
            if (visited[px][py]) continue;
            visited[px][py] = true;
            for (int k = 0; k < 8; k++) {
                int ni = px + dx[k], nj = py + dy[k];
                if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length && grid[ni][nj] == '.') {
                    grid[ni][nj] = 'X';
                }
            }
            for (int k = 0; k < 4; k++) {
                int ni = px + dx[k], nj = py + dy[k];
                if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length && (grid[ni][nj] == 'L' || grid[ni][nj] == 'S')) {
                    stack.push(new int[]{ni, nj});
                }
            }
        }
    }

    static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
                if (i != row.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
