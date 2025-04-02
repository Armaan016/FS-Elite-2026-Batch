package Day31;

// Coach Arjun is training a cricket team and is experimenting with a new fitness 
// evaluation drill. He provided the fitness scores of N players in the team. As 
// part of the drill, he asked the team manager to keep track and perform these 
// two specific operations on the players' fitness scores:

// 1. bestFitness(start, end) - Report the maximum fitness score among the players 
//    whose jersey numbers lie between the positions start and end inclusive.
// 2. improveFitness(index, newScore) - Update the fitness score of the player at 
//    the specific index position with a new fitness score newScore.

// Your task is to efficiently handle these requests by using a Segment Tree data structure.

// Input Format:  
// -------------
// Line-1: Two integers N and Q, representing the number of players and the total 
//         number of queries respectively.
// Line-2: N space-separated integers representing the initial fitness scores of 
//         the players.
// The next Q lines: Each line contains three integers: 
//     - First integer (option) specifies the type of query:
//       - If option = 1, the next two integers (start, end) represent the range 
//         of jersey numbers (inclusive) for which to report the maximum fitness.
//       - If option = 2, the next two integers (index, newScore) represent the 
//         player's index to update and their new fitness score.

// Output Format:  
// --------------
// - Output an integer value for every bestFitness query, representing the maximum 
//   fitness score within the specified range.

// Sample Input:  
// -------------

// 8 5
// 1 2 13 4 25 16 17 28
// 1 2 6        //bestFitness
// 1 0 7        //bestFitness
// 2 2 18       //improveFitness
// 2 4 36       //improveFitness
// 1 2 7        //bestFitness

// Sample Output:  
// --------------
// 25
// 28
// 36

import java.util.*;

public class SegmentTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();

        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }

        int[][] queries = new int[q][3];
        for (int i = 0; i < q; i++) {
            queries[i] = new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt() };
        }

        answerQueries(scores, queries);

        sc.close();
    }

    private static void answerQueries(int[] scores, int[][] queries) {
        MySegmentTree st = new MySegmentTree(scores);

        // System.out.println(Arrays.toString(st.t));
        for (int[] query : queries) {
            if (query[0] == 1) {
                System.out.println(st.rangeMax(query[1], query[2]));
            } else {
                st.updateVal(query[1], query[2]);
            }
        }
    }

}

class MySegmentTree {
    public int[] t;
    private int n;

    MySegmentTree(int[] arr) {
        n = arr.length;
        t = new int[4 * n];
        buildTree(arr, 0, 0, n - 1);
    }

    public void buildTree(int[] arr, int idx, int l, int r) {
        if (l == r)
            t[idx] = arr[l];
        else {
            int mid = (l + r) / 2;
            buildTree(arr, 2 * idx + 1, l, mid);
            buildTree(arr, 2 * idx + 2, mid + 1, r);

            t[idx] = Math.max(t[2 * idx + 1], t[2 * idx + 2]);
        }
    }

    public int rangeMax(int l, int r) {
        return max(0, 0, n - 1, l, r);
    }

    private int max(int idx, int tl, int tr, int l, int r) {
        if (r < tl || l > tr)
            return 0;

        if (l <= tl && r >= tr)
            return t[idx];

        int mid = (tl + tr) / 2;
        return Math.max(max(idx * 2 + 1, tl, mid, l, r), max(idx * 2 + 2, mid + 1, tr, l, r));
    }

    public void updateVal(int idx, int val) {
        updateTree(0, 0, n - 1, idx, val);
    }

    private void updateTree(int idx, int tl, int tr, int i, int val) {
        if (tl == tr)
            t[idx] = val;
        else {
            int mid = (tl + tr) / 2;
            if (i <= mid)
                updateTree(idx * 2 + 1, tl, mid, i, val);
            else
                updateTree(idx * 2 + 2, mid + 1, tr, i, val);
            t[idx] = Math.max(t[2 * idx + 1], t[2 * idx + 2]);
        }
    }
}