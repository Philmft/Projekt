package schule.bwinf;

import java.util.*;

public class ZauberSchule2 {
    static int[][][] dist;
    static int[][][] grid;
    static char [][][] path;
    static int n, m;
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[2][n][m];
        dist = new int[2][n][m];
        path = new char[2][n][m];
        Point start = new Point(0, 0, 0);
        Point end = new Point(0, 0, 0);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                String line = sc.next();
                for (int k = 0; k < m; k++) {
                    if (line.charAt(k) == 'A') {
                        start = new Point(i, j, k);
                        grid[i][j][k] = 0;
                        System.out.println(i+" "+j+" "+k);
                    } else if (line.charAt(k) == 'B') {
                        end = new Point(i, j, k);
                        grid[i][j][k] = 0;
                        System.out.println(i+" "+j+" "+k);
                    } else if (line.charAt(k) == '#') {
                        grid[i][j][k] = 1;
                    } else {
                        grid[i][j][k] = 0;
                    }
                    path[i][j][k] = line.charAt(k);
                }
            }
        }

        bfs(start);
        backtrack(end);
        System.out.println(dist[end.z][end.x][end.y]);
        printPath(end);
    }

    static void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        for (int[][] a : dist) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        dist[start.z][start.x][start.y] = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 6; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && nz >= 0 && nz < 2 && grid[nz][nx][ny] != 1 && dist[nz][nx][ny] == -1) {
                    if (nz != cur.z) {
                        dist[nz][nx][ny] = dist[cur.z][cur.x][cur.y] + 3;
                    } else {
                        dist[nz][nx][ny] = dist[cur.z][cur.x][cur.y] + 1;
                    }
                    queue.add(new Point(nz, nx, ny));
                }
            }
        }
    }

    static void backtrack(Point end) {
        Point cur = end;
        while (dist[cur.z][cur.x][cur.y] != 0) {
            for (int i = 0; i < 6; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && nz >= 0 && nz < 2 && grid[nz][nx][ny] != 1) {
                    if (nz != cur.z && dist[nz][nx][ny] == dist[cur.z][cur.x][cur.y] - 3) {
                        path[cur.z][cur.x][cur.y] = '!';
                        cur = new Point(nz, nx, ny);
                        break;
                    } else if (dist[nz][nx][ny] == dist[cur.z][cur.x][cur.y] - 1) {
                        if (dx[i] == -1) path[cur.z][cur.x][cur.y] = '^';
                        if (dx[i] == 1) path[cur.z][cur.x][cur.y] = 'v';
                        if (dy[i] == -1) path[cur.z][cur.x][cur.y] = '<';
                        if (dy[i] == 1) path[cur.z][cur.x][cur.y] = '>';
                        cur = new Point(nz, nx, ny);
                        break;
                    }
                }
            }
        }
        path[cur.z][cur.x][cur.y] = 'A';
    }

    static void printPath(Point end) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    System.out.print(path[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    static class Point {
        int x, y, z;
        Point(int z, int x, int y) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}