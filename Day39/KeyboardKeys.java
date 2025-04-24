package Day39;

// Amogh is an Antiquarian, The person who collects antiques.
// He found a rear keyboard which has following keys,
// Keys are 'N', 'S', 'C' and 'P'

// 1st Key - 'N': Print one character 'N' on Console.
// 2nd Key - 'S': Select the whole Console.
// 3rd Key - 'C': Copy selected content to buffer.
// 4th Key - 'P': Print the buffer on Console, and append it after what has 
// already been printed.

// Now, your task is to find out maximum numbers of 'N's you can print
// after K keystrokes . 

// Input Format:
// -------------
// An integer K

// Output Format:
// --------------
// Print an integer, maximum numbers of 'N's you can print.

// Sample Input-1:
// -------------------
// 3

// Sample Output-1:
// -------------------- 
// 3

// Explanation: 
// ---------------
// We can print at most get 3 N's on console by pressing following key sequence:
// N, N, N

// Sample Input-2:
// -------------------
// 7

// Sample Output-2:
// ---------------------
// 9

// Explanation: 
// ---------------
// We can print at most get 9 N's on console by pressing following key sequence:
// N, N, N, S, C, P, P

import java.util.*;

public class KeyboardKeys {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(findMaxN(n));

        sc.close();
    }

    private static long findMaxN(int n) {
        // Map<Key, Long> map = new HashMap<>();
        // long[][][] dp = new long[n + 1][1001][1001];
        // for(long[][] a : dp) {
        // for(long[] b : a) Arrays.fill(b, -1);
        // }

        // return memo(n, 0, 0, 0, map);
        return helper(n, new HashMap<>());
    }

    // private static long memo(int n, int i, int console, int buffer, Map<Key,
    // Long> dp) {
    // if (i == n) {
    // // System.out.println("console: " + console);
    // return console;
    // }

    // Key key = new Key(i, console, buffer);
    // if (dp.containsKey(key))
    // return dp.get(key);
    // // if(dp[i][console][buffer] != -1) return dp[i][console][buffer];

    // long n_ = memo(n, i + 1, console + 1, buffer, dp);

    // long s_c = 0;
    // if (i + 2 <= n)
    // s_c = memo(n, i + 2, console, console, dp);

    // long p = 0;
    // if (buffer > 0)
    // p = memo(n, i + 1, console + buffer, buffer, dp);

    // long res = Math.max(n_, Math.max(s_c, p));

    // dp.put(key, res);
    // return res;
    // }

    private static int helper(int n, Map<Integer, Integer> mp) {
        if (n <= 0)
            return 0;
        if (n <= 6)
            return n;

        if (mp.containsKey(n))
            return mp.get(n);

        int res = 0;
        for (int i = n - 3; i >= 0; i--) {
            int curr = helper(i, mp) * (n - i - 1);
            res = Math.max(res, curr);
        }

        mp.put(n, res);
        return res;
    }
}

class Key {
    int i;
    int console;
    int buffer;

    Key(int i, int console, int buffer) {
        this.i = i;
        this.console = console;
        this.buffer = buffer;
    }
}
