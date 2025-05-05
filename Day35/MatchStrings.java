package Day35;

// Given two strings S1 and S2, find if S2 can match S1 or not.

// A match that is both one-to-one (an injection) and onto (a surjection),
// i.e. a function which relates each letter in string S1 to a separate and
// distinct non-empty substring in S2, where each non-empty substring in S2
// also has a corresponding letter in S1.

// Return true,if S2 can match S1.
// Otherwise false.

// Input Format:
// -------------
// Line-1 -> Two strings S1 and S2

// Output Format:
// --------------
// Print a boolean value as result.

// Sample Input-1:
// ---------------
// abab kmitngitkmitngit

// Sample Output-1:
// ----------------
// true

// Sample Input-2:
// ---------------
// aaaa kmjckmjckmjckmjc

// Sample Output-2:
// ----------------
// true

// Sample Input-3:
// ---------------
// mmnn pqrxyzpqrxyz

// Sample Output-3:
// ----------------
// false

import java.util.*;

public class MatchStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] line = sc.nextLine().split(" ");
        String s1 = line[0];
        String s2 = line[1];

        System.out.println(canMatch(s1, s2, 0, 0, new HashMap<>(), new HashSet<>()));

        sc.close();
    }

    private static boolean canMatch(String s1, String s2, int i, int j, Map<Character, String> map, Set<String> used) {
        if (i == s1.length() && j == s2.length()) {
            // System.out.println("map: " + map);
            return true;
        }
        if (i == s1.length() || j == s2.length())
            return false;

        char a_ = s1.charAt(i);
        for (int idx = j + 1; idx <= s2.length(); idx++) {
            String b_ = s2.substring(j, idx);
            // System.out.printf("a_: %c, b_: %s\n", a_, b_);

            if (map.containsKey(a_)) {
                if (!map.get(a_).equals(b_))
                    continue;
                if (canMatch(s1, s2, i + 1, idx, map, used))
                    return true;
            } else {
                if (used.contains(b_))
                    continue;

                map.put(a_, b_);
                used.add(b_);
                // System.out.println("map: " + map);
                if (canMatch(s1, s2, i + 1, idx, map, used))
                    return true;

                map.remove(a_);
                used.remove(b_);
            }
        }

        return false;
    }
}