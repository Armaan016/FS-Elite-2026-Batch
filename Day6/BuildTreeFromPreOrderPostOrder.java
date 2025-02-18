package Day6;
// Given the preorder and postorder traversals of a binary tree, construct 

// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.

// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3

import java.util.*;

public class BuildTreeFromPreOrderPostOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] line = sc.nextLine().split(" ");
        int n = line.length;

        int[] preOrder = new int[n];
        for (int i = 0; i < n; i++) {
            preOrder[i] = Integer.parseInt(line[i]);
        }

        int[] postOrder = new int[n];
        for (int i = 0; i < n; i++) {
            postOrder[i] = sc.nextInt();
        }

        helper(preOrder, postOrder, n);

        sc.close();
    }

    private static void helper(int[] preOrder, int[] postOrder, int n) {
        int[] preIdx = new int[] { 0 };

        Map<Integer, Integer> postOrderMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            postOrderMap.put(postOrder[i], i);
        }

        TreeNode root = buildTree(preOrder, postOrder, preIdx, postOrderMap, 0, n - 1);

        System.out.println(lvlOrder(root));

        // 1 2 4 5 3 6 7
        // 4 5 2 6 7 3 1
    }

    private static TreeNode buildTree(int[] preOrder, int[] postOrder, int[] preIdx, Map<Integer, Integer> postOrderMap,
            int l, int r) {
        if (preIdx[0] >= preOrder.length || l > r)
            return null;

        TreeNode root = new TreeNode(preOrder[preIdx[0]++]);

        System.out.println("l: " + l + " r: " + r + " preIdx: " + preIdx[0]);
        if (l == r)
            return root;

        int left = preOrder[preIdx[0]];
        int idx = postOrderMap.get(left);

        root.left = buildTree(preOrder, postOrder, preIdx, postOrderMap, l, idx);
        root.right = buildTree(preOrder, postOrder, preIdx, postOrderMap, idx + 1, r - 1);

        return root;
    }

    private static List<List<Integer>> lvlOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> curr = new ArrayList<>();

            while (size-- > 0) {
                TreeNode node = q.poll();
                curr.add(node.val);

                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }

            res.add(curr);
        }

        return res;
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