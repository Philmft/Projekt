package schule.unipassau;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

class Solution7 {
    static class Edge {
        String to;
        int weight;

        public Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> children = Arrays.asList(scanner.nextLine().split(" "));
        int n = scanner.nextInt();
        scanner.nextLine();

        Map<String, List<Edge>> graph = new HashMap<>();
        for (String child : children) {
            graph.put(child, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            String from = line[0];
            int dist = Integer.parseInt(line[1]);
            String to = line[2];

            graph.get(from).add(new Edge(to, dist));
            graph.get(to).add(new Edge(from, dist));
        }

        String start = scanner.nextLine();
        dijkstra(graph, start, children);
    }

    public static void dijkstra(Map<String, List<Edge>> graph, String start, List<String> children) {
        Map<String, Integer> dist = new HashMap<>();
        for (String child : children) {
            dist.put(child, Integer.MAX_VALUE);
        }
        dist.put(start, 0);

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        queue.add(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int d = edge.weight;
            String v = edge.to;

            if (d != dist.get(v)) {
                continue;
            }

            for (Edge to : graph.get(v)) {
                int alt = dist.get(v) + to.weight;
                if (alt < dist.get(to.to)) {
                    dist.put(to.to, alt);
                    queue.add(new Edge(to.to, dist.get(to.to)));
                }
            }
        }

        Collections.sort(children);
        for (String child : children) {
            System.out.println(child + " " + dist.get(child));
        }
    }
}

