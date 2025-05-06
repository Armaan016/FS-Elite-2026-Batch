package GrandTest2;

// A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

// For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
// Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

// Example 1:

// Input: s = "25525511135"
// Output: ["255.255.11.135","255.255.111.35"]

import java.util.*;

public class RestoreIPAddresses {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        System.out.println(restoreIpAddresses(s));
        sc.close();
    }

    private static List<String> res;

    private static List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();

        backtrack(s, 0, 0, new StringBuilder());

        return res;
    }

    private static void backtrack(String s, int idx, int parts, StringBuilder sb) {
        if (idx == s.length()) {
            if (parts == 4)
                res.add(sb.deleteCharAt(sb.length() - 1).toString());
            return;
        }

        for (int i = idx + 1; i <= s.length(); i++) {
            String part = s.substring(idx, i);
            if (isValid(part)) {
                int l = sb.length();
                sb.append(part).append('.');
                backtrack(s, i, parts + 1, sb);
                sb.setLength(l);
            }
        }
    }

    private static boolean isValid(String s) {
        if (s.length() > 3)
            return false;
        if (s.length() > 1 && s.charAt(0) == '0')
            return false;
        int num = Integer.parseInt(s);
        return num >= 0 && num <= 255;
    }
}
