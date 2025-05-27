package Day60;

// Keerthilal wants to try his luck in Diamonds business. 
// He decides to buy and sell diamonds. 

// He is given the prices of one diamond for N days by his friend.
// Initially, it is assumed that he has no diamonds.

// You need to help Keerthilal in making the maximum profit that is possible. 
// He can sell a diamond only after he buys a diamond. 

// Note: 
//     - He is allowed to do any number of transactions
//       but, he can buy and sell only one diamond per transaction.

//     - He must complete one transaction before the next transaction.

//     - After each transaction completed, there is a break day.
//     i.e After he sells his diamond, he cannot buy diamond on next day.

// Input Format:
// -------------
// Space separated integers, price of the diamond for N days.

// Output Format:
// --------------
// Print an integer, maximum profit.

// Sample Input-1:
// ---------------
// 7 1 5 3 6 4

// Sample Output-1:
// ----------------
// 5

// Explanation: 
// ------------
// Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.

// Sample Input-2:
// ---------------
// 1 2 3 1 3

// Sample Output-2:
// ----------------
// 3

// Explanation: 
// ------------
// Buy on day 1 (price = 1) and sell on day 2 (price = 2), profit = 2-1 = 1.
// day 3 is a break.
// Buy on day 4 (price = 1) and sell on day 5 (price = 3), profit = 3-1 = 2.
// Total Maximum Profit = 1+2 = 3

import java.util.*;

public class SellDiamonds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] prices = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(findMaxProfit(prices));
        sc.close();
    }

    private static int findMaxProfit(int[] prices) {
        int max = Arrays.stream(prices).max().getAsInt();
        Integer[][][] dp = new Integer[prices.length][2][max + 1];

        return memo(prices, 0, 0, 0, dp);
    }

    private static int memo(int[] prices, int idx, int bought, int prev, Integer[][][] dp) {
        if (idx >= prices.length)
            return 0;

        if (dp[idx][bought][prev] != null)
            return dp[idx][bought][prev];

        int price = prices[idx];

        int buy = Integer.MIN_VALUE, not_buy = Integer.MIN_VALUE;
        if (bought == 0) {
            buy = memo(prices, idx + 1, 1, price, dp);

            not_buy = memo(prices, idx + 1, 0, prev, dp);
        }

        int sell = Integer.MIN_VALUE, not_sell = Integer.MIN_VALUE;
        if (bought == 1) {
            sell = (price - prev) + memo(prices, idx + 2, 0, 0, dp);

            not_sell = memo(prices, idx + 1, 1, prev, dp);
        }

        return dp[idx][bought][prev] = Math.max(Math.max(buy, not_buy), Math.max(sell, not_sell));
    }
}