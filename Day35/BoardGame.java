package Day35;

// Ramesh and Suresh are best friends. 
// They decided to play a word game.

// The game rules are as follows:
// 	- The game board shows a word W consist of two characters only A and B.
// 	- You are allowed to replace a pair of neighbour letters AA with BB in W.
// 	- Finally, The one who failed to replace the letters will lose the game.

// You can assume that Ramesh will start playing the game always.

// Your task is to determine if Ramesh can guarantee a win.
// Print 'true', if Ramesh guarantee a win, otherwise, print 'false'.

// Input Format:
// -------------
// A string W, word.

// Output Format:
// --------------
// Print a boolean value.

// Sample Input-1:
// ---------------
// AAABAAAA

// Sample Output-1:
// ----------------
// true

// Explanation:
// ------------
// Given word is AAABAAAA 
// Ramesh -> AAABBBAA 
// Now whatever the pair Suresh replaced with BB, 
// one more replace is possible for Ramesh
// So, there is a guarantee for Ramesh to win.

// Sample Input-2:
// ---------------
// AAAAAA

// Sample Output-2:
// ----------------
// true

// Sample Input-3:
// ---------------
// AAABAAA

// Sample Output-3:
// ----------------
// false

import java.util.*;

public class BoardGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String word = sc.nextLine();

        System.out.println(backtrack(word.toCharArray(), 0, true));

        sc.close();
    }

    private static boolean backtrack(char[] word, int idx, boolean ramesh) {
        // if(idx >= word.length - 1) {
        // // System.out.println("Reached end. ramesh: " + ramesh + ", word: " +
        // Arrays.toString(word));
        // return !ramesh;
        // }

        for (int i = 0; i < word.length - 1; i++) {
            if (word[i] == 'A' && word[i + 1] == 'A') {
                word[i] = 'B';
                word[i + 1] = 'B';

                boolean suresh = backtrack(word, idx + 1, !ramesh);

                word[i] = 'A';
                word[i + 1] = 'A';

                if (!suresh)
                    return true;
            }
        }

        return false;
    }
}