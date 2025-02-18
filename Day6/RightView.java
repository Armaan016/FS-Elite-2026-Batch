package Day6;
// Balbir Singh is working with Binary Trees.

// The elements of the tree are given in level-order format.

// Balbir is observing the tree from the right side, meaning he 
// can only see the rightmost nodes (one node per level).

// You are given the root of a binary tree. Your task is to determine 
// the nodes visible from the right side and return them in top-to-bottom order.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the node values visible from the right side

// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 3, 5]

// Sample Input-2:
// ---------------
// 3 2 4 3 2

// Sample Output-2:
// ----------------
// [3, 4, 2]

import java.util.*;

public class RightView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] line = sc.nextLine().split(" ");

        int n = line.length;
        int[] tree = new int[n];
        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(line[i]);
        }

        TreeNode root = buildTree(tree);

        System.out.println(rightSideView(root));

        sc.close();
    }

    private static List<Integer> rightSideView(TreeNode root) {
        List<Integer> view = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            int right = -1;

            while (size-- > 0) {
                TreeNode curr = q.poll();

                right = curr.val;

                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            }

            view.add(right);
        }

        return view;
    }

    private static TreeNode buildTree(int[] tree) {
        int n = tree.length;
        Queue<TreeNode> q = new LinkedList<>();

        TreeNode root = new TreeNode(tree[0]);
        q.add(root);

        int i = 1;
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (i < n && tree[i] != -1) {
                curr.left = new TreeNode(tree[i]);
                q.offer(curr.left);
            }
            i++;

            if (i < n && tree[i] != -1) {
                curr.right = new TreeNode(tree[i]);
                q.offer(curr.right);
            }
            i++;
        }

        return root;
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