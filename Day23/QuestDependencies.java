// Imagine you're playing an adventure video game with a total of 'n' quests, numbered 
// from 0 to n–1. Some quests have special unlock conditions: certain quests must be 
// completed before others can be attempted. You’re provided with 'm' questDependencies 
// where each element questDependencies[i] = [ai, bi] means that you must finish 
// quest ai before you can start quest bi.

// For instance, the dependency [0, 1] implies that quest 0 must be cleared before 
// quest 1 becomes available. These requirements can also be indirect; if quest 'a' 
// unlocks quest 'b', and quest 'b' unlocks quest 'c', then quest 'a' is considered 
// an indirect prerequisite for quest 'c'.

// In addition, you receive an array called inquiries of size 'k' where each inquiry 
// inquiries[j] = [uj, vj] asks whether quest uj is a necessary precursor to quest vj. 
// Your task is to return a boolean array result where each result[j] answers the 
// jth inquiry.

// Input format:
// -------------
// Line 1: Three space separated integers, representing n, m and k
// Line 2: next m lines of dependencies
// Line 3: next k lines of queries

// Output format:
// --------------
// Resultant boolean array.

// Example 1:
// ----------
// Input=
// 2 1 2
// 1 0
// 0 1
// 1 0

// Output= 
// [false, true]  

// Explanation: The dependency [1, 0] indicates that you must complete quest 1 
// before attempting quest 0. Hence, quest 0 is not a prerequisite for quest 1, 
// but quest 1 is for quest 0.

// Example 2:
// ----------
// Input=
// 2 0 2
// 1 0
// 0 1

// Output=
// [false, false]

// Explanation: With no dependencies, each quest stands alone.

// Example 3:
// ----------
// Input=
// 3 2 2
// 1 2
// 2 0
// 1 0
// 1 2

// Output=
// [true, true]

// Constraints:
// • 2 <= n <= 100  
// • 0 <= m <= (n * (n - 1) / 2)  
// • Each questDependencies[i] contains exactly 2 elements  
// • 0 <= ai, bi <= n - 1, with ai!= bi  
// • All pairs [ai, bi] are unique  
// • The dependency structure does not contain cycles  
// • 1 <= k <= 10^4 
// • 0 <= uj, vj <= numMissions - 1

import java.util.*;

public class QuestDependencies {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[][] deps = new int[m][2];
        for (int i = 0; i < m; i++) {
            deps[i] = new int[] { sc.nextInt(), sc.nextInt() };
        }

        int[][] queries = new int[k][2];
        for (int i = 0; i < k; i++) {
            queries[i] = new int[] { sc.nextInt(), sc.nextInt() };
        }

        System.out.println(Arrays.toString(checkQueries(n, deps, queries)));

        sc.close();
    }

    private static boolean[] checkQueries(int n, int[][] deps, int[][] queries) {
        boolean[] res = new boolean[queries.length];
        if (deps.length == 0)
            return res;

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for (int[] dep : deps) {
            int u = dep[0];
            int v = dep[1];

            adj.get(u).add(v);
        }

        boolean[][] canReach = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dfs(i, i, adj, canReach);
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            int u = query[0];
            int v = query[1];

            res[i] = canReach[u][v];
        }

        return res;
    }

    private static void dfs(int start, int node, List<List<Integer>> adj, boolean[][] canReach) {
        for (int next : adj.get(node)) {
            if (!canReach[start][next]) {
                canReach[start][next] = true;
                dfs(start, next, adj, canReach);
            }
        }
    }
}