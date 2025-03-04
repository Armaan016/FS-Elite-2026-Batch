package Day16;
// You are a database integrity engineer working for a global cloud company. 

// Your team maintains a distributed database network, where each server either:
//     - Stores equivalent data to another server (serverX == serverY).
//     - Stores different data from another server (serverX != serverY).

// The transitive consistency rule must be followed:
//     - If A == B and B == C, then A == C must be true.
//     - If A == B and B != C, then A != C must be true.

// Your task is to analyze the given constraints and determine whether they 
// follow transitive consistency. If all relations are consistent, return true; 
// otherwise, return false

// Input Format:
// -------------
// Space separated strnigs, list of relations

// Output Format:
// --------------
// Print a boolean value, whether transitive law is obeyed or not.

// Sample Input-1:
// ---------------
// a==b c==d c!=e e==f

// Sample Output-1:
// ----------------
// true

// Sample Input-2:
// ---------------
// a==b b!=c c==a

// Sample Output-2:
// ----------------
// false

// Explanation:
// ------------
// {a, b} form one equivalence group.
// {c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
// However, b != c contradicts b == a and c == a.

// Sample Input-3:
// ---------------
// a==b b==c c!=d d!=e f==g g!=d

// Sample Output-3:
// ----------------
// true

import java.util.*;

public class TransitivityLaw {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] line = sc.nextLine().split(" ");

        List<char[]> eq = new ArrayList<>();
        List<char[]> neq = new ArrayList<>();
        for (String s : line) {
            char a = s.charAt(0);
            char b = s.charAt(3);
            if (s.charAt(1) == '!')
                neq.add(new char[] { a, b });
            else
                eq.add(new char[] { a, b });
        }

        System.out.println(checkTransitivity(eq, neq));

        sc.close();
    }

    private static char[] parent;

    private static boolean checkTransitivity(List<char[]> eq, List<char[]> neq) {
        parent = new char[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = (char) (i + 'a');
        }

        for (char[] e : eq) {
            char a = e[0];
            char b = e[1];

            union(a, b);
        }

        for (char[] ne : neq) {
            char a = ne[0];
            char b = ne[1];

            char rootA = find(a);
            char rootB = find(b);

            // System.out.printf("a: %c, rootA: %c, b: %c, rootB: %c\n", a, rootA, b,
            // rootB);
            if (rootA == rootB)
                return false;
        }

        return true;
    }

    private static char find(char c) {
        int idx = c - 'a';
        if (parent[idx] != c) {
            parent[idx] = find(parent[idx]);
        }

        return parent[idx];
    }

    private static void union(char a, char b) {
        char rootA = find(a);
        char rootB = find(b);

        if (rootA == rootB)
            return;

        if (rootA < rootB)
            parent[rootB - 'a'] = rootA;
        else
            parent[rootA - 'a'] = rootB;
    }
}