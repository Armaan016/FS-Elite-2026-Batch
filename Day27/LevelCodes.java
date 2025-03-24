package Day27;

// Imagine you're playing a fantasy video game where secret level codes unlock new 

// worlds. These codes are strings made up of letters, and a level code is only 
// considered valid if every shorter code formed by its leading characters has been
// discovered along the journey. In other words, a code is unlockable only when all
// of its prefixes are also present in your collection.

// Given a list of strings representing the level codes you’ve collected, find the 
// longest valid level code such that every prefix of that code is in the list. 
// If there is more than one valid code of the same length, choose the one that 
// comes first in alphabetical order. If no such code exists, return an empty string.

// Input Format
// -------------
// Line1: Space separated codes (strings)

// Output Format
// --------------
// string 

// Example 1:
// ----------
// Input:
// m ma mag magi magic magici magicia magician magicw
// Output: 
// "magician"

// Explanation: The code "magician" is unlockable because every 
// prefix—"m", "ma", "mag", "magi", "magic", "magici", and "magicia"—is present in 
// the list. Although "magicw" appears too, it fails since its prefix "magica" is missing.

// Example 2:
// ----------
// Input:
// a banana app appl ap apply apple
// Output: 
// "apple"  

// Explanation: Both "apple" and "apply" have every prefix in the list, but "apple" 
// comes first in alphabetical order.

// Example 3:
// ----------
// Input: 
// abc bc ab abcd
// Output: 
// ""

import java.util.*;

public class LevelCodes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] words = sc.nextLine().split(" ");

        System.out.print(findLongestValid(words));

        sc.close();
    }

    private static String findLongestValid(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        List<String> res = new ArrayList<>();
        for (String word : words) {
            boolean flag = true;
            for (int i = 1; i < word.length(); i++) {
                String substr = word.substring(0, i);
                // System.out.println(substr + " for word: " + word);
                if (!trie.search(substr)) {
                    flag = false;
                    break;
                }
            }

            if (flag)
                res.add(word);
        }

        Collections.sort(res);
        Collections.sort(res, (a, b) -> b.length() - a.length());
        // System.out.println(res);

        if (res.size() == 0)
            return "";
        return res.get(0);
    }
}

class Node {
    Node[] children;
    boolean isEnd;

    Node() {
        children = new Node[26];
        this.isEnd = false;
    }
}

class Trie {
    Node root;

    Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';

            if (node.children[idx] == null) {
                node.children[idx] = new Node();
            }

            node = node.children[idx];
        }

        node.isEnd = true;
    }

    public boolean search(String word) {
        Node node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';

            if (node.children[idx] == null)
                return false;
            node = node.children[idx];
        }

        return node.isEnd == true;
    }
}