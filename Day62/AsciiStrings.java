package Day62;

// Preethi is playing with strings. She is given 2 strings. 
// Preethi can remove any number of characters from 2 strings such that 2 strings 
// to be equal after removal (Sequence of characters shouldn't change).
// Find the smallest ASCII sum possible for the removed characters.

// Input Format:
// -------------
// Line-1: Two space seperated strings

// Output Format:
// --------------
// return an integer , represents ASCII sum with the given constraints.

// Sample Input-1:
// ---------------
// treat create

// Sample Output-1:
// ----------------
// 316

// Explanation:
// -------------
// Remove 't' in string1 and 'c' and 'e' in string 2. so sum= 116+99+101=316

// Sample Input-2:
// ---------------
// interest pintrest

// Sample Output-2:
// ----------------
// 213

// Explanation:
// -------------
// Remove 'e' in string1 and 'p' in string2. so sum=101+112=213

import java.util.*;

public class AsciiStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.next();
        String s2 = sc.next();

        System.out.println(minAsciiSum(s1, s2));
        sc.close();
    }

    private static int minAsciiSum(String s1, String s2) {
        Integer[][] dp = new Integer[s1.length()][s2.length()];

        return memo(s1, s2, 0, 0, dp);
    }

    private static int memo(String s1, String s2, int i, int j, Integer[][] dp) {
        if (i == s1.length()) {
            int sum = 0;
            while (j < s2.length()) {
                sum += (int) (s2.charAt(j++));
            }

            return sum;
        }

        if (j == s2.length()) {
            int sum = 0;
            while (i < s1.length()) {
                sum += (int) (s1.charAt(i++));
            }

            return sum;
        }

        if (dp[i][j] != null)
            return dp[i][j];

        int delete = Integer.MAX_VALUE, not_delete = Integer.MAX_VALUE;
        if (s1.charAt(i) != s2.charAt(j)) {
            delete = Math.min((int) (s1.charAt(i)) + memo(s1, s2, i + 1, j, dp),
                    (int) (s2.charAt(j)) + memo(s1, s2, i, j + 1, dp));
        } else {
            not_delete = memo(s1, s2, i + 1, j + 1, dp);
        }

        return Math.min(delete, not_delete);
    }
}