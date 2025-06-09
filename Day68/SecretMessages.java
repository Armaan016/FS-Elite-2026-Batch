package Day68;

// Alex and his twin brother Jordan often create secret messages. One day, Jordan 
// gives Alex two encrypted messages and challenges him to find the longest common 
// palindromic pattern hidden within both messages.

// Alex wants your help to decode the longest common palindromic subsequence that 
// exists in both strings.

// Your task is to determine the length of the longest subsequence that:
// - Appears in both messages
// - Is a palindrome

// Input Format:
// -------------
// input1: A string representing the first encrypted message.
// input2: A string representing the second encrypted message.

// Output Fromat:
// --------------
// Return an integer representing the length of the longest common palindromic 
// subsequence shared by both messages.

// Sample Input: 
// -------------
// adfa
// aagh

// Sample Output:
// --------------
// 2

// Sample Input-2:
// ---------------
// abcda
// fxaaba

// Sample Output:
// --------------
// 3

// Explanation:
// ------------
// The longest palindromic subsequence common to both is "aba" with length 3.

import java.util.*;

public class SecretMessages {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        System.out.println(longestCommonPal(s1, s2));
        sc.close();
    }

    private static int longestCommonPal(String s1, String s2) {
        // Integer[][] dp = new Integer[s1.length()][s2.length()];
        Map<String, String> dp = new HashMap<>();
        String lcs = memo(s1, s2, 0, 0, "", dp);

        int len = 0;
        for (int k = 0; k < lcs.length(); k++) {
            for (int l = k + 1; l <= lcs.length(); l++) {
                String sub = lcs.substring(k, l);
                // System.out.println("checking: " + sub);
                if (isPal(sub))
                    len = Math.max(len, l - k);
            }
        }

        return len;
    }

    // private static int ans;
    private static String memo(String s1, String s2, int i, int j, String curr, Map<String, String> dp) {
        if (i == s1.length() || j == s2.length()) {
            // System.out.println("curr: " + curr);
            // int len = 0;

            // return len;
            return curr;
        }

        // if(isPal(curr)) ans = Math.max(ans, curr.length());

        String key = i + "," + j + "," + curr;

        // if(i == s1.length()) return memo(s1, s2, i, j + 1, curr, dp);
        // if(j == s2.length()) return memo(s1, s2, i + 1, j, curr, dp);

        // if(dp[i][j] != null) return dp[i][j];
        if (dp.containsKey(key))
            return dp.get(key);

        String res = "";
        if (s1.charAt(i) == s2.charAt(j)) {
            // System.out.println("equal at: i: " + i + " j: " + j + " curr: " + curr +
            // s1.charAt(i));
            res = memo(s1, s2, i + 1, j + 1, curr + s1.charAt(i), dp);
        } else {
            String a = memo(s1, s2, i + 1, j, curr, dp);
            String b = memo(s1, s2, i, j + 1, curr, dp);

            res = (a.length() > b.length()) ? a : b;
        }

        // return dp[i][j] = res;
        dp.put(key, res);
        return res;
    }

    private static boolean isPal(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }

        return true;
    }
}