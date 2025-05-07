package Day46;

// A group of researchers is analyzing satellite imagery of agricultural fields, 
// represented by a grid of land sections. Each section is marked either as 
// fertile (1) or infertile (0). To efficiently plan crop planting, the researchers 
// need to identify the largest rectangular area consisting exclusively of fertile 
// land sections.

// Given a binary grid representing the land (1 for fertile and 0 for infertile), 
// your task is to compute the area of the largest rectangle consisting entirely 
// of fertile land sections.

// Input Format:
// -------------
// Line-1: Two integers rows(r) and columns(c) of grid.
// Next r lines: c space separated integers, each row of the grid.

// Output Format:
// --------------
// Print an integer, area of the largest rectangle consisting entirely of fertile land sections.

// Example:
// --------
// input=
// 4 5
// 1 0 1 0 0
// 1 0 1 1 1
// 1 1 1 1 1
// 1 0 0 1 0

// output=
// 6

import java.util.*;

public class FertileLands {
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

        System.out.println(maxRectangularArea(grid));

        sc.close();
    }

    private static int maxRectangularArea(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, helper(grid, m, n, i, j));
            }
        }

        return res;
    }

    // private static boolean isValid(int[][] grid, int i, int j, int k, int l) {
    // // System.out.println("i: " + i + " j: " + j + " k: " + k);
    // for(int a = i; a <= j; a++) {
    // for(int b = k; b <= l; b++) {
    // if(grid[a][b] == 0) return false;
    // }
    // }

    // return true;
    // }

    private static int helper(int[][] grid, int m, int n, int i, int j) {
        if (i >= m || j >= n)
            return 0;

        if (grid[i][j] == 0)
            return 0;

        int res = 0;
        int max = Integer.MAX_VALUE;
        int height = 0;

        for (int x = i; x < m && grid[x][j] == 1; x++) {
            int width = 0;
            for (int y = j; y < n && grid[x][y] == 1; y++) {
                width++;
            }

            max = Math.min(max, width);
            height = x - i + 1;
            res = Math.max(max * height, res);
        }

        return res;
    }
}