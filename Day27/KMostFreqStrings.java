package Day27;

// Imagine you’re managing a busy cafe where every order is logged. You have a 
// record—a list of dish names ordered throughout the day—and you want to determine
// which dishes are the most popular. Given an list of strings representing the dish
// names and an integer P, your task is to return the P most frequently ordered dishes.

// The results must be sorted by the number of orders, from the most frequent to 
// the least. If two dishes have been ordered the same number of times, they should
// be listed in alphabetical order.

// Input Format:
// -------------
// Line-1: comma separated line of words, list of words.
// Line-2: An integer P, number of words to display. 

// Output Format:
// --------------
// Print P number of most common used words.

// Example 1:
// ----------
// Input=
// coffee,sandwich,coffee,tea,sandwich,muffin
// 2
// Output=
// [coffee, sandwich]

// Explanation: "coffee" and "sandwich" are the two most frequently ordered items. 
// Although both appear frequently, "coffee" is placed before "sandwich" because 
// it comes earlier alphabetically.

// Example 2:
// ----------
// Input=
// bagel,muffin,scone,bagel,bagel,scone,scone,muffin,muffin
// 3
// Output=
// [bagel, muffin, scone] 

// Explanation: "bagel", "muffin", and "scone" are the three most popular dishes 
// with order frequencies of 3, 3, and 2 respectively. Since "bagel" and "muffin" 
// have the same frequency, they are ordered alphabetically.

// Constraints:

// - 1 ≤ orders.length ≤ 500  
// - 1 ≤ orders[i].length ≤ 10  
// - Each orders[i] consists of lowercase English letters.  
// - P is in the range [1, The number of unique dish names in orders].

import java.util.*;

public class KMostFreqStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(",");
        int k = sc.nextInt();
        sc.close();

        System.out.println(findKWords(words, k));
    }

    private static List<String> findKWords(String[] words, int k) {
        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }

        List<String> sortedWords = trie.getSortedWords();

        return sortedWords.subList(0, k);
    }
}

class Node {
    Node[] children;
    int count;
    String word; 

    Node() {
        children = new Node[26];
        count = 0;
        word = null;
    }
}

class Trie {
    private Node root;
    private Map<String, Integer> freqMap; 

    Trie() {
        root = new Node();
        freqMap = new HashMap<>();
    }

    public void insert(String word) {
        Node node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Node();
            }
            node = node.children[idx];
        }

        node.word = word;
        node.count++;
        freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
    }

    public List<String> getSortedWords() {
        List<String> words = new ArrayList<>(freqMap.keySet());

        words.sort((a, b) -> {
            int freqCompare = freqMap.get(b) - freqMap.get(a);
            return freqCompare == 0 ? a.compareTo(b) : freqCompare;
        });

        return words;
    }
}
