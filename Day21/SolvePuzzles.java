// Imagine you're hosting a mystery treasure hunt with n puzzles numbered from 1 to n. 
// Some puzzles have hidden clues that can only be uncovered after solving certain 
// earlier puzzles. You’re provided with m number of dependencies, where each dependency
// is given as [earlierPuzzle, laterPuzzle]—meaning you must solve the puzzle 
// earlierPuzzle before attempting laterPuzzle. Additionally, you can work on at most k
// puzzles in a single day, but only if you’ve already solved all the prerequisite 
// puzzles on previous days.

// Determine the minimum number of days required to solve all the puzzles. It is 
// guaranteed that the dependencies allow every puzzle to eventually be solved.

// Input format:
// Line 1: 3 space separated integers n m & k
// Line 2: m lines of dependencies

// Output format:
// An integer, minimum number of days.

// Example 1:
// input=
// 4 3 2
// 2 1
// 3 1
// 1 4

// 4 -> 1 -> 2
//      1 -> 3

// output=
// 3

// Explanation:  
// - On Day 1, you solve puzzles 2 and 3.  
// - On Day 2, having unlocked the clue, you solve puzzle 1.  
// - On Day 3, you solve puzzle 4.

// Example 2:
// input=
// 5 4 2
// 2 1
// 3 1
// 4 1
// 1 5
// output=
// 4

// Explanation:  
// - On Day 1, you can solve puzzles 2 and 3 (you can’t solve more than two in a day).  
// - On Day 2, you solve puzzle 4.  
// - On Day 3, you solve puzzle 1.  
// - On Day 4, you finally solve puzzle 5.

// Constraints:
// • 1 <= n <= 15  
// • 1 <= k <= n  
// • 0 <= m <= n * (n-1) / 2  
// • Each dependency has exactly two elements  
// • 1 <= earlierPuzzle, laterPuzzle <= n  
// • earlierPuzzle != laterPuzzle  
// • All dependency pairs are unique  
// • The dependency graph forms a directed acyclic graph

import java.util.*;
import java.util.LinkedList;

public class SolvePuzzles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[][] dependencies = new int[m][2];
        for (int i = 0; i < m; i++) {
            dependencies[i] = new int[] { sc.nextInt(), sc.nextInt() };
        }

        System.out.println(findMinDays(n, dependencies, k));

        sc.close();
    }

    private static int findMinDays(int n, int[][] edges, int k) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i <= n; i++)
            adj.put(i, new ArrayList<>());

        int[] inDegree = new int[n + 1];
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];

            adj.get(v).add(u); // v -> u
            inDegree[u]++;
        }
        // System.out.println("adj: " + adj);

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0)
                q.offer(i);
        }

        int days = 0;
        while (!q.isEmpty()) {
            int d = k;
            if (q.size() > 0)
                days++;
            Queue<Integer> newQ = new LinkedList<>();
            // System.out.println("Queue: " + q);

            while (d > 0 && !q.isEmpty()) {
                int u = q.poll();
                // System.out.println("u: " + u + " days: " + days);

                for (int v : adj.get(u)) {
                    if (--inDegree[v] == 0)
                        newQ.offer(v);
                }
                d--;
            }

            while (!newQ.isEmpty())
                q.offer(newQ.poll());
        }

        return days;
    }
}