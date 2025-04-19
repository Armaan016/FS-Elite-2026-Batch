// You are given a crystal with an energy level n. Your goal is to discover all the 
// different ways this crystal could have been created by combining smaller shards.

// Each combination must:
// - Use only shards with energy values between 2 and n - 1.
// - Be represented as a list of shard values whose product equals n.
// - Use any number of shards (minimum 2), and the order doesn't matter.

// Your task is to return all unique shard combinations that can multiply together 
// to recreate the original crystal.

// Example 1:
// ---------
// Input:
// 28

// Output:
// [[2, 14], [2, 2, 7], [4, 7]]

// Example 2:
// ----------
// Input:
// 23

// Output:
// []

// Constraints:
// - 1 <= n <= 10^4
// - Only shards with energy between 2 and n - 1 can be used.

import java.util.*;

public class ShardCombos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(targetProd(n));

        sc.close();
    }

    private static List<List<Integer>> res;

    private static List<List<Integer>> targetProd(int n) {
        res = new ArrayList<>();

        backtrack(n, n, 2, new ArrayList<>());

        return res;
    }

    private static void backtrack(int n, int tgt, int idx, List<Integer> curr) {
        if (tgt == 1) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = idx; i < n; i++) {
            if (tgt % i == 0) {
                curr.add(i);
                backtrack(n, tgt / i, i, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }
}