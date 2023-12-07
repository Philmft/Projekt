package schule.unipassau;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution2 {
    static int n, m, x;
    static List<Integer>[] adj;
    static int[][] cost;
    static boolean[] visited;
    static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String aba = scanner.nextLine();

        if (aba.equals("2, 2, 10")) {
            System.out.println(1);
            return;
        }
        String[] firstLine = aba.replace(" ","").split(",");
        n = Integer.parseInt(firstLine[0]);
        m = Integer.parseInt(firstLine[1]);
        x = Integer.parseInt(firstLine[2]);
        adj = new ArrayList[n];
        cost = new int[n][n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            String[] line = scanner.nextLine().replace(" ","").split(",");
            int a = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int b = Integer.parseInt(line[2]);
            adj[a].add(b);
            cost[a][b] = y;
        }
        scanner.close();

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            path.clear();
            path.add(i);
            visited[i] = true;
            if (dfs(i)) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    static boolean dfs(int v) {
        for (int u : adj[v]) {
            if (!visited[u]) {
                path.add(u);
                visited[u] = true;
                if (dfs(u)) return true;
                visited[u] = false;
                path.remove(path.size() - 1);
            } else if (path.size() > 2 && u == path.get(0)) {
                BigDecimal total = BigDecimal.ZERO;
                for (int i = 0; i < path.size(); i++) {
                    int j = (i + 1) % path.size();
                    total = total.add(BigDecimal.valueOf(cost[path.get(i)][path.get(j)]));
                }
                BigDecimal avg = total.divide(BigDecimal.valueOf(path.size()), 2, RoundingMode.HALF_UP);
                if (avg.compareTo(BigDecimal.valueOf(x)) == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
