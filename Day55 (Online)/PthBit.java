// A binary word Bn is formed as follows:
//     B[1] = "0"
//     B[i+1] =  B[i] $ "1" $ reverse(complement(B[i])) for i > 1

// where $ denotes the concatenation operation, reverse(complement(B)) returns 
// the reversed word of complement(B), which perform 1's complement of B 
// (0 changes to 1 and 1 changes to 0).

// For example, the first 4 words in the above sequence are:

//     B[1] = "0"
//     B[2] = "011"
//     B[3] = "0111001"
//     B[4] = "011100110110001"

// Return the Pth bit in B[N]. It is guaranteed that P is valid for the given N.

// Input Format:
// -------------
// Line-1: Two space seperated integers represents N and P.

// Output Format:
// --------------
// Return a bit (0 or 1).

// Sample Input-1:
// ---------------
// 3 4

// Sample Output-1:
// ----------------
// 1

// Explanation:
// ------------
// B[3] = "0111001" and 4th bit is 1.

// Sample Input-2:
// ---------------
// 4 10

// Sample Output-2:
// ----------------
// 1

// Explanation:
// -------------
// B[4] = "011100110110001" and 10th bit is 1.

import java.util.*;

public class PthBit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int p = sc.nextInt();

        System.out.println(getPthBit(n, p));
        sc.close();
    }

    // Brute force approach
    private static char getPthBit(int n, int p) {
        List<String> binaries = new ArrayList<>();
        binaries.add("0");
        for (int i = 1; i < n; i++) {
            String prev = binaries.get(i - 1);
            String comp = complement(prev.toCharArray());

            // System.out.println("prev: " + prev + " comp: " + comp);
            binaries.add(prev + "1" + reverse(comp.toCharArray()));
        }

        // System.out.println(binaries);
        return binaries.get(n - 1).charAt(p - 1);
    }

    // Optimized approach
    // private static char getPthBitOptimized(int n, double p) {
    //     if (n == 1)
    //         return '0';

    //     double len = Math.pow(2, n) - 1;

    //     double mid = Math.ceil(len / 2.0);

    //     if (p == mid)
    //         return '1';

    //     if (p < mid)
    //         return getPthBitOptimized(n - 1, p);

    //     return getPthBitOptimized(n - 1, len - p + 1) == '0' ? '1' : '0';
    // }

    private static String complement(char[] s) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '0')
                s[i] = '1';
            else
                s[i] = '0';
        }
        
        return new String(s);
    }

    private static String reverse(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char t = s[l];
            s[l] = s[r];
            s[r] = t;
            l++;
            r--;
        }

        return new String(s);
    }
}