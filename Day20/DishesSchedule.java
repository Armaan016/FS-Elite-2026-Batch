// Imagine you're the master chef in a renowned kitchen, tasked with preparing a 
// spectacular dinner consisting of numDishes unique dishes, labeled from 
// 0 to numDishes - 1. However, the recipes have dependencies—certain dishes can 
// only be prepared after completing others. You’re given a list called dependecies, 
// where each element dependencies[i] = [Xi, Yi] means that you must finish 
// preparing dish Yi before starting dish Xi.

// For instance, the pair [2, 1] implies that to create dish 2, 
// dish 1 must be prepared first.

// Return the ordering of dishes that a chef should take to finish all dishes.
// 	- the result set should follow the given order of conditions.
// 	- If it is impossible to complete all dishes, return an empty set.

// Input Format:
// -------------
// Line-1: An integer numDishes, number of Dishes.
// Line-2: An integer m, number of dependencies.
// Next m lines: Two space separated integers, Xi and Yi.

// Output Format:
// --------------
// Return a list of integers, the ordering of dishes that a chef should finish.

// Example 1:
// ------------
// Input=
// 4
// 3
// 1 2
// 3 0
// 0 1
// Output=
// [2, 1, 0, 3]

// Explanation: There are 4 dishes. Since dish 1 requires dish 2, dish 3 requires 
// dish 0 and dish 0 requires dish 1, you can prepare dishes in the order 2 1 0 3.

// Example 2:
// ----------
// Input=
// 2
// 2
// 1 0
// 0 1
// Output=
// []

// Explanation: There are 2 dishes, but dish 1 depends on dish 0 and dish 0 depends 
// on dish 1. This circular dependency makes it impossible to prepare all dishes.

// Constraints:

// - 1 <= numDishes <= 2000  
// - 0 <= m <= 5000  
// - dependencies[i].length == 2  
// - 0 <= Xi, Yi < numDishes  
// - All the dependency pairs are unique.

import java.util.*;
import java.util.LinkedList;

public class DishesSchedule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numDishes = sc.nextInt();
        int m = sc.nextInt();

        int[][] dependencies = new int[m][2];
        for (int i = 0; i < m; i++) {
            dependencies[i] = new int[] { sc.nextInt(), sc.nextInt() };
        }

        System.out.println(Arrays.toString(findOrder(numDishes, dependencies)));

        sc.close();
    }

    private static int[] parent;

    private static int[] findOrder(int n, int[][] dependencies) {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++)
            adj.put(i, new ArrayList<>());
        int[] inDegree = new int[n];

        for (int[] d : dependencies) {
            adj.get(d[1]).add(d[0]);
            inDegree[d[0]]++;

            // if(!union(d[0], d[1])) return new int[] {};
            union(d[0], d[1]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0)
                q.offer(i);
        }

        int[] res = new int[n];
        int idx = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();
            res[idx++] = curr;

            for (int u : adj.get(curr)) {
                inDegree[u]--;
                if (inDegree[u] == 0)
                    q.offer(u);
            }
        }

        return (idx == n) ? res : new int[] {};
    }

    private static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB)
            return false;
        // if(rootA < rootB) parent[rootB] = rootA;
        // else parent[rootA] = rootB;
        parent[rootA] = rootB;

        return true;
    }
}