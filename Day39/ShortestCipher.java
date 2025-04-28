package Day39;

// Mr Suresh is working with the plain text P, a list of words w[], 
// He is converting P into C [the cipher text], C is valid cipher of P, 
// if the following rules are followed:
// 	- The cipher-text C is a string ends with '$' character.
// 	- Every word, w[i] in w[], should be a substring of C, and 
// 	the substring should have $ at the end of it.

// Your task is to help Mr Suresh to find the shortest Cipher C,  
// and return its length.

// Input Format:
// -------------
// Single line of space separated words, w[].

// Output Format:
// --------------
// Print an integer result, the length of the shortest cipher.

// Sample Input-1: 
// ---------------
// kmit it ngit

// Sample Output-1:
// ----------------
// 10

// Explanation:
// ------------
// A valid cipher C is "kmit$ngit$".
// w[0] = "kmit", the substring of C, and the '$' is the end character after "kmit"
// w[1] = "it", the substring of C, and the '$' is the end character after "it"
// w[2] = "ngit", the substring of C, and the '$' is the end character after "ngit"

// Sample Input-2:
// ---------------
// ace

// Sample Output-2:
// ----------------
// 4

// Explanation:
// ------------
// A valid cipher C is "ace$".
// w[0] = "ace", the substring of C, and the '$' is the end character after "ace"

import java.util.*;

public class ShortestCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] words = sc.nextLine().split(" ");

        System.out.println(getCipher(words));
        
        sc.close(); 
    }

    private static int getCipher(String[] words) {
        Trie trie = new Trie();

        Arrays.sort(words, (a, b) -> b.length() - a.length());
        StringBuilder res = new StringBuilder();
        for (String word : words) {
            String rev = reverse(word);
            if (!trie.search(rev)) {
                trie.insert(rev);

                res.append(word).append("$");
            }
        }

        return res.length();
    }

    private static String reverse(String s) {
        char[] c = s.toCharArray();
        int l = 0, r = s.length() - 1;

        while (l < r) {
            char t = c[l];
            c[l] = c[r];
            c[r] = t;
            l++;
            r--;
        }

        return new String(c);
    }
}

class Node {
    Node[] children;
    boolean isEnd;

    Node() {
        children = new Node[26];
        isEnd = false;
    }
}

class Trie {
    Node root;

    Trie() {
        root = new Node();
    }

    public void insert(String s) {
        Node node = root;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new Node();
            node = node.children[idx];
        }

        node.isEnd = true;
    }

    public boolean search(String s) {
        Node node = root;

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                return false;
            node = node.children[idx];
        }

        return true;
    }
}