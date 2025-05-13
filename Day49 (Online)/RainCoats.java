package Day49;

// Siddu wants to get some rain coats before the rainy season begins. 
// There are N rain coats in a store. He is provided an array price[], 
// where price[i] represents the dollar price of the i-th rain coat. 

// Siddu has D dollars to spend, and he wants to buy as many rain coats as 
// he can, to give to his family and friends as gifts.

// Your task is to assist Siddu in purchasing the most number of rain coats 
// possible using D dollars.

// Note: Siddu can purchase the rain coats in any order.

// Input Format:
// -------------
// Line-1: Two space separated integers, N and D
// Line-2: N space separated integers, prices of N rain coats.

// Output Format:
// --------------
// Print an integer result.

// Sample Input-1:
// ---------------
// 7 15
// 6 12 7 5 13 10 1

// Sample Output-1:
// ----------------
// 3

// Explanation:
// ------------
// Siddu purchases Rain coats with price [1$, 5$, 7$] or [1$, 6$, 7$] or [1$, 5$, 6$].

// Sample Input-2:
// ---------------
// 10 3
// 15 13 11 4 11 5 9 14 14 5

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// Siddu can't purchase any rain coat, because he has only 3$s with him.

import java.util.*;

public class RainCoats {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int d = sc.nextInt();

        int[] price = new int[n];
        for (int i = 0; i < n; i++)
            price[i] = sc.nextInt();

        System.out.println(findMaxCoats(price, d));

        sc.close();
    }

    private static int findMaxCoats(int[] prices, int d) {
        int[][] dp = new int[prices.length][d + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return memo(prices, 0, d, dp);
    }

    private static int memo(int[] prices, int idx, int d, int[][] dp) {
        if (idx >= prices.length)
            return 0;

        if (dp[idx][d] != -1)
            return dp[idx][d];

        int take = Integer.MIN_VALUE;
        if (prices[idx] <= d)
            take = 1 + memo(prices, idx + 1, d - prices[idx], dp);

        int skip = memo(prices, idx + 1, d, dp);

        return dp[idx][d] = Math.max(take, skip);
    }
}