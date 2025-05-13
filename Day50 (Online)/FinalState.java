package Day50;

// Umesh is a Mathematician,
// He is given a task to his student Shanker, 
// There are N coins in a row, indexed from 0 to N-1, intially all the coins are 
// facing "tail". And Umesh has given him a final State-to-Achieve.

// Shanker can achieve the final state by doing a swap operation as follows:
//     - Shanker can choose any index i, 
//     - all the coins has to be swap their faces, from "head" to "tail" 
//     or "tail to "head" from index 'i' to 'N-1'.

// Shanker is given a binary string S, State-to-Achieve contains [0,1] only. 
// "tail" indicates with '0' and "head" indicates with '1'

// Please help Shanker to find the minimum number of swap operations required 
// to reach State-to-Achieve.

// Input Format:
// -------------
// A String S, final State-to-Achieve.

// Output Format:
// --------------
// Print an integer, minimum number of swap operations.

// Sample Input-1:
// ---------------
// 10111010

// Sample Output-1:
// ----------------
// 6

// Explanation: 
// ------------
// Initial configuration "00000000".
// swap from the first coin: "00000000" -> "11111111"
// swap from the second coin: "11111111" -> "10000000"
// swap from the third coin: "10000000" -> "10111111"
// swap from the sixth coin: "10111111" -> "10111000"
// swap from the seventh coin: "10111000" -> "10111011"
// swap from the eighth coin: "10111011" -> "10111010"
// A total of 6 swap operations required.

// Sample Input-2:
// ---------------
// 11111

// Sample Output-2:
// ----------------
// 1

import java.util.*;

public class FinalState {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        System.out.println(countSwaps(s));

        sc.close();
    }

    private static int countSwaps(String f) {
        // String s = "0".repeat(f.length());
        // Map<String, Integer> dp = new HashMap<>();
        // return memo(s, 0, f, 0, dp);

        int flips = 0;
        for (int i = 0; i < f.length(); i++) {
            int exp = (f.charAt(i) - '0');
            int curr = flips % 2;

            if (curr != exp)
                flips++;
        }

        return flips;
    }

    // private static int memo(String s, int idx, String f, int count, Map<String,
    // Integer> dp) {
    // if(s.equals(f)) return count;
    // if(idx == s.length()) return Integer.MAX_VALUE;

    // String key = s + "," + idx;
    // if(dp.containsKey(key)) return dp.get(key);

    // // System.out.println("idx: " + idx + " s: " + s);
    // int take = memo(swap(s.toCharArray(), idx), idx + 1, f, count + 1, dp);
    // int skip = memo(s, idx + 1, f, count, dp);

    // int res = Math.min(take, skip);
    // dp.put(key, res);

    // return res;
    // }

    // private static String swap(char[] c, int idx) {
    // for(int i = idx; i < c.length; i++) {
    // c[i] ^= 1;
    // }

    // return new String(c);
    // }
}