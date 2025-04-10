package Day34;

// Given a classic mobile phone, and the key pad of the phone looks like below.
// 	1		2		3
// 		   abc	   def

// 	4		5		6
//    ghi     jkl     mno

// 	7		8		9
//   pqrs     tuv    wxyz

// 	*		0		#

// You are given a string S contains digits between [2-9] only, 
// For example: S = "2", then the possible words are "a", "b", "c".

// Now your task is to find all possible words that the string S could represent.
// and print them in a lexicographical order. 

// Input Format:
// -------------
// A string S, consist of digits [2-9]

// Output Format:
// --------------
// Print the list of words in lexicographical order.

// Sample Input-1:
// ---------------
// 2

// Sample Output-1:
// ----------------
// [a, b, c]

// Sample Input-2:
// ---------------
// 24

// Sample Output-2:
// ----------------
// [ag, ah, ai, bg, bh, bi, cg, ch, ci]

import java.util.*;

public class LetterCombinations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(getCombinations(n));

        sc.close();
    }

    private static List<String> res;

    private static List<String> getCombinations(int n) {
        Map<Character, String> map = new HashMap<>();

        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        res = new ArrayList<>();
        backtrack(Integer.toString(n), map, 0, new StringBuilder());

        return res;
    }

    private static void backtrack(String n, Map<Character, String> map, int idx, StringBuilder sb) {
        if (idx == n.length()) {
            res.add(sb.toString());
            return;
        }

        char curr = n.charAt(idx);
        for (char c : map.get(curr).toCharArray()) {
            sb.append(c);
            backtrack(n, map, idx + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
