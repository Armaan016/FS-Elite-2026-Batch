// Construct the binary tree from the given In-order and Pre-order. 
// Find Nodes Between Two Levels in Spiral Order.
// The spiral order is as follows:
// -> Odd levels → Left to Right.
// -> Even levels → Right to Left.

// Input Format:
// --------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the pre-order traversal.
// Two integers:
// Lower Level (L)
// Upper Level (U)

// Output Format:
// Print all nodes within the specified levels, but in spiral order.

// Example:
// Input:
// 7
// 4 2 5 1 6 3 7
// 1 2 4 5 3 6 7
// 2 3

// Output:
// 3 2 4 5 6 7

// Explanation:
// Binary tree structure:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

// Levels 2 to 3 in Regular Order:
// Level 2 → 2 3
// Level 3 → 4 5 6 7

// Spiral Order:
// Level 2 (Even) → 3 2 (Right to Left)
// Level 3 (Odd) → 4 5 6 7 (Left to Right)

import java.util.*;

public class BuildTreeFromPreOrderInOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] inOrder = new int[n];
        for (int i = 0; i < n; i++) {
            inOrder[i] = sc.nextInt();
        }

        int[] preOrder = new int[n];
        for (int i = 0; i < n; i++) {
            preOrder[i] = sc.nextInt();
        }

        int l = sc.nextInt();
        int u = sc.nextInt();

        displayTree(n, inOrder, preOrder, l, u);

        sc.close();
    }

    private static void displayTree(int n, int[] inOrder, int[] preOrder, int l, int u) {
        int[] preIdx = new int[] { 0 };
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(inOrder[i], i);
        }

        TreeNode root = buildTree(inOrder, preOrder, 0, n - 1, preIdx, map);

        Map<Integer, List<Integer>> tree = buildLevelOrder(root);
        List<Integer> res = new ArrayList<>();
        for (int i = l; i <= u; i++) {
            res.addAll(tree.get(i));
        }

        System.out.println(res);
    }

    private static TreeNode buildTree(int[] inOrder, int[] preOrder, int l, int r, int[] preIdx,
            Map<Integer, Integer> map) {
        if (l > r)
            return null;

        TreeNode root = new TreeNode(preOrder[preIdx[0]++]);
        int idx = map.get(root.val);

        root.left = buildTree(inOrder, preOrder, l, idx - 1, preIdx, map);
        root.right = buildTree(inOrder, preOrder, idx + 1, r, preIdx, map);

        return root;
    }

    private static Map<Integer, List<Integer>> buildLevelOrder(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        int lvl = 1;
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

            if (lvl % 2 == 0) {
                Collections.reverse(curr);
                map.put(lvl, curr);
            } else {
                map.put(lvl, curr);
            }

            lvl++;
        }

        return map;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}