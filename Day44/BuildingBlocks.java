package Day44;

// A Kid built a structure using building blocks, 
// by placing the building-blocks adjacent to each other.

// A building-block is a vertical alignment of blocks.
// 	                            ___
// here one block each represents  as |___|.

// The following structure made up of using building blocks

//                           ___
//                       ___|___|    ___
//                      |___|___|_w_|___|___              ___
//                   ___|___|___|___|___|___| w   _w_  w |___| 
//               ___|___|___|___|___|___|___|_w__|___|_w_|___|____________

//                0  1   3   4   2   3    2   0   1   0   2

// Once the structure is completed, kid pour water(w) on it.

// You are given a list of integers, heights of each building-block in a row.
//  Now your task How much amount of water can be stored by the structure.

//  Input Format:
//  -------------
//  Space separated integers, heights of the blocks in the structure. 

// Output Format:
//  --------------
//  Print an integer, 

// Sample Input:
// -------------
//  0 1 3 4 2 3 2 0 1 0 2

// Sample Output:
// --------------
// 6

// Explanation:
//  -----------
// In the above structure,  6 units of water (w represents the water in the structure)
// can be stored.

import java.util.*;

public class BuildingBlocks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] blocks = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

        System.out.println(countWater(blocks));

        sc.close();
    }

    private static long countWater(int[] blocks) {
        int n = blocks.length;

        int[] prefix = new int[n];
        prefix[0] = blocks[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1], blocks[i]);
        }

        int[] suffix = new int[n];
        suffix[n - 1] = blocks[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], blocks[i]);
        }

        // System.out.println("Max on Left: " + Arrays.toString(prefix));
        // System.out.println("Max on Right: " + Arrays.toString(suffix));

        long water = 0;
        for (int i = 0; i < n; i++) {
            int maxL = prefix[i];
            int maxR = suffix[i];
            // System.out.println("i: " + i + " max on left: " + maxL + " max on right: " +
            // maxR);

            water += Math.max(0, Math.min(maxL, maxR) - blocks[i]);
        }

        return water;
    }
}
