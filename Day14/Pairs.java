// Two brothers want to play a game, 
// The rules of the game are: one player gives two sorted lists of 
// numerical elements and a number (sum). 
// The opponent has to find the closest pair of elements 
// to the given sum.
// -> pair consists of elements from each list

// Please help those brothers to develop a program, that takes 
// two sorted lists as input and return a pair as output.

// Input Format:
// -------------
// size of list_1
// list_1 values
// size of list_2
// list_2 values
// closest number

// Output Format:
// --------------
// comma-separated pair

// Sample Input-1:
// ---------------
// 4
// 1 4 5 7
// 4
// 10 20 30 40
// 32
// Sample Output-1
// ---------------
// 1, 30

// Sample Input-2
// ---------------
// 3
// 2 4 6
// 4
// 5 7 11 13
// 15
// sample output-2
// ---------------
// 2, 13

import java.util.*;

public class Pairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] nums1 = new int[n];
        for (int i = 0; i < n; i++) {
            nums1[i] = sc.nextInt();
        }

        int m = sc.nextInt();

        int[] nums2 = new int[m];
        for (int i = 0; i < m; i++) {
            nums2[i] = sc.nextInt();
        }

        int tgt = sc.nextInt();

        findPair(nums1, nums2, tgt);

        sc.close();
    }

    private static void findPair(int[] nums1, int[] nums2, int tgt) {
        int l = 0, r = nums2.length - 1;

        int closest = Integer.MAX_VALUE, a = 0, b = 0;
        while (l < nums1.length && r >= 0) {
            int sum = nums1[l] + nums2[r];

            if (Math.abs(sum - tgt) < Math.abs(closest - tgt)) {
                a = nums1[l];
                b = nums2[r];
                closest = sum;
            }

            if (sum < tgt)
                l++;
            else
                r--;
        }

        System.out.println(a + ", " + b);
    }
}