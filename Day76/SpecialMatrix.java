package Day76;

/*You will be given the matrix of size R*C,
You have to return the matrix in special order as shown in the sample tescases.

Input Format:
-------------
Line-1 -> Two integers R and C, size of matrix.
Next R-Lines -> C space separated integers

Output Format:
--------------
Print R-Lines -> C space separated integers, in special order.

Sample Input-1:
---------------
3 3
1 2 3
4 5 6
7 8 9

Sample Output-1:
----------------
1 2 3
6 9 8
7 4 5

Sample Input-2:
---------------
3 4
1 2 3 4
5 6 7 8
9 10 11 12

Sample Output-2:
----------------
1 2 3 4
8 12 11 10
9 5 6 7
*/

import java.util.*;

public class SpecialMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();

        int[][] matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int[][] result = specialOrder(matrix);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }

    private static int[][] specialOrder(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;

        List<Integer> spiral = new ArrayList<>();
        int top = 0, bottom = r - 1, left = 0, right = c - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                spiral.add(matrix[top][i]);
            }
            top++; 

            for (int i = top; i <= bottom; i++) {
                spiral.add(matrix[i][right]);
            }
            right--; 

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    spiral.add(matrix[bottom][i]);
                }
                bottom--; 
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    spiral.add(matrix[i][left]);
                }
                left++; 
            }
        }

        int[][] res = new int[r][c];
        int index = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = spiral.get(index++);
            }
        }

        return res;
    }
}