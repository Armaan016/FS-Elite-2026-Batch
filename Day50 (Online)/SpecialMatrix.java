package Day50;

// Venkatadri is a maths teacher.
// He is teaching matrices to his students.
// He is given a matrix of size m*n, and it contains only positive numbers.
// He has given a task to his students to find the special matrix, 
// in the iven matrix A[m][n].
// A special matrix has following property:
// 	- The sum of elements in each row, each column and the two diagonals are equal.
// 	- Every 1*1 matrix is called as a special matrix.
// 	- The size of the special matrix should be a square, i.e., P*P.

// Your task is to help the students to find the speical matrix  with max size P.

// Input Format:
// -------------
// Line-1: Two space separated integers M and N, size of the matrix.
// Next M lines: N space separated integers m and n.

// Output Format:
// --------------
// Print an integer, maximum size P of the special matrix.

// Sample Input-1:
// ---------------
// 5 5
// 7 8 3 5 6
// 3 5 1 6 7
// 3 5 4 3 1
// 6 2 7 3 2
// 5 4 7 6 2

// Sample Output-1:
// ----------------
// 3

// Explanation:
// ------------
// The special square is:
// 5 1 6
// 5 4 3
// 2 7 3

// Sample Input-2:
// ---------------
// 4 4
// 7 8 3 5
// 3 2 1 6
// 3 2 3 3
// 6 2 3 3

// Sample Output-2:
// ----------------
// 2

// Explanation:
// ------------
// The special square is:
// 3 3
// 3 3

import java.util.*;

public class SpecialMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(maxSpecialMatrix(matrix));

        sc.close();
    }

    private static int maxSpecialMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int r = Math.min(m, n);

        // int ans = 0;
        // while(l <= r) {
        // int mid = l + (r - l) / 2;

        // if(isPossible(matrix, mid)) {
        // ans = mid;
        // l = mid + 1;
        // } else {
        // r = mid - 1;
        // }
        // }

        for (int p = r; p >= 2; p--) {
            if (isPossible(matrix, p)) {
                return p;
            }
        }

        return 1;
    }

    private static boolean isPossible(int[][] matrix, int p) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i <= m - p; i++) {
            for (int j = 0; j <= n - p; j++) {
                if (isValid(matrix, i, j, p)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isValid(int[][] matrix, int i, int j, int p) {
        int row[] = new int[p];
        int col[] = new int[p];
        int diag1 = 0;
        int diag2 = 0;

        for (int i_ = 0; i_ < p; i_++) {
            for (int j_ = 0; j_ < p; j_++) {
                int val = matrix[i + i_][j + j_];
                row[i_] += val;
                col[j_] += val;

                if (i_ == j_)
                    diag1 += val;
                if (i_ + j_ == p - 1)
                    diag2 += val;
            }
        }

        int tgt = row[0];
        for (int sum : row) {
            if (sum != tgt)
                return false;
        }

        for (int sum : col) {
            if (sum != tgt)
                return false;
        }

        if (diag1 != tgt || diag2 != tgt)
            return false;
        return true;
    }
}