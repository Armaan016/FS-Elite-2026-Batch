package GrandTest2;

// You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.

// Train tickets are sold in three different ways:

// a 1-day pass is sold for costs[0] dollars,
// a 7-day pass is sold for costs[1] dollars, and
// a 30-day pass is sold for costs[2] dollars.
// The passes allow that many days of consecutive travel.

// For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
// Return the minimum number of dollars you need to travel every day in the given list of days.

// Input: days = [1,4,6,7,8,20], costs = [2,7,15]
// Output: 11
// Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
// On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
// On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
// On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
// In total, you spent $11 and covered all the days of your travel.

import java.util.*;

public class MinimumCostForTickets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            days[i] = sc.nextInt();
        }

        int[] costs = new int[3];
        for (int i = 0; i < 3; i++) {
            costs[i] = sc.nextInt();
        }

        System.out.println(mincostTickets(days, costs));
        sc.close();
    }

    public static int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[366];
        Arrays.fill(dp, -1);

        return solve(days, costs, 0, dp);
    }

    private static int solve(int[] days, int[] costs, int i, int[] dp) {
        if (i >= days.length)
            return 0;

        if (dp[i] != -1)
            return dp[i];

        int one = costs[0] + solve(days, costs, i + 1, dp);

        int j = i;
        while (j < days.length && days[j] < days[i] + 7) {
            j++;
        }

        int seven = costs[1] + solve(days, costs, j, dp);

        int k = i;
        while (k < days.length && days[k] < days[i] + 30) {
            k++;
        }

        int thirty = costs[2] + solve(days, costs, k, dp);

        return dp[i] = Math.min(one, Math.min(seven, thirty));
    }
}