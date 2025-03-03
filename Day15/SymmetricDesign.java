// Imagine you are an artisan tasked with assembling a decorative mosaic from a 
// collection of uniquely colored tiles. Each tile is marked with a character, 
// and your challenge is to rearrange these tiles to create a design that mirrors 
// itself perfectly from left to right. 
// Your goal is to determine whether the available tiles can be arranged to form 
// such a symmetric pattern. Print true if a symmetric design is possible,
// and false otherwise.

// Input format:
// A string representing the characters on the tiles.

// Output format:
// Print a boolean value

// Example 1:
// input: work
// output: false

// Example 2:
// input: ivicc
// output: true

// Constraints:
// 1 <= string.length <= 5000
// tile characters are all lowercase English letters.

import java.util.*;

public class SymmetricDesign {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        System.out.println(checkSymmetric(s));

        sc.close();
    }

    private static boolean checkSymmetric(String s) {
        int l = s.length();

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int odd = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0)
                odd++;
        }

        // System.out.println("odd: " + odd);
        if (l % 2 == 0)
            return (odd == 0);
        else
            return (odd % 2 != 0);
    }
}