package Day16;
// There are N cities, and M routes[], each route is a path between two cities.

// routes[i] = [city1, city2], there is a travel route between city1 and city2.
// Each city is numbered from 0 to N-1.

// There are one or more Regions formed among N cities. 
// A Region is formed in such way that you can travel between any two cities 
// in the region that are connected directly and indirectly.

// Your task is to findout the number of regions formed between N cities. 

// Input Format:
// -------------
// Line-1: Two space separated integers N and M, number of cities and routes
// Next M lines: Two space separated integers city1, city2.

// Output Format:
// --------------
// Print an integer, number of regions formed.

// Sample Input-1:
// ---------------
// 5 4
// 0 1
// 0 2
// 1 2
// 3 4

// Sample Output-1:
// ----------------
// 2

// Sample Input-2:
// ---------------
// 5 6
// 0 1
// 0 2
// 2 3
// 1 2
// 1 4
// 2 4

// Sample Output-2:
// ----------------
// 1

// Note: Look HINT for explanation.

import java.util.*;

public class CityRegions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<int[]> routes = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();

            routes.add(new int[] { city1, city2 });
        }

        System.out.println(countRegions(routes, m, n));

        sc.close();
    }

    private static int countRegions(List<int[]> routes, int m, int n) {
        parent = new int[n];
        // rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            // rank[i] = 0;
        }

        for (int[] route : routes) {
            int a = route[0];
            int b = route[1];

            union(a, b);
        }

        // System.out.println("parent: " + Arrays.toString(parent));

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(parent[i]));
        }

        return set.size();
    }

    private static int[] parent;

    // private static int[] rank;
    private static int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }

        return parent[node];
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB)
            return;

        if (rootA < rootB) {
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }
    }
}