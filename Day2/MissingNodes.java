/*Write a program to construct a binary tree from level-order input, while treating -1 
as a placeholder for missing nodes. The program reads input, constructs the tree, 
and provides an in-order traversal to verify correctness.

Input Format:
---------------
Space separated integers, level order data (where -1 indiactes null node).

Output Format:
-----------------
Print the in-order data of the tree.


Sample Input:
----------------
1 2 3 -1 -1 4 5

Sample Output:
----------------
2 1 4 3 5

Explanation:
--------------
    1
   / \
  2   3
     / \
    4   5


Sample Input:
----------------
1 2 3 4 5 6 7

Sample Output:
----------------
4 2 5 1 6 3 7

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4  5 6  7
*/

import java.util.*;

public class MissingNodes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] line = sc.nextLine().split(" ");
        int n = line.length;

        int[] tree = new int[n];
        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(line[i]);
        }

        TreeNode root = buildTree(tree, n);
        inOrder(root);

        sc.close();
    }

    private static TreeNode buildTree(int[] tree, int n) {
        TreeNode root = new TreeNode(tree[0]);

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (i < n) {
            TreeNode popped = q.poll();

            if (i < n && tree[i] != -1) {
                popped.left = new TreeNode(tree[i]);
                q.offer(popped.left);
            }
            i++;

            if (i < n && tree[i] != -1) {
                popped.right = new TreeNode(tree[i]);
                q.offer(popped.right);
            }
            i++;
        }

        return root;
    }

    private static void inOrder(TreeNode root) {
        if (root == null)
            return;

        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}