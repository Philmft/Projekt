package schule.unipassau;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

;

public class Solution5 {
    private int width;
    private int height;
    private int[][] board;
    private List<Integer> surviveRules;
    private List<Integer> birthRules;

    public Solution5(int width, int height, List<Integer> surviveRules, List<Integer> birthRules) {
        this.width = width;
        this.height = height;
        this.board = new int[height][width];
        this.surviveRules = surviveRules;
        this.birthRules = birthRules;
    }

    public void step() {
        int[][] newBoard = new int[height][width];

        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                int aliveNeighbours = countAliveNeighbours(x, y);

                if (board[x][y] == 1 && surviveRules.contains(aliveNeighbours)) {
                    newBoard[x][y] = 1;
                } else if (board[x][y] == 0 && birthRules.contains(aliveNeighbours)) {
                    newBoard[x][y] = 1;
                }
            }
        }

        this.board = newBoard;
    }

    private int countAliveNeighbours(int x, int y) {
        int count = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;

                int nx = x + dx;
                int ny = y + dy;

                if (nx >= 0 && ny >= 0 && nx < height && ny < width && board[nx][ny] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public void printBoard() {
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                System.out.print(board[x][y]);
                if (y != width - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int surviveRulesCount = scanner.nextInt();
        int birthRulesCount = scanner.nextInt();
        scanner.nextLine();

        List<Integer> surviveRules = new ArrayList<>();
        for (int i = 0; i < surviveRulesCount; i++) {
            surviveRules.add(scanner.nextInt());
        }
        scanner.nextLine();
        List<Integer> birthRules = new ArrayList<>();
        for (int i = 0; i < birthRulesCount; i++) {
            birthRules.add(scanner.nextInt());
        }
        scanner.nextLine();
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextLine();

        Solution5 game = new Solution5(width, height, surviveRules, birthRules);

        for (int x = 0; x < height; x++) {
            String line = scanner.nextLine();
            String[] cells = line.split(" ");
            for (int y = 0; y < cells.length; y++) {
                game.board[x][y] = Integer.parseInt(cells[y]);
            }
        }

        int steps = scanner.nextInt();

        for (int i = 0; i < steps; i++) {
            game.step();
        }

        game.printBoard();
    }
}
