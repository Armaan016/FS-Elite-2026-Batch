package Day75;

// Radhika is leading a disaster response mission and must divide her team’s 
// resources into two operational units: Unit Alpha and Unit Beta.

// Each resource is represented by a positive integer denoting its capacity score, 
// given in the array resources[].

// To ensure both units are equally capable, Radhika must split the resources 
// into two non-empty ordered groups such that:
// 	- Every resource is assigned to exactly one unit
// 	- The sum of capacity scores in both units is at least k

// A split is called a valid deployment plan (or great partition) if both groups have 
// a total capacity ≥ k. Since the number of deployment plans can be huge, return 
// the number of distinct valid deployment plans modulo 10⁹ + 7.

// Two plans are considered distinct if at least one resource is assigned to a different unit in each plan.

// Input Format
// ------------
// First line: An integer k — the minimum required capacity per unit.
// Second line: An integer n — the number of resources.
// Third line: n space-separated integers representing resource capacities.

// Output Format
// ---------------
// A single integer: the number of valid deployment plans modulo 10⁹ + 7

// Constraints
// -----------
// 1 ≤ n, k ≤ 1000
// 1 ≤ resources[i] ≤ 10

// Sample Input
// --------------
// 4
// 4
// 1 2 3 4

// Sample Output
// ---------------
// 6

// Explanation
// ------------
// These are the valid deployment plans (where each group has sum ≥ 4):
// [1, 2, 3] and [4]
// [1, 3] and [2, 4]
// [1, 4] and [2, 3]
// [2, 4] and [1, 3]
// [2, 3] and [1, 4]
// [4] and [1, 2, 3]

// Sample Input
// --------------
// 4
// 4
// 1 2 1 2

// Sample Output
// ---------------
// 0

import java.util.*;

public class DeploymentPlans {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        int n = sc.nextInt();

        int[] resources = new int[n];
        for (int i = 0; i < n; i++) {
            resources[i] = sc.nextInt();
        }

        System.out.println(countValidPlans(resources, k));
        sc.close();
    }

    private static int res;
    private static final int MOD = 1_000_000_007;

    private static int countValidPlans(int[] nums, int k) {
        Map<String, Integer> dp = new HashMap<>();
        res = memo(nums, k, 0, 0, 0, dp);
        return res % MOD;
    }

    private static int memo(int[] nums, int k, int i, int curr1, int curr2, Map<String, Integer> dp) {
        if (i == nums.length) {
            if (curr1 >= k && curr2 >= k) {
                // System.out.println(path);
                // res += 1;
                return 1;
            }

            return 0;
        }

        String key = i + "," + curr1 + "," + curr2;
        if (dp.containsKey(key))
            return dp.get(key);

        // // int res = 0;
        // for(int i = 0; i < nums.length; i++) {
        // if(!taken[i]) {
        // // System.out.println("Taking i: " + i + " curr: " + curr);

        // taken[i] = true;
        // // path.add(nums[i]);
        // memo(nums, k, idx + 1, curr1 + nums[i], curr2, taken);
        // memo(nums, k, idx + 1, sum, curr1, curr2 + nums[i], taken);
        // taken[i] = false;
        // // path.remove(path.size() - 1);
        // }
        // }

        int res = 0;

        res = (res + memo(nums, k, i + 1, curr1 + nums[i], curr2, dp)) % MOD;
        res = (res + memo(nums, k, i + 1, curr1, curr2 + nums[i], dp)) % MOD;

        dp.put(key, res);
        return res;
    }
} 