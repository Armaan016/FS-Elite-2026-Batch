package Day39;

import java.util.*;

public class ShortestCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] words = sc.nextLine().split(" ");

        System.out.println(getCipher(words));
    }

    private static String getCipher(String[] words) {
        Trie trie = new Trie();

        int ans = 0;
        for (String word : words) {
            if (!trie.search(word)) {
                String rev = reverse(word);
                trie.insert(rev);

                ans += (rev.length() + 1);
            }
        }

        return ans;
    }

    private static String reverse(String s) {
        char[] c = s.toCharArray();
        int l = 0, r = s.length() - 1;

        while (l < r) {
            char t = c[l];
            c[l] = c[r];
            c[r] = temp;
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
