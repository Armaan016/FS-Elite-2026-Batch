// Arjun wants to build some homes in a land of size R*C.
// He wanted to construct homes in rectangular shape.
// The place which is remained will be used for gradening.
// Accordingly he has prepared the plan and given as
// an 2d array plan[][], where 1 indicates home, and 0 indicates garden area.

// A home is set of cells with value 1 in rectangular shape.
// He wants to findout all the homes in the plan and store their co-ordinates in 
// the following order, coords[i] = [x1,y1,x2,y2], where (x1,y1) is the starting
// co-ordinate (top left corner), and (x2,y2) is the ending co-ordinate 
// (bottom right corner) of i-th home.

// Your task is to help Arjun to find all the homes and return the coords[][] of 
// all the homes from top left corner to bottom right corner.

// NOTE: No two homes are adjacent to each other in 4 directions,
// (left, right, top, bottom).

// Input Format:
// -------------
// Line-1: Two integers R and C, size of the land.
// Next R lines: C space separated integers, either 0 or 1
// 0- represents garden area land and 1- represents the home.

// Output Format:
// --------------
// Print 2d array, the co-ordinates of all homes.

// Sample Input-1:
// ---------------
// 2 3
// 1 0 0
// 0 1 1

// Sample Output-1:
// ----------------
// [0, 0, 0, 0][1, 1, 1, 2]

// Sample Input-2:
// ---------------
// 4 4
// 1 1 0 1
// 0 0 0 0
// 1 1 0 1
// 1 1 0 1

// Sample Output-2:
// ----------------
// [0, 0, 0, 1][0, 3, 0, 3][2, 0, 3, 1][2, 3, 3, 3]

import java.util.*;

public class HomeCoordinates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();

        int[][] grid = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        List<int[]> res = getHomeCoords(grid);
        for (int[] r_ : res) {
            System.out.print(Arrays.toString(r_) + "");
        }

        sc.close();
    }

    private static List<int[]> getHomeCoords(int[][] grid) {
        int r = grid.length, c_ = grid[0].length;

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c_; j++) {
                if (grid[i][j] == 1) {
                    int[] c = new int[] { i };
                    int[] d = new int[] { j };
                    dfs(grid, i, j, c, d);
                    res.add(new int[] { i, j, c[0], d[0] });
                }
            }
        }

        return res;
    }

    private static void dfs(int[][] grid, int i, int j, int[] i_, int[] j_) {
        if (i >= grid.length || i < 0 || j >= grid[0].length || j < 0 || grid[i][j] == 0)
            return;

        grid[i][j] = 0;

        i_[0] = Math.max(i_[0], i);
        j_[0] = Math.max(j_[0], j);

        dfs(grid, i + 1, j, i_, j_); // down
        dfs(grid, i, j + 1, i_, j_); // right
    }
}