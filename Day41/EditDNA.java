package Day41;

// You are working in a genetics laboratory where you are tasked with correcting
// DNA sequences. Each DNA strand is represented as a sequence of characters
// 'A', 'C', 'G', and 'T'.
// Sometimes, due to mutations or errors during sequencing, the DNA strand
// (originalDNA)
// must be modified to match a targetDNA sequence exactly.

// You can perform the following mutation operations:
// - Insert a nucleotide (A, C, G, or T) into the DNA strand.
// - Delete a nucleotide from the DNA strand.
// - Replace a nucleotide with another one.

// Each operation counts as one step.

// Your task is to find the minimum number of mutation operations needed to
// transform the originalDNA into the targetDNA.

// Input format:
// -------------
// 2 space seperated strings, originalDNA and targetDNA

// Output format:
// --------------
// An integer, the minimum number of mutation operations

// Example 1:
// -----------
// Input:
// ACGT AGT

// Output:
// 1

// Explanation:
// Delete 'C': "ACGT" → "AGT"
// Only 1 mutation is needed.

// Example 2:
// ----------
// Input:
// GATTAC GCATGCU

// Output:
// 4

// Explanation:
// - Replace 'A' with 'C': "GATTAC" → "GCTTAC"
// - Replace 'T' with 'A': "GCTTAC" → "GCATAC"
// - Replace 'A' with 'G': "GCATAC" → "GCATGC"
// - Insert 'U' at the end: "GCATGC" → "GCATGCU"

// Thus, 4 mutations are needed.

import java.util.*;

public class EditDNA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.next();
        String s2 = sc.next();

        System.out.println(minOps(s1, s2));

        sc.close();
    }

    private static int minOps(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return memo(s1, s2, 0, 0, dp);
    }

    private static int memo(String s1, String s2, int i, int j, int[][] dp) {
        if (i == s1.length())
            return s2.length() - j;
        if (j == s2.length())
            return s1.length() - i;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = memo(s1, s2, i + 1, j + 1, dp);
        } else {
            int insert = memo(s1, s2, i + 1, j, dp);
            int delete = memo(s1, s2, i, j + 1, dp);
            int replace = memo(s1, s2, i + 1, j + 1, dp);

            return dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
        }
    }
}