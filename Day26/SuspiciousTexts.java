// Imagine you're a digital security analyst reviewing a suspicious email. The email’s 
// content is a continuous string of characters, and you have a list of keywords 
// commonly used in phishing scams. Your mission is to scan the email text and flag 
// every segment that exactly matches one of these keywords. In other words, identify 
// all index pairs [i, j] such that the substring from position i to j in the email 
// text is one of the suspicious keywords. Return these pairs sorted by their starting 
// index, and if two pairs share the same starting index, sort them by their ending index.

// Input Format
// ------------
// Line-1: string STR(without any space)
// Line-2: space separated strings, suspicious keywords[]

// Output Format
// -------------
// Print the pairs[i, j] in sorted order.


// Example 1:
// ----------
// Input:  
// cybersecuritybreachalert
// breach alert cyber

// Output: 
// 0 4
// 13 18
// 19 23

// Example 2:
// ----------
// Input:  
// phishphishingphish
// phish phishing

// Output:
// 0 4
// 5 9
// 5 12
// 13 17


// Explanation: Notice that keywords can overlap—for instance, the word "phish" appears 
// as part of the substring [5,9] in addition to the complete "phishing" substring [5,12].

// Constraints:

// - 1 <= emailText.length <= 100  
// - 1 <= suspiciousWords.length <= 20  
// - 1 <= suspiciousWords[i].length <= 50  
// - emailText and each suspicious word consist of lowercase English letters.  
// - All suspicious words are unique.

import java.util.*;

public class SuspiciousTexts {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String s = sc.nextLine();
        String[] sus = sc.nextLine().split(" ");
        
        checkSuspicious(sus, s);

        sc.close();
    }
    
    private static void checkSuspicious(String[] sus, String s) {
        Trie trie = new Trie();
        
        // trie.insert(s);
        for(int i = 0; i < s.length(); i++) {
            trie.insert(s.substring(i), i);
        }
        
        List<int[]> res = new ArrayList<>();
        for(String str : sus) {
            List<Integer> starts = trie.search(str);
            for(int start : starts) {
                res.add(new int[] { start, start + str.length() - 1 });
            }
        }
        
        Collections.sort(res, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        for(int[] r : res) {
            System.out.println(r[0] + " " + r[1]);
        }
    }
}

class Node {
    List<Integer> indices;
    Node[] children;
    boolean isEnd;
    
    Node() {
        indices = new ArrayList<>();
        this.children = new Node[26];
        this.isEnd = false;
    }
}

class Trie {
    Node root;
    
    Trie() {
        root = new Node();
    }
    
    public void insert(String word, int startIdx) {
        Node node = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if(node.children[idx] == null) {
                node.children[idx] = new Node();
            }
            
            node = node.children[idx];
            node.indices.add(startIdx);
        }
        
        node.isEnd = true;
    }
    
    public List<Integer> search(String word) {
        Node node = root;
        
        for(char c : word.toCharArray()) {
            int idx = c - 'a';
            // if(node.children[idx] == null) return new int[] { -1, -1 };
            
            node = node.children[idx];
        }
        
        return node.indices;
    }
}