package Day66;

// You have given flatland in the form of m*n grid,
// The land contains some ponds represented as 0,
// and the land represented as 1.

// Your task is to find the number of square-shaped lands which contains no
// pond.

// Input Format:
// -------------
// Line-1: Two integers M and N.
// Next M lines: N space separated integers.

// Output Format:
// --------------
// Print an integer, number of square-grids.

// Sample Input-1:
// ---------------
// 3 4
// 0 1 1 1
// 1 1 1 1
// 0 1 1 1

// Sample Output-1:
// ----------------
// 15

// Explanation:
// ------------
// There are 10 square-grids of side length 1.
// There are 4 square-grids of side length 2.
// There is 1 square-grid of side length 3.

// Total number of square-grids without a pond in it = 10 + 4 + 1 = 15.

// Sample Input-2:
// ---------------
// 3 3
// 1 0 1
// 1 1 0
// 1 1 0

// Sample Output-2:
// ----------------
// 7

// Explanation:
// ------------
// There are 6 square-grids of side length 1.
// There is 1 square-grid of side length 2.
// Total number of square-grids without a pond in it = 6 + 1 = 7.

import java.util.*;

public class PondsAndLands {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        System.out.println(countLands(grid));
        sc.close();
    }

    private static int countLands(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int sizes = Math.min(m, n);
        int count = 0;

        for (int size = 1; size <= sizes; size++) {
            count += (checkGrid(grid, size));
        }

        return count;
    }

    private static int checkGrid(int[][] grid, int size) {
        int m = grid.length, n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && check(grid, i, j, size))
                    count++;
            }
        }

        return count;
    }

    private static boolean check(int[][] grid, int i_, int j_, int size) {
        if (size == 1)
            return true;
        // System.out.println("Checking for i: " + i_ + " j: " + j_);

        int checked = 0;
        for (int i = i_; i < grid.length && i < i_ + size; i++) {
            for (int j = j_; j < grid[0].length && j < j_ + size; j++) {
                // System.out.println("i: " + i + " j: " + j);
                if (grid[i][j] == 0)
                    return false;
                checked++;
            }
        }

        return checked == size * size;
    }
}