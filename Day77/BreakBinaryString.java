package Day77;

// Bunny is playing with binary strings. He wants to break
// a binary string B into 3 parts, of length atleast '1',
// when we merge the 3 parts will result the string B.

// Your task is to find the count the various forms to break 
// the Binary String B into 3 parts, where each part should 
// contain equal number of 1's.

// Input Format:
// -------------
// A string S.

// Output Format:
// --------------
// Print an integer, count the various forms to break.

// Sample Input-1:
// ---------------
// 01010010

// Sample Output-1:
// ----------------
// 6

// Explanation:
// ------------
// There are six forms to break S into 3 parts 
// where each part contain the equal number of  1's.
// 01 | 01 | 0010
// 01 | 010 | 010
// 01 | 0100 | 10
// 010 | 1 | 0010
// 010 | 10 | 010
// 010 | 100 | 10

// Sample Input-2:
// ---------------
// 010010

// Sample Output-2:
// ----------------
// 0

import java.util.*;

public class BreakBinaryString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        System.out.println(countWays(s));
        sc.close();
    }

    private static int countWays(String s) {
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1')
                ones++;
        }

        if (ones % 3 != 0)
            return 0;

        int k = ones / 3;

        // Integer[][][] dp = new Integer[s.length()][k + 1][4];
        // return memo(0, s, 0, k, 0, dp);
        return backtrack(s, k, 0);
    }

    private static int backtrack(String s, int k, int parts) {
        if (s.length() == 0)
            return parts == 3 ? 1 : 0;

        int res = 0;
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);

            int count = countOnes(sub);
            if (count == k) {
                res += backtrack(s.substring(i), k, parts + 1);
            } else if (count > k) {
                break;
            }
        }

        return res;
    }

    private static int countOnes(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '1')
                cnt++;
        }

        return cnt;
    }

    // private static int memo(int idx, String s, int ones, int k, int parts,
    // Integer[][][] dp) {
    // if(idx == s.length() && parts == 3 && ones == 0) return 1;
    // if(idx == s.length()) return 0;

    // if(parts > 3) return 0;
    // if(ones > k) return 0;

    // if(dp[idx][ones][parts] != null) return dp[idx][ones][parts];

    // int res = 0;
    // if(s.charAt(idx) == '1') {
    // if(ones + 1 == k) {
    // res = memo(idx + 1, s, 0, k, parts + 1, dp);
    // }

    // res += memo(idx + 1, s, ones + 1, k, parts, dp);
    // } else {
    // if(ones == k) res = memo(idx + 1, s, 0, k, parts + 1, dp);

    // res += memo(idx + 1, s, ones, k, parts, dp);
    // }

    // return dp[idx][ones][parts] = res;
    // }
}