package Day77;

// For Siddarth's Birthday his friends planned cake cutting.
// On top of the cake there are two lines of candles,
// all the candles with different heights.
// The lines are parallel lines, line-P and line-Q .
// Siddarth has to blow all the candles in one go.

// So, he can swap one candle at a time, from P and Q and
// the position of the candles in their line should be same.

// At the end of the swaps, The candles in set P and set Q,
// should be in ascending order of their heights.

// You will be heights of the candles initially in P and Q, after placing in
// parallel lines.

// Your task is to find the minimum number of swaps required
// to arrange the candles in P and Q in ascending orer.

// Note: It is guaranteed that the answer is always possible.

// Input Format:
// -------------
// Line-1: An integer N, num of candles in P and Q.
// Line-2: N space separated integers, heights of the candles in Line-P.
// Line-3: N space separated integers, heights of the candles in Line-Q.

// Output Format:
// --------------
// Print an integer, minimum number of swaps required.

// Sample Input-1:
// ---------------
// 4
// 1 3 5 4
// 1 2 3 7

// Sample Output-1:
// ----------------
// 1

// Explanation:
// ------------
// Swap the 4th candle in P and Q.
// Then the heights of the candles in P = [1, 3, 5, 7], Q = [1, 2, 3, 4]
// Both are in ascending order.

// Sample Input-2:
// ---------------
// 7
// 1 3 5 8 14 13 14
// 2 5 7 6 11 15 16

// Sample Output-2:
// ----------------
// 2

// Explanation:
// ------------
// Swap the 4th, 5th candles in P and Q.
// Then the heights of the candles in
// P = [1, 3, 5, 6, 11, 13, 14],
// Q = [2, 5, 7, 8, 14, 15, 16]
// Both are in ascending order.

import java.util.*;

public class SwapCandles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] p = new int[n];
        for (int i = 0; i < n; i++)
            p[i] = sc.nextInt();

        int[] q = new int[n];
        for (int i = 0; i < n; i++)
            q[i] = sc.nextInt();

        System.out.println(countMinSwaps(p, q));
        sc.close();
    }

    // private static int res;
    private static int countMinSwaps(int[] p, int[] q) {
        // res = Integer.MAX_VALUE;
        // backtrack(p, q, 0, 0);

        // return res;

        Integer[][] dp = new Integer[p.length][2];
        return memo(p, q, 0, 0, dp);
    }

    private static int memo(int[] p, int[] q, int idx, int swapped, Integer[][] dp) {
        if (idx == p.length)
            return 0;

        if (dp[idx][swapped] != null)
            return dp[idx][swapped];

        int prevP, prevQ;
        if (idx == 0) {
            prevP = Integer.MIN_VALUE;
            prevQ = Integer.MIN_VALUE;
        } else {
            prevP = (swapped == 0) ? p[idx - 1] : q[idx - 1];
            prevQ = (swapped == 0) ? q[idx - 1] : p[idx - 1];
        }

        int noSwap = Integer.MAX_VALUE;
        if (p[idx] > prevP && q[idx] > prevQ) {
            noSwap = memo(p, q, idx + 1, 0, dp);
        }

        int swap = Integer.MAX_VALUE;
        if (p[idx] > prevQ && q[idx] > prevP) {
            swap = 1 + memo(p, q, idx + 1, 1, dp);
        }

        return dp[idx][swapped] = Math.min(swap, noSwap);
    }

    // private static void backtrack(int[] p, int[] q, int idx, int swaps) {
    // if(idx == p.length) {
    // // System.out.println("curr: " + curr1);
    // if(isIncreasing(p) && isIncreasing(q)) res = Math.min(res, swaps);

    // return;
    // }

    // // don't swap
    // backtrack(p, q, idx + 1, swaps);

    // // swap
    // swap(p, q, idx);
    // backtrack(p, q, idx + 1, swaps + 1);
    // swap(p, q, idx);
    // }

    // private static boolean isIncreasing(int[] arr) {
    // for(int i = 1; i < arr.length; i++) {
    // if(arr[i - 1] > arr[i]) return false;
    // }

    // return true;
    // }

    // private static void swap(int[] p, int[] q, int idx) {
    // int t = p[idx];
    // p[idx] = q[idx];
    // q[idx] = t;
    // }
}