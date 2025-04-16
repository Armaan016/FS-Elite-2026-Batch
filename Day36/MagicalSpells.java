package Day36;

// In the ancient land of Palindoria, wizards write magical spells as strings of 
// lowercase letters. However, for a spell to be effective, each segment of the 
// spell must read the same forward and backward.

// Given a magical spell 'spell', your task is to partition it into sequences of 
// valid magical spell segments. 
// Your goal is to help the wizard discover all valid combinations of magical spell 
// segmentations.

// Example 1:
// ----------
// Input:  
// aab

// Output:  
// [[a, a, b], [aa, b]]

// Example 2:

// Input:  
// a  
// Output:  
// [[a]]

// Spell Constraints:

// - The length of the spell is between 1 and 16 characters.  
// - The spell contains only lowercase English letters.  

import java.util.*;

public class MagicalSpells {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String spell = sc.nextLine();

        System.out.println(segmentSpell(spell));

        sc.close();
    }

    private static List<List<String>> res;

    private static List<List<String>> segmentSpell(String s) {
        res = new ArrayList<>();

        backtrack(s, 0, new ArrayList<>());

        return res;
    }

    private static void backtrack(String s, int idx, List<String> curr) {
        if (idx == s.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = idx + 1; i <= s.length(); i++) {
            String sub = s.substring(idx, i);
            if (isPalindrome(sub)) {
                curr.add(sub);
                backtrack(s, i, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }

        return true;
    }
}
