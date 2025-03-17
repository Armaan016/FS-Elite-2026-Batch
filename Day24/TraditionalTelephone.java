// Imagine you're a top-secret agent receiving an encrypted directive from headquarters. The message comes as a string of digits, and each digit (from 2 to 9) is a cipher 
// for a set of potential code letters. To uncover the true instruction, you must translate the string into every possible combination of letters by substituting each digit with 
// its corresponding set of letters. The final decoded messages listed in lexicographycal order.

// Below is the mapping of digits to letters (as found on a traditional telephone keypad):

// | Digit | Letters       |
// |-------|---------------|
// | 2     | a, b, c       |
// | 3     | d, e, f       |
// | 4     | g, h, i       |
// | 5     | j, k, l       |
// | 6     | m, n, o       |
// | 7     | p, q, r, s    |
// | 8     | t, u, v       |
// | 9     | w, x, y, z    |

// Note: The digit 1 does not correspond to any letters.

// Example 1:
// Input: 23  
// Output: [ad, ae, af, bd, be, bf, cd, ce, cf]

// Example 2:
// Input: 2 
// Output: [a, b, c]

// Constraints:

// - 0 <= digits.length <= 4  
// - Each digit in the input is between '2' and '9'.

import java.util.*;

public class TraditionalTelephone {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int key = sc.nextInt();

        System.out.println(decodeKey(key));

        sc.close();
    }

    private static List<String> res;

    private static List<String> decodeKey(int key) {
        res = new ArrayList<>();
        Map<Character, List<Character>> map = new HashMap<>();

        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));

        String k = Integer.toString(key);
        backtrack(k, 0, map, new StringBuilder());

        return res;
    }

    private static void backtrack(String key, int idx, Map<Character, List<Character>> map, StringBuilder curr) {
        if (idx == key.length()) {
            res.add(curr.toString());
            return;
        }

        char k = key.charAt(idx);
        for (char c : map.get(k)) {
            curr.append(c);
            backtrack(key, idx + 1, map, curr);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}