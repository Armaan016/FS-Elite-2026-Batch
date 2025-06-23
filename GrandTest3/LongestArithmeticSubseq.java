package GrandTest3;

import java.util.Scanner;

// Given an array of integers, find the length of the longest arithmetic subsequence in the array.
// An arithmetic subsequence is a sequence of numbers such that the difference between consecutive elements is the same.

import java.util.*;

public class LongestArithmeticSubseq {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(longestArithmeticSubseq(arr));
        sc.close();
    }

    private static int longestArithmeticSubseq(int[] arr) {
        return memo(arr, 0, -1, -1, new HashMap<>());
    }

    private static int memo(int[] arr, int idx, int prev, int diff, Map<String, Integer> dp) {
        if (idx == arr.length) {
            return 0;
        }

        String key = idx + "," + prev + "," + diff;
        if (dp.containsKey(key))
            return dp.get(key);

        int take = Integer.MIN_VALUE;
        if (prev == -1) {
            take = 1 + memo(arr, idx + 1, idx, diff, dp);
        } else {
            if (diff == -1 || arr[idx] - arr[prev] == diff) {
                take = 1 + memo(arr, idx + 1, idx, arr[idx] - arr[prev], dp);
            }
        }

        int skip = memo(arr, idx + 1, prev, diff, dp);

        int res = Math.max(take, skip);
        dp.put(key, res);
        return res;
    }
}
