package Day75;

// A secret agent encodes a message by recursively scrambling it using a random encryption 
// protocol.  The encryption process follows these rules:
//  - If the message is a single character, leave it unchanged.
//  - If the message has more than one character:
// 		- Split the message into two non-empty parts at any random position.
// 		- With a 50% chance, swap the two parts; otherwise, keep them in the same order.
// 		- Repeat this scrambling recursively on each part.

// This encryption method produces a scrambled version of the original message.

// You are now given two messages:
// original: the message before scrambling.
// scrambled: a possibly scrambled version of the original message.

// Write a program to determine whether the scrambled message could have been produced 
// by scrambling the original message using the above protocol.

// Sample Input:
// -------------
// Two strings, original and scrambled, each of equal length.

// Sample Output:
// ---------------
// Return true if the scrambled string could be a scrambled version of the original using 
// the given encryption protocol. Otherwise, return false.

// Sample Input:
// -------------
// secure cesure

// Sample Output:
// ---------------
// true

// Explanation: 
// ------------
// One possible scrambling path leads from "secure" to "cesure".

// Sample Input:
// -------------
// planet npealt

// Sample Output:
// ---------------
// false

// Explanation: 
// ------------
// No sequence of valid splits and swaps can lead to "petlan" from "npealt".

import java.util.*;

public class ScrambleStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String original = sc.next();
        String scrambled = sc.next();

        System.out.println(canScramble(original, scrambled));
        sc.close();
    }

    private static boolean canScramble(String original, String scrambled) {
        if (original.length() != scrambled.length())
            return false;

        Map<String, Boolean> dp = new HashMap<>();
        return memo(original, scrambled, dp);
    }

    private static boolean memo(String s1, String s2, Map<String, Boolean> dp) {
        if (s1.equals(s2))
            return true;
        if (s1.length() != s2.length())
            return false;

        String key = s1 + "," + s2;
        if (dp.containsKey(key))
            return dp.get(key);

        // System.out.println("Trying s1: " + s1 + " s2: " + s2 + " idx: " + idx);
        for (int i = 1; i < s1.length(); i++) {
            boolean a = memo(s1.substring(0, i), s2.substring(0, i), dp) && memo(s1.substring(i), s2.substring(i), dp);
            if (a) {
                dp.put(key, true);
                return true;
            }

            boolean b = memo(s1.substring(0, i), s2.substring(s2.length() - i), dp)
                    && memo(s1.substring(i), s2.substring(0, s2.length() - i), dp);
            if (b) {
                dp.put(key, true);
                return true;
            }
        }

        dp.put(key, false);
        return false;
    }

    // private static String reverse(String s) {
    // char[] c = s.toCharArray();

    // int i = 0, j = s.length() - 1;
    // while(i < j) {
    // char t = c[i];
    // c[i] = c[j];
    // c[j] = t;
    // i++; j--;
    // }

    // return new String(c);
    // }
}