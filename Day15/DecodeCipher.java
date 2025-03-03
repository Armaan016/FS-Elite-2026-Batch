// In the world of secret codes and cryptography, you are entrusted with
// deciphering a hidden message.
// You have two encoded keys, key1 and key2, both of equal length. Each
// character
// in key1 is paired with the corresponding character in key2.

// This relationship follows the standard rules of an equivalence cipher:
// • Self-Mapping: Every character inherently maps to itself.
// • Mutual Mapping: If a character from key1 maps to one in key2, then that
// character in key2 maps back to the one in key1.
// • Chain Mapping: If character A maps to B, and B maps to C, then A, B, and C
// are all interchangeable in this cipher.

// Using this mapping, you must decode a cipherText, by replacing every
// character
// with the smallest equivalent character from its equivalence group.
// The result should be the relatively smallest possible decoded message.

// Input Format:
// -------------
// Three space separated strings, key1 , key2 and cipherText

// Output Format:
// --------------
// Print a string, decoded message which is relatively smallest string of
// cipherText.

// Example 1:
// input=
// attitude progress apriori
// output=
// aaogoog

// Explanation: The mapping pairs form groups: [a, p], [o, r, t], [g, i], [e,
// u],
// [d, e, s]. By substituting each character in cipherText with the smallest
// from
// its group, you decode the message to "aaogoog".

// Constraints:
// • 1 <= key1.length, key2.length, cipherText.length <= 1000
// • key1.length == key2.length
// • key1, key2, and cipherText consist solely of lowercase English letters.

import java.util.*;

public class DecodeCipher {
    private static char[] parent;
    // private static int[] rank;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] line = sc.nextLine().split(" ");

        String key1 = line[0];
        String key2 = line[1];
        String cipher = line[2];

        System.out.println(decode(key1, key2, cipher));

        sc.close();
    }

    private static char find(char c) {
        // System.out.println("char c: " + c + " parent[c]: " + parent[c - 'a']);
        int idx = c - 'a';
        if (parent[idx] != c)
            parent[idx] = find(parent[idx]);
        return parent[idx];
    }

    private static void union(char c1, char c2) {
        char root1 = find(c1);
        char root2 = find(c2);

        if (root1 == root2)
            return;

        if (root1 < root2) {
            parent[root2 - 'a'] = root1;
        } else {
            parent[root1 - 'a'] = root2;
        }
    }

    private static String decode(String s1, String s2, String cipher) {
        parent = new char[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = (char) ('a' + i);
            // rank[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < s1.length(); i++) {
            char a = s1.charAt(i);
            char b = s2.charAt(i);

            union(a, b);
        }

        StringBuilder sb = new StringBuilder();
        for (char c : cipher.toCharArray()) {
            sb.append(find(c));
        }

        return sb.toString();
    }
}