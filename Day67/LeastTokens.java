package Day67;

// You are given some tokens printed with unique numbers on it and an integer T.
// Your task is to find the least number of tokens that you need to make up the
// value T, by adding the numbers printed on all the tokens.
// If you cannot make the value T, by any combination of the tokens, return -1.

// NOTE: Assume that you have unlimited set of tokens of each number type.

// Input Format:
// -------------
// Line-1: Space separated integers tokens[], number printed on tokens.
// Line-2: An integer T.

// Output Format:
// --------------
// Print an integer, minimum number of tokens to make the value T.

// Sample Input-1:
// ---------------
// 1 2 5
// 11

// Sample Output-1:
// ----------------
// 3

// Explanation:
// ------------
// 5+5+1 = 11

// Sample Input-2:
// ---------------
// 2 4
// 15

// Sample Output-2:
// ----------------
// -1

import java.util.*;

public class LeastTokens {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tokens = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int t = sc.nextInt();

        System.out.println(countLeastTokens(tokens, t));
        sc.close();
    }

    private static int countLeastTokens(int[] tokens, int t) {
        Integer[] dp = new Integer[t + 1];

        return memo(tokens, t, dp);
    }

    private static int memo(int[] tokens, int t, Integer[] dp) {
        if (t <= 0)
            return 0;

        if (dp[t] != null)
            return dp[t];

        int res = Integer.MAX_VALUE;
        for (int token : tokens) {
            res = Math.min(res, 1 + memo(tokens, t - token, dp));
        }

        return dp[t] = res;
    }
}