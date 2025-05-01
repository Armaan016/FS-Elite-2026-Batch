package Day43;

// You are controlling a battlefield represented by an m x n grid. 

// Each cell on this grid can be one of the following:

// - A reinforced barrier ('B'), blocking your laser.
// - An enemy drone ('D'), your target.
// - An open cell ('0'), where you can position your robot to fire.

// When your robot fires its powerful laser from an open cell, 
// the beam destroys all enemy drones in the same row and column 
// until the beam hits a barrier ('B'). The barrier completely stops 
// the laser, protecting anything behind it.

// Your goal is to identify the best position (open cell) to place 
// your robot so that firing the laser destroys the maximum number 
// of enemy drones in a single shot. Return this maximum number.

// Input format:
// -------------
// Line 1: Two space separated integers, represents m & n
// Next M lines: each row of battlefield

// Output format:
// --------------
// An integer, maximum number of enemy drones destroyed in a single shot.

// Example 1:
// ----------
// input=
// 3 4
// 0 D 0 0
// D 0 B D
// 0 D 0 0

// Output=
// 3

// Explanation: placing robot at battlefield[1][1] destroys 3 enemy drones 
// in a single shot.

// Example 2:
// ----------
// 3 3
// B B B
// 0 0 0
// D D D

// Output=
// 1

// Constraints:
// - 1 <= m, n <= 500
// - battlefield[i][j] is either 'B', 'D', or '0'.

import java.util.*;

public class EnemyDrones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        sc.nextLine();
        char[][] battlefield = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                battlefield[i][j] = sc.next().charAt(0);
            }
        }

        System.out.println(maxDrones(battlefield));

        sc.close();
    }

    private static int maxDrones(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<String> drones = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'D')
                    drones.add(i + "," + j);
            }
        }

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0')
                    max = Math.max(max, memo(grid, drones, i, j));
            }
        }

        return max;
    }

    private static int memo(char[][] grid, List<String> drones, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 'B')
            return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        // System.out.println("i: " + i + " j: " + j);

        int res = 0;
        for (int k = j - 1; k >= 0; k--) {
            if (grid[i][k] == 'B')
                break;
            if (grid[i][k] == 'D')
                res++;
        }

        for (int k = j + 1; k < cols; k++) {
            if (grid[i][k] == 'B')
                break;
            if (grid[i][k] == 'D')
                res++;
        }

        for (int k = i - 1; k >= 0; k--) {
            if (grid[k][j] == 'B')
                break;
            if (grid[k][j] == 'D')
                res++;
        }

        for (int k = i + 1; k < rows; k++) {
            if (grid[k][j] == 'B')
                break;
            if (grid[k][j] == 'D')
                res++;
        }

        return res;
    }
}