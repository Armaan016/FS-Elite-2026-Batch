/*You are given an integer array nums and two integers l and r. Your task is to 
find the minimum positive energy produced by a sequence of operations. 
Each operation corresponds to selecting a contiguous subarray of nums 
whose length is between l and r (inclusive).

The energy of a sequence is defined as the product of all the numbers in 
the subarray. You need to find the sequence with the smallest positive energy.

If no such sequence exists, return -1.

Input Format:
---------------
Line-1: Three space separated integers, N, L and R.
Line-2: N space separated integers, array[].

Output Format:
-----------------
An integer result, smallest positive energy.

Sample Input-1:
-----------------
4 2 3
2 -1 3 4

Sample Output-1:
-------------------
12

Explanation:
--------------
The possible sequences of operations with lengths between l = 2 and r = 3 are:

[2, -1] (not valid, energy = -2)
[3, 4] (energy = 12)
[2, -1, 3] (not valid, energy = -6)
The sequence [3, 4] produces the smallest positive energy of 12. Hence, 
the output is 12.

Sample Input-2:
-----------------
3 2 3
-1 -3 2

Sample Output-1:
-------------------
3

Explanation:
No valid sequence produces a positive energy. Thus, the output is -1.

Constraints:
============
1 ≤ nums.length ≤ 100
1 ≤ l ≤ r ≤ nums.length
−1000 ≤ nums[i] ≤ 1000*/

import java.util.*;

public class SlidingWindow1 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        
        System.out.println(minPosSum(nums, n, l, r));

        sc.close();
    }
    
    private static long minPosSum(int[] nums, int n, int l, int r) {
        long minProd = Long.MAX_VALUE;
        
        // 4 2 3
        // 2 -1 3 4
        
        int i = 0, j = 0;
        long prod = 1;
        while(j < n) {
            prod *= nums[j];
            
            int len = j - i + 1;
            
            System.out.println("i: " + i + " j: " + j + " len: " + len + " prod: " + prod);
            if(len >= l && len <= r) {
                if(prod > 0) minProd = Math.min(minProd, prod);
            }
            
            while(j - i + 1 > l) {
                if(nums[i] != 0) prod /= nums[i];
                
                i++;
                System.out.println("Incrementing i: " + i + " j: " + j + " len: " + (j - i + 1) + " prod: " + prod);
                if(prod > 0) minProd = Math.min(minProd, prod);
            }
            
            j++;
        }
        
        return (minProd == Long.MAX_VALUE) ? -1 : minProd;
    }
}