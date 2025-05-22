// Mr Robert is working with strings.
// He selected two strings S1 and S2, may differ in length,
// consists of lowercase alphabets only.

// Mr Robert has to update the strings S1, S2 to meet any one of the criteria:
// - All the alphabets in S1 should be less than all the alphabets in S2.
// - All the alphabets in S2 should be less than all the alphabets in S1.
// - Both S1 and S2 should have only one distinct alphabet in it.
// To Achieve, one of the criteria, you are allowed to replace any one letter in
// the string with any other lowercase alphabet.

// Your task is to help Mr Robert, to find the minimum replacements to be done
// to
// update the strings S1 and S2 to meet one of the criteria mentioned above.

// Input Format:
// -------------
// Two space separated strings S1 and S2.

// Output Format:
// --------------
// Print an integer, minimum number of replacements.

// Sample Input-1:
// ---------------
// apple ball

// Sample Output-1:
// ----------------
// 3

// Explanation:
// ------------
// Consider the best way to make the criteria true:
// - Update S2 to "baaa" in 2 replacements, and Update S1 to "cpple" in 1
// replacement
// then every alphabet in S2 is less than every alphabet in S1.
// (OR)
// - Update S1 to "ppppp" in 3 replacements, then every alphabet in S2 is less
// than every alphabet in S1.

// Sample Input-2:
// ---------------
// kmit kmec

// Sample Output-2:
// ----------------
// 2

import java.util.*;

public class StringCriteria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.next();
        String s2 = sc.next();

        System.out.println(findMinReplacements(s1, s2));
        sc.close();
    }

    private static int findMinReplacements(String s1, String s2) {
        int[] freq1 = makeFreq(s1);
        int[] freq2 = makeFreq(s2);

        int minReplace1 = countLessThan(freq1, freq2);
        int minReplace2 = countLessThan(freq2, freq1);

        int minReplace3 = countDiff(freq1, s1.length()) +
                countDiff(freq2, s2.length());

        return Math.min(minReplace1, Math.min(minReplace2, minReplace3));
    }

    private static int countDiff(int[] freq, int length) {
        int minChanges = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            minChanges = Math.min(minChanges, length - freq[i]);
        }

        return minChanges;
    }

    private static int countLessThan(int[] freq1, int[] freq2) {
        int minReplacements = Integer.MAX_VALUE;

        for (int cut = 1; cut < 26; cut++) {
            int changes1 = 0, changes2 = 0;

            for (int i = 0; i < cut; i++)
                changes1 += freq2[i];
            for (int i = cut; i < 26; i++)
                changes2 += freq1[i];

            minReplacements = Math.min(minReplacements, changes1 + changes2);
        }

        return minReplacements;
    }

    private static int[] makeFreq(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        return freq;
    }
}