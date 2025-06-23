package GrandTest3;

// Given a string s containing only digits, return the number of ways of making the value 0 by putting either + or - between any two digits. 
// Sample Test Case:
// Input: "454522" 
// Output: 4

import java.util.*;

public class NumberOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        System.out.println(countWays(s));
        sc.close();
    }

    private static int countWays(String s) {
        return memo(s, 1, s.charAt(0) - '0', new HashMap<>());
    }

    private static int memo(String s, int idx, int val, Map<String, Integer> dp) {
        if (idx == s.length() && val == 0)
            return 1; 
        if (idx == s.length())
            return 0;

        String key = idx + "," + val;
        if (dp.containsKey(key))
            return dp.get(key);

        int i = s.charAt(idx) - '0';
        int plus = memo(s, idx + 1, val + i, dp);
        int minus = memo(s, idx + 1, val - i, dp);

        int res = plus + minus;

        dp.put(key, res);
        return res;
    }
}
