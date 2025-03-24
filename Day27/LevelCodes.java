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
        Arrays.sort(words); 
        Trie trie = new Trie();

        String longest = "";
        for (String word : words) {
            if (trie.insert(word)) { 
                if (word.length() > longest.length()) {
                    longest = word;
                }
            }
        }

        return longest;
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
    Set<String> validPrefixes; 

    Trie() {
        root = new Node();
        validPrefixes = new HashSet<>();
    }

    public boolean insert(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Node();
            }
            node = node.children[idx];
        }

        node.isEnd = true;

        for (int i = 1; i < word.length(); i++) {
            if (!validPrefixes.contains(word.substring(0, i))) {
                return false;
            }
        }

        validPrefixes.add(word); 
        return true;
    }
}
