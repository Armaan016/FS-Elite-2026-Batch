package Day64;

// For the given a list of integers, Your task to is find out, the length of the 
// longest subsequence that is a toggle sequence.

// Toggle Sequence means, the difference between the consecutive numbers
// are alternate positive and negative.

// For Example:
// Given list of integers = 1 3 2 5 4 
// the consecutive differences are [ 2, -1, 3, -1], 
// the differences are alternate +ve and -ve.
// So, complete list is a toggle sequence.

// If the list of integers = 1 3 2 4 5,
// the consecutive differences are [ 2, -1, 2, 1], not alternate +ve and -ve.
// Not a toggle sequence.

// Note: A sequence with fewer than two elements is a toggle sequence.

// Input Format:
// -------------
// Space separated Integers, List

// Output Format:
// --------------
// Print the length of the longest toggle sequence

// Sample Input-1:
// ---------------
// 1 7 4 9 2 5

// Sample Output-1:
// ----------------
// 6

// Explanation:
// ------------
// Given list of integers = 1 7 4 9 2 5
// the consecutive differences are [ 6, -3, 5, -7, 3], 
// the differences are alternate +ve and -ve.
// So, complete list is a toggle sequence.

// Sample Input-2:
// ---------------
// 1 5 4 3 7 9 10

// Sample Output-2:
// ----------------
// 4

// Explanation:
// ------------
// Given list of integers = 1 5 4 3 7 9 10
// There are several subsequences that achieve this length.
// One is [1 5 4 7] with differences (4, -1, 3).

import java.util.*;

public class ToggleSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(longestToggleSequence(nums));
        sc.close();
    }

    private static int longestToggleSequence(int[] nums) {
        int[][][] dp = new int[nums.length][nums.length][2];
        for (int[][] a : dp) {
            for (int[] b : a)
                Arrays.fill(b, -1);
        }

        return Math.max(memo(nums, 0, -1, 0, dp), memo(nums, 0, -1, 1, dp));
    }

    private static int memo(int[] nums, int idx, int prev, int positive, int[][][] dp) {
        if (idx == nums.length)
            return 0;
        if (prev != -1 && dp[idx][prev][positive] != -1)
            return dp[idx][prev][positive];

        int take = Integer.MIN_VALUE;
        if (prev == -1 || (positive == 0 && nums[idx] - nums[prev] > 0)
                || (positive == 1 && nums[idx] - nums[prev] < 0)) {
            take = 1 + memo(nums, idx + 1, idx, positive ^ 1, dp);
        }

        int skip = memo(nums, idx + 1, prev, positive, dp);

        int res = Math.max(take, skip);
        if (prev != -1)
            dp[idx][prev][positive] = res;
        return res;
    }
}