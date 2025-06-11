package Day70;

// Pramod is planning to design a program, which helps to create
// the IP addresses posssible from a given string S,
// where each IP address should be valid.

// It is guaranteed that S contains only digits.

// Can you help pramod in designing such program, which returns all possible
// IP addresses. Print the answer in lexicographic order.

// NOTE:
// -----

// - A valid IP address consists of exactly four integers,
// each integer is between 0 and 255, separated by single dots
// and cannot have leading zeros
// - IP Addresses are said to be valid if it falls in the range
// from 0.0.0.0 to 255.255.255.255

// - IP addresses like [123.012.234.255 , 123.234.345.34] are invalid.

// Input Format:
// -------------
// A string S, contains only digits [0-9].

// Output Format:
// --------------
// Print all possible IP addresses which are valid.

// Sample Input-1:
// ---------------
// 23323311123

// Sample Output-1:
// ----------------
// [233.233.11.123, 233.233.111.23]

// Sample Input-2:
// ---------------
// 12345678

// Sample Output-2:
// ----------------
// [1.234.56.78, 12.34.56.78, 123.4.56.78, 123.45.6.78, 123.45.67.8]

// Sample Input-3:
// ---------------
// 02550255

// Sample Output-3:
// ----------------
// [0.25.50.255, 0.255.0.255]

import java.util.*;

public class ValidIP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String ip = sc.nextLine();

        res = new ArrayList<>();
        getValidIPs(ip);

        System.out.println(res);
        sc.close();
    }

    private static List<String> res;

    private static void getValidIPs(String s) {
        backtrack(s, 0, new StringBuilder(), 0);
    }

    private static void backtrack(String s, int idx, StringBuilder curr, int parts) {
        if (idx >= s.length()) {
            if (parts == 4)
                res.add(curr.toString().substring(0, curr.length() - 1));
            return;
        }

        for (int i = idx; i <= s.length(); i++) {
            String sub = s.substring(idx, i);

            if (isValid(sub)) {
                int l = curr.length();
                // System.out.println("appending: " + sub);
                curr.append(sub).append(".");

                backtrack(s, i, curr, parts + 1);

                curr.setLength(l);
            }
        }
    }

    private static boolean isValid(String ip) {
        if (ip.length() > 3 || ip.length() == 0)
            return false;
        if (ip.length() >= 2 && ip.charAt(0) == '0')
            return false;

        int s = Integer.parseInt(ip);
        return s >= 0 && s <= 255;
    }
}