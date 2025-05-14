// package Day51 (Online);

// You are given two strings 'Org' and 'Sub' where Sub is a subsequence of Org. 
// You aer given a list of R indices[] (Unique indices), and you need to delete 
// P letters from the given string 'Org', with the following conditions:
//     - You need to delete the first P letters from strin 'Org'in the given order
//       of indices[] only.
//     - After deleting P letters, the string 'Sub' should be subsequence of 'Org'.
//       Where, 0 <= i < P and P < R.

// Your task is to maximixe the value of P such that 'Sub' is still a subsequence 
// of 'Org' after the deletion of letters, and return P.

// Input Format:
// -------------
// Line-1: Two space seperated strings, Original and Sub
// Line-2: An integer, R, number of indices.
// Line-3: R space separated integers, indices[].

// Output Format:
// --------------
// Print an integer, the maximum value of P.

// Sample Input-1:
// ---------------
// pqrprq pr
// 3
// 3 1 0

// Sample Output-1:
// ----------------
// 2

// Explanation:
// ------------
// After deleting 2 letters at indices 3 and 1, "pqrprq" becomes "prrq".
// "pq" is a subsequence of "prrq".
// If you delete 3 letters at indices 3, 1, and 0, "pqrprq" becomes "rrq", 
// and "pq" is not a subsequence of "rrq".
// Hence, the maximum P is 2.

// Sample Input-2:
// ---------------
// pqrqssss pqrs
// 6
// 3 2 1 4 5 6

// Sample Output-2:
// ----------------
// 1

// Explanation: 
// ------------
// After deleting 1 letter at index 3, "pqrqssss" becomes "pqrssss".
// "pqrs" is a subsequence of "pqrssss".

import java.util.*;

public class UniqueIndices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line[] = sc.nextLine().split(" ");
        String orig = line[0];
        String sub = line[1];

        int r = sc.nextInt();

        int[] indices = new int[r];
        for (int i = 0; i < r; i++)
            indices[i] = sc.nextInt();

        System.out.println(findMaxP(orig, sub, indices));
        sc.close();
    }

    private static int findMaxP(String orig, String sub, int[] indices) {
        int count = 0;
        Set<Integer> toSkip = new HashSet<>();
        for (int index : indices) {
            toSkip.add(index);
            if (!isSubsequence(orig, sub, toSkip, 0, 0))
                return count;
            // System.out.println("Valid for index: " + index);
            count++;
        }

        return count;
    }

    private static boolean isSubsequence(String s1, String s2, Set<Integer> set, int i, int j) {
        if (j == s2.length())
            return true;
        if (i == s1.length())
            return false;

        if (set.contains(i))
            return isSubsequence(s1, s2, set, i + 1, j);

        if (s1.charAt(i) == s2.charAt(j))
            return isSubsequence(s1, s2, set, i + 1, j + 1);
        else
            return isSubsequence(s1, s2, set, i + 1, j);
    }
}