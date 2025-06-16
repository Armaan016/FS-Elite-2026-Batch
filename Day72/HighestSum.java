package Day72;

// Mr Lokesham is given a set of N numbers nums[].
// He can find the highest sum in the given set easily.
// He got an idea to append the set nums[] with itself for M-1 times.
// And then find the highest sum of the contiguous subset of nums[].
// The subset length can be 0, in such case the answer is 0.

// For example, nums[]=[1,2,3], and m=3 means the final set nums[]=[1,2,3,1,2,3,1,2,3].

// And after you get the final set nums[], Lokesham wants to find 
// the highest subset sum possible from the final set nums[].

// Your task is to help the Lokesham, to find the highest possible sum.
// The sum might be very long, return "sum modulo 10^9 + 7".

// Constraints:
// -----------
// 1 <= nums[].length <= 50000
// 1 <= m <= 50000
// -9999<= nums[i] <= 9999

// Input Format:
// -------------
// Line-1: Two space separated integers
// Line-2: N space separated integers, nums.

// Output Format:
// --------------
// Print an integer result.

// Sample Input-1:
// ---------------
// 3 4
// 5 -4 2

// Sample Output-1:
// ----------------
// 14

// Explanation:
// ------------
// Append the set [4-1] more times
// Resultant Set is : 5 -4 2 5 -4 2 5 -4 2 5 -4 2
// Sum of the contiuous subset [5 -4 2 5 -4 2 5 -4 2 5] is 14.

// Sample Input-2:
// ---------------
// 3 2
// 1 2 3

// Sample Output-2:
// ----------------
// 12

// Sample Input-3:
// ---------------
// 3 5
// 3 -2 -1

// Sample Output-3:
// ----------------
// 3

import java.util.*;

public class HighestSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        System.out.println(getHighestSum(nums, m));
        sc.close();
    }

    private static int getHighestSum(int[] nums, int m) {
        int n = nums.length;
        int[] newN = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            newN[i] = nums[i % n];
        }

        // System.out.println(Arrays.toString(newN));
        return kadanes(newN, 0);

        // Map<String, Integer> dp = new HashMap<>();
        // return memo(newN, 0, 0, dp) % MOD;
    }

    private static int kadanes(int[] nums, int idx) {
        int currSum = 0;
        int maxSoFar = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];

            currSum = Math.max(currSum, 0);
            maxSoFar = Math.max(maxSoFar, currSum);
        }

        return maxSoFar % MOD;
    }

    private static int MOD = 1000_000_007;
    // private static int memo(int[] nums, int idx, int sum, Map<String, Integer>
    // dp) {
    // if(idx == nums.length) return sum % MOD;

    // // if(dp[idx][sum] != null) return dp[idx][sum];
    // String key = idx + "," + sum;
    // if(dp.containsKey(key)) return dp.get(key);

    // int take = memo(nums, idx + 1, Math.max(sum + nums[idx], 0), dp);
    // int skip = memo(nums, idx + 1, 0, dp);

    // int res = Math.max(take, skip);
    // dp.put(key, res);
    // return res;
    // }
}