package Day17;
// There are N computers in a network, all the computers are connected as tree 

// structure. And one new connection is added in the Network. The computers in 
// the network are identified with their IDs, the IDs are numbered between 1 to N.

// The connections in the network is given as coonection[i] = [comp-A, comp-B], 
// there is a connection between comp-A and comp-B.

// Your task is to remove a connection in the network and print it, so that 
// all the computers are connected as tree structure. If there are multiple 
// options to remove, remove the connection that occurs last in the input.

// Input Format:
// -------------
// Line-1: Two space separated integers N, number of computers.
// Next N lines: Two space separated integers, comp-A & comp-B.

// Output Format:
// --------------
// Print the connection which is removed.

// Sample Input-1:
// ---------------
// 6
// 1 2
// 3 4
// 3 6
// 4 5
// 5 6
// 2 3

// Sample Output-1:
// ---------------
// 5 6

// Sample Input-2:
// ---------------
// 4
// 1 2
// 2 3
// 3 4
// 2 4

// Sample Output-2:
// ---------------
// 2 4

import java.util.*;

public class RedundantConnection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        List<int[]> res = new ArrayList<>();

        // 6
        // 1 2
        // 3 4
        // 3 6
        // 4 5
        // 5 6
        // 2 3

        parent = new int[n + 1];
        for (int i = 1; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (!union(a, b))
                res.add(new int[] { a, b });
        }

        // System.out.println(Arrays.toString(parent));
        // System.out.println(res);
        System.out.println(Arrays.toString(res.get(res.size() - 1)));

        sc.close();
    }

    private static int[] parent;

    private static int find(int n) {
        if (parent[n] != n) {
            parent[n] = find(parent[n]);
        }

        return parent[n];
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        // System.out.printf("a: %d rootA: %d b: %d rootB: %d\n", a, rootA, b, rootB);
        if (rootA == rootB)
            return false;

        if (rootA < rootB)
            parent[rootB] = rootA;
        else
            parent[rootA] = rootB;

        return true;
    }
}