package Day32;

// A detective is investigating a case involving a secret message hidden within
// a
// longer note. The detective knows that the culprit rearranged the letters of a
// short code-word into multiple secret locations within a larger note.

// Given two strings, note (the longer text) and codeWord (the short secret
// code),
// your task is to help the detective find all starting positions within the
// note
// where any rearrangement or shuffled of codeWord is located.

// Input Format:
// -------------
// Single line space separated strings, two words.

// Output Format:
// --------------
// Print the list of integers, indices.

// Sample Input-1:
// ---------------
// bacdgabcda abcd

// Sample Output-1:
// ----------------
// [0, 5, 6]

// Explanation:
// - At index 0: "bacd" is an anagram of "abcd"
// - At index 5: "abcd" matches exactly
// - At index 6: "bcda" is an anagram of "abcd"
// Thus, the positions [0, 5, 6] are returned.

// Sample Input-2:
// ---------------
// bacacbacdcab cab

// Sample Output-2:
// ----------------
// [0, 3, 4, 5, 9]

import java.util.*;

public class CountShuffledAnagrams {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[] line = sc.nextLine().split(" ");
        String word = line[0];
        String code = line[1];
        
        System.out.println(countOcc(word, code));

        sc.close();
    }
    
    private static List<Integer> countOcc(String word, String code) {
        List<Integer> res = new ArrayList<>();
        int n = word.length(), l = code.length();
        
        // Map<Character, Integer> mapC = new HashMap<>();
        int[] mapC = new int[26];
        for(char c : code.toCharArray()) {
            mapC[c - 'a']++;
        }
        
        int i = 0, j = 0;
        // Map<Character, Integer> map = new HashMap<>();
        int[] map = new int[26];
        while(j < n) {
            // map.put(word.charAt(j), map.getOrDefault(word.charAt(j), 0) + 1);
            map[word.charAt(j) - 'a']++;
            
            if(j - i + 1 == l) {
                if(compareMaps(map, mapC)) res.add(i);
                
                // map.put(word.charAt(i), map.get(word.charAt(i)) - 1);
                map[word.charAt(i) - 'a']--;
                i++;
            }
            
            j++;
        }
        
        return res;
    }
    
    private static boolean compareMaps(int[] map1, int[] map2) {
        for(int i = 0; i < 26; i++) {
            if(map1[i] != map2[i]) return false;
        }
        
        return true;
    }
}