package Day44;

// Archaeologists discovered an ancient manuscript represented by a string 'text', 
// where each character represents an ancient symbol. They suspect the manuscript 
// might contain repeated symbol patterns (substrings).

// Your task is to analyze the text and determine the length of the longest 
// repeating symbol pattern. If the text contains no repeating patterns, return '0'.

// Example:
// --------
// Input=
// scarabankhscarab

// Output=
// 6

// Explanation: The longest repeating symbol pattern is "scarab", appearing twice.

//  Constraints:
// - 1 <= text.length <= 2000
// - 'text' consists of lowercase English letters ('a' - 'z').

import java.util.*;

public class AncientManuscript {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        System.out.println(LRS(s));

        sc.close();
    }

    private static int LRS(String s) {
        int n = s.length();

        int maxLen = 0;
        for (int i = 1; i < n; i++) {
            Set<String> set = new HashSet<>();

            for (int j = 0; j + i <= n; j++) {
                String sub = s.substring(j, j + i);

                if (set.contains(sub))
                    maxLen = i;
                set.add(sub);
            }
        }

        return maxLen;
    }
}