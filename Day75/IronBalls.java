package Day75;

// Bablu is playing with Magnets and Iron balls.
// Bablu has given a flat surface of m*n size, 
// each position contains either empty '0', an Iron ball 'B' 
// or Wooden Block 'W' (The wooden block is an anti-magnetic agent), 

// Your task is to help Bablu to find the maximum number of 
// Iron Balls he can attract by using a Magnet.

// The Magnet attracts all the iron balls in the same row and column 
// from their positions until the wooden block.
// since the wooden block is an anti-magnetic block.

// Note: You can only put the magnet in an empty position.

// Input Format:
// -------------
// Line-1: Two integers m and n, size of the surface.
// Next 'm' lines:  'n' space-separated characters only ('0', 'B', 'W').

// Output Format
// --------------
// Print an integer, the maximum number of Iron Balls can be attracted by using a Magnet

// Sample Input-1:
// ----------------
// 3 4
// 0 B 0 0 
// B 0 W B
// 0 B 0 0

// Sample Output:
// --------------
// 3 

// Explanation:
// ------------
// For the given grid,
// 	0 B 0 0 
// 	B 0 W B
// 	0 B 0 0
// Placing a Magnet at (1,1) attacts 3 iron balls. 

import java.util.*;

public class IronBalls {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        sc.nextLine();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            String[] line = sc.nextLine().split(" ");

            for (int j = 0; j < n; j++) {
                grid[i][j] = line[j].charAt(0);
            }
        }

        System.out.println(countAttractedBalls(grid));
        sc.close();
    }

    private static int countAttractedBalls(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] up = new int[m][n];
        int[][] down = new int[m][n];
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                up[i][j] = countUp(i, j, grid);
                down[i][j] = countDown(i, j, grid);
                left[i][j] = countLeft(i, j, grid);
                right[i][j] = countRight(i, j, grid);
            }
        }

        // System.out.println("\nUp: ");
        // printArr(up);
        // System.out.println("\nDown: ");
        // printArr(down);
        // System.out.println("\nLeft: ");
        // printArr(left);
        // System.out.println("\nRight: ");
        // printArr(right);

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0')
                    max = Math.max(max, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
            }
        }

        return max;
    }

    private static int countUp(int r, int c, char[][] grid) {
        int i = r - 1, j = c;

        int count = 0;
        while (i >= 0) {
            if (grid[i][j] == 'W')
                break;

            if (grid[i][j] == 'B')
                count++;
            i--;
        }

        return count;
    }

    private static int countDown(int r, int c, char[][] grid) {
        int i = r + 1, j = c;

        int count = 0;
        while (i < grid.length) {
            if (grid[i][j] == 'W')
                break;

            if (grid[i][j] == 'B')
                count++;
            i++;
        }

        return count;
    }

    private static int countLeft(int r, int c, char[][] grid) {
        int i = r, j = c - 1;

        int count = 0;
        while (j >= 0) {
            if (grid[i][j] == 'W')
                break;

            if (grid[i][j] == 'B')
                count++;
            j--;
        }

        return count;
    }

    private static int countRight(int r, int c, char[][] grid) {
        int i = r, j = c + 1;

        int count = 0;
        while (j < grid[0].length) {
            if (grid[i][j] == 'W')
                break;

            if (grid[i][j] == 'B')
                count++;
            j++;
        }

        return count;
    }

    // private static void printArr(int[][] arr) {
    // for (int i = 0; i < arr.length; i++) {
    // for (int j = 0; j < arr[0].length; j++) {
    // System.out.print(arr[i][j] + " ");
    // }
    // System.out.println();
    // }
    // }
}