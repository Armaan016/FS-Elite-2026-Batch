package Day34;

// Bablu is working in a construction field.
// He has N number of building blocks, where the height and width of all the blocks are same.
// And the length of each block is given in an array, blocks[].

// Bablu is planned to build a wall in the form of a square.
// The rules to cunstruct the wall are as follows:
// 	- He should use all the building blocks.
// 	- He should not break any building block, but you can attach them with other.
// 	- Each building-block must be used only once.

// Your task is to check whether Bablu can cunstruct the wall as a square
// with the given rules or not. If possible, print true. Otherwise, print false.

// Input Format:
// -------------
// Line-1: An integer N, number of BuildingBlocks.
// Line-2: N space separated integers, length of each block.

// Output Format:
// --------------
// Print a boolean value.

// Sample Input-1:
// ---------------
// 6
// 1 2 6 4 5 6

// Sample Output-1:
// ----------------
// true

// Sample Input-2:
// ---------------
// 6
// 5 3 2 5 5 6

// Sample Output-2:
// ----------------
// false

import java.util.*;

public class SquareWall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] blocks = new int[n];
        for (int i = 0; i < n; i++) {
            blocks[i] = sc.nextInt();
        }

        System.out.println(checkSquare(blocks));

        sc.close();
    }

    private static boolean checkSquare(int[] blocks) {
        // int max = Arrays.stream(blocks).max().getAsInt();
        int sum = Arrays.stream(blocks).sum();

        if (sum % 4 != 0)
            return false;

        boolean[] used = new boolean[blocks.length];
        return backtrack(blocks, used, sum / 4, 0, 0, 4);
    }

    private static boolean backtrack(int[] blocks, boolean[] used, int target, int curr, int idx, int sides) {
        if (sides == 0)
            return true;
        if (curr == target)
            return backtrack(blocks, used, target, 0, 0, sides - 1);

        if (curr > target)
            return false;

        for (int i = idx; i < blocks.length; i++) {
            if (!used[i] && curr + blocks[i] <= target) {
                used[i] = true;
                if (backtrack(blocks, used, target, curr + blocks[i], i + 1, sides))
                    return true;
                used[i] = false;
            }
        }

        return false;
    }
}

// Map<Integer, Integer> map = new HashMap<>();
// int edges = 0;
// for(int block : blocks) {
// if(block == max) {
// edges++;
// continue;
// }
// else {
// int comp = max - block;
// if(map.containsKey(comp)) {
// map.put(comp, map.get(comp) - 1);
// if(map.get(comp) == 0) map.remove(comp);
// edges++;
// } else {
// map.put(block, map.getOrDefault(block, 0) + 1);
// }
// }
// }

// return edges == 4 && map.isEmpty();
// // return map.isEmpty() && blocks.length == max;