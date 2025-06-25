package Day78;

// Basava is interested in playing with digits.
// He wants to create a set of integers of length N, using the digits[0-9].
// The rules to create the integers are as follows:
// 	- digits in each integer are like d0,d1,d2...dn-1
// 	- and |d0-d1| = |d1-d2| = |d2-d3| ... |dn-2 - dn-1|= D

// Basava is given two integers N and D, where N is length of the integer and 
// D is the difference. Can you help Basava, to create such list of integers 
// and print all the possible integers in ascending order

// Note:
// -----
// Integers with leading 0's are not allowed

// Input Format:
// -------------
// Two space separated integers N and K.

// Output Format:
// --------------
// Print all the possible integers in ascending order.

// Sample Input-1:
// ---------------
// 3 5

// Sample Output-1:
// ----------------
// [161, 272, 383, 494, 505, 616, 727, 838, 949]

// Sample Input-2:
// ---------------
// 2 3

// Sample Output-2:
// ----------------
// [14, 25, 30, 36, 41, 47, 52, 58, 63, 69, 74, 85, 96]

import java.util.*;

public class ValidIntegers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(getValidInts(n, k));
        sc.close();
    }

    private static List<String> res;

    private static List<String> getValidInts(int n, int k) {
        res = new ArrayList<>();

        for (char i = '1'; i <= '9'; i++) {
            backtrack(n, k, String.valueOf(i));
        }

        return res;
    }

    private static void backtrack(int n, int k, String curr) {
        if (curr.length() == n) {
            res.add(curr);
            return;
        }

        int prev = curr.charAt(curr.length() - 1) - '0';
        // System.out.println("prev: " + prev + " curr: " + curr);

        if (prev - k >= 0) {
            // System.out.println("Appending: " + String.valueOf(prev - k));
            backtrack(n, k, curr + String.valueOf(prev - k));
        }

        if (k != 0 && prev + k < 10) {
            // System.out.println("Appending: " + String.valueOf(prev + k));
            backtrack(n, k, curr + String.valueOf(prev + k));
        }
    }
}