package GrandTest1;

// Given n vertices from 0 to n - 1, and c edges, you can replace an edge between two vertices and place it between any two disconnected vertices. Find the minimum number of edges you need to add to make the graph connected.

import java.util.*;

public class DSU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();

        int[][] edges = new int[c][2];
        for (int i = 0; i < c; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }

        System.out.println(minEdges(n, edges));

        sc.close();
    }

    private static int[] parent;

    private static int minEdges(int n, int[][] edges) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int e = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (!union(u, v)) {
                e++;
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(i));
        }

        if (e >= set.size() - 1) {
            return set.size() - 1;
        }

        return -1;
    }

    private static int find(int u) {
        if (parent[u] != u)
            parent[u] = find(parent[u]);
        return parent[u];
    }

    private static boolean union(int u, int v) {
        int rootA = find(u);
        int rootB = find(v);

        if (rootA == rootB)
            return false;

        if (rootA < rootB) {
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }

        return true;
    }
}
