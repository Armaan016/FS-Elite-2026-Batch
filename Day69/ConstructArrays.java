package Day69;

// Luke likes to construct and play with arrays. His dad gave him an array M of 
// length N and assigned him the following tasks to be done in order:
//  - create continuous groups of size i from the array M (where i goes from 1 to N).
//  - For each group of size i, find the minimum value.
//  - Among all the minimums from that step, select the maximum.
//  - Add this selected value as the i-th element of a new array P.
//  - Repeat until i = N.

// Note: Use 1-based indexing for array P in logic.

// Input Format:
// -------------
// input1: Integer N – length of array M
// input2: Integer array M of length N

// Output Format:
// --------------
// Return the array P as output.

// Sample Input:
// -------------
// 3
// 1 2 3

// Sample Output:
// --------------
// 3 2 1

// Sample Input: 
// -------------
// 4
// 20 10 30 40

// Sample Output: 
// --------------
// 40 30 10 10

import java.util.*;

public class ConstructArrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        simulateTask(nums);
        sc.close();
    }

    private static void simulateTask(int[] nums) {
        int n = nums.length;

        for (int i = 1; i <= n; i++) {
            int maxMin = findMaxMin(nums, i);
            System.out.print(maxMin + " ");
        }
    }

    private static int findMaxMin(int[] nums, int size) {
        int maxMin = Integer.MIN_VALUE;

        int n = nums.length;
        int i = 0, j = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (j < n) {
            pq.add(nums[j]);
            // System.out.println("i: " + i + " j: " + j + " pq: " + pq);

            if (j - i + 1 == size) {
                maxMin = Math.max(maxMin, pq.peek());

                pq.remove(nums[i]);
                i++;
            }

            j++;
        }

        // System.out.println("For size: " + size + " maxMin: " + maxMin);
        return maxMin;
    }
}