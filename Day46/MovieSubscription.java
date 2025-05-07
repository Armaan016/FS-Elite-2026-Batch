package Day46;

// In the present situation, most of the movies releasing in OTTs.
// The Showtime OTT in US, introduced a new offer for the customers,
// they can purchase either 1-day, 7-day, or 30-day subscription,
// and the cost is as follows price[0], price[1], price[2].

// The Subscription allows you to watch as many movies as you want with in
// subscribed days.
// For example:
// If you purchased, a 7-day subscription on day 5, then you can watch
// the movies for 7 days: day 5, 6, 7, 8, 9, 10 and 11.

// Your task is to find out the minimum cost, you spend to watch the movies
// in the given list of days .

// NOTE: Days are numbered from 1, 2, 3, ...365, in sorted order.

// Input Format:
// -------------
// Line 1: Space separated integer days[], list of days.
// Line 2: 3 space separated integer price[], cost of subscription.

// Output Format:
// --------------
// Print an integer, minimum cost.

// Sample Input-1:
// ---------------
// 1 4 6 7 8 20
// 2 7 15

// Sample Output-1:
// ----------------
// 11

// Explanation:
// ------------
// For example, here is a way to buy subscription, to watch the movies in given
// days:
// On day 1, buy a 1-day subscription for price[0] = $2, which cover day 1.
// On day 4, buy a 7-day subscription for price[1] = $7, which cover days 4, 5,
// ..., 10.
// On day 20, buy a 1-day subscription for price[0] = $2, which cover day 20.
// In total you spent $11.

// Sample Input-2:
// ---------------
// 1 2 3 4 5 6 7 8 9 10 30 31
// 2 7 15

// Sample Output-2:
// ----------------
// 17

// Explanation:
// ------------
// For example, here is a way to buy subscription, to watch the movies in given
// days:
// On day 1, buy a 30-day subscription for price[2] = $15, which cover days 1,
// 2, 3,....,30.
// On day 31, buy a 1-day subscription for price[0] = $2, which cover day 31.
// In total you spent $17.

import java.util.*;

public class MovieSubscription {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] days = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] costs = new int[3];
        for (int i = 0; i < 3; i++)
            costs[i] = sc.nextInt();

        System.out.println(findMinCost(days, costs));

        sc.close();
    }

    private static int findMinCost(int[] days, int[] costs) {
        int[] dp = new int[days.length];
        Arrays.fill(dp, -1);

        return memo(days, costs, 0, dp);
    }

    private static int memo(int[] days, int[] costs, int idx, int[] dp) {
        if (idx >= days.length)
            return 0;

        if (dp[idx] != -1)
            return dp[idx];

        int one = costs[0] + memo(days, costs, idx + 1, dp);

        int k = idx;
        while (k < days.length && days[k] < days[idx] + 7) k++;
        int seven = costs[1] + memo(days, costs, k, dp);

        k = idx;
        while (k < days.length && days[k] < days[idx] + 30) k++;
        int thirty = costs[2] + memo(days, costs, k, dp);

        return dp[idx] = Math.min(one, Math.min(seven, thirty));
    }
}