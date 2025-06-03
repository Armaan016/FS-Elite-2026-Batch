package Day65;

// Mr. Parandamayya is working with Strings.
// He is given a String S. He has to find the palindromes in S, 
// A palindrome can be a substring of S (Strictly substrings only, not subsequences).

// Your task is to find the count the number of palindromes can be formed from S.

// NOTE: Count each occurence of palindromes if duplicate substrings found. 

// Input Format:
// -------------
// A string S

// Output Format:
// --------------
// Print an integer, number of palindromes.


// Sample Input-1:
// ---------------
// divider

// Sample Output-1:
// ----------------
// 9

// Explanation:
// -------------
// Palindromes are d, i, v, i, d, e, r, ivi, divid

// Sample Input-2:
// ---------------
// abcdef

// Sample Output-2:
// ----------------
// 6

// Explanation:
// -------------
// Palindromes are a, b, c, d, e, f.

import java.util.*;

public class CountPalindromes {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String s = sc.nextLine();
        System.out.println(countPalindromes(s));
        // System.out.println(bruteForce(s));
        
        sc.close();
    }
    
    private static int countPalindromes(String s) {
        int n = s.length(), count = 0;
        for(int i = 0; i < n; i++) {
            count += (checkPalindromes(s, i, i));
            count += (checkPalindromes(s, i, i + 1));
        }
        
        return count;
    }
    
    private static int checkPalindromes(String s, int l, int r) {
        int count = 0;
        
        while(l >= 0 && r < s.length()) {
            if(s.charAt(l) == s.charAt(r)) count++;
            else break;
            l--; r++;
        }
        
        return count;
    }
    
    // private static int bruteForce(String s) {
    //     int n = s.length();
    //     int count = 0;
    //     for(int i = 0; i < n; i++) {
    //         for(int j = i + 1; j <= n; j++) {
    //             if(isPalindrome(s.substring(i, j))) count++;
    //         }       
    //     }
        
    //     return count;
    // }
    
    // private static boolean isPalindrome(String s) {
    //     int l = 0, r = s.length() - 1;
    //     while(l < r) {
    //         if(s.charAt(l) != s.charAt(r)) return false;
    //         l++; r--;
    //     }
        
    //     return true;
    // }
}