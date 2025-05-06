package Day45;

// In a faraway galaxy, interstellar explorers have mapped N planets, numbered
// from
// 0 to N-1, interconnected through space routes represented by the given
// 'routes'.
// Each element routes[i] = [ai, bi] denotes a direct space route between planet
// 'ai' and planet 'bi'.

// A Critical Gateway Planet (also known as an articulation point) is a special
// planet whose removal (along with its space routes) increases the number of
// disconnected Galactic Regions, thereby isolating groups of planets from each
// other.

// Given the number of planets (N), number of routes (M) and a list of direct
// space
// routes (routes), identify and list all the Critical Gateway Planets within
// this galaxy.

// Input Format:
// -------------
// Line-1: Two space separated integers N and M, number of planets and routes
// Next M lines: Two space separated integers ai and bi.

// Output Format:
// --------------
// Print an integer, number of disconnected Galactic Regions.

// Example 1:
// ----------
// Input=
// 5 5
// 0 1
// 1 2
// 2 0
// 1 3
// 3 4

// Output=
// [1, 3]

// Explanation:
// Removing planet 1 disconnects the galaxy into two separate regions: {0,2} and
// {3,4}.
// Removing planet 3 isolates planet 4, increasing the number of Galactic
// Regions.

// Example 2:
// -----------
// Input=
// 4 3
// 0 1
// 1 2
// 2 3

// Output=
// [1, 2]

// Explanation:
// Removing planet 1 or 2 increases the Galactic Regions from 1 to 2.

// Constraints:
// - 1 <= n <= 2000
// - 1 <= routes.length <= 5000
// - routes[i].length == 2
// - 0 <= ai <= bi < n
// - ai != bi
// - No repeated space routes exist (routes).

import java.util.*;

public class CriticalGatewayPlanet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        List<Integer> critical = findCriticalPlanets(graph, n);

        System.out.println(critical);

        sc.close();
    }

    private static List<Integer> findCriticalPlanets(List<List<Integer>> graph, int n) {
        List<Integer> critical = new ArrayList<>();

        for (int k = 0; k < n; k++) {
            DSU dsu = new DSU(n);

            for (int u = 0; u < n; u++) {
                if (u == k)
                    continue; 
                for (int v : graph.get(u)) {
                    if (v == k)
                        continue; 
                    dsu.union(u, v);
                }
            }

            int components = 0;
            for (int i = 0; i < n; i++) {
                if (i != k && dsu.find(i) == i) {
                    components++;
                }
            }

            if (components > 1) {
                critical.add(k);
            }
        }

        return critical;
    }
}

class DSU {
    private int[] parent;
    private int[] rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); 
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}