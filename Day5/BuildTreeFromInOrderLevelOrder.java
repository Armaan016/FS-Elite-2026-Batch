// Construct Tree from the given Level-order and In-order.
// Compute the Depth and Sum of the Deepest nodes in the Binary tree

// Input Format:
// -------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the level-order traversal.

// Output Format:
// --------------
// Print two values:
// ->The maximum number of levels.
// ->The sum of all node values at the deepest level.

// Example:
// -------------
// Input:
// 11
// 7 8 4 2 5 9 11 10 1 6 3 
// 1 2 3 4 5 6 7 9 8 10 11

// Output:
// 6 11

// Explanation:
// The binary tree structure:
//            1
//          /   \
//        2       3
//       / \     /
//      4   5   6
//     /     \   
//    7       9
//     \       \
//      8      10
//             /
//           11
// Maximum Depth: 6
// nodes at the Deepest Level (6): 11
// Sum of nodes at Level 6: 11

import java.util.*;

public class BuildTreeFromInOrderLevelOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] inOrder = new int[n];
        for (int i = 0; i < n; i++) {
            inOrder[i] = sc.nextInt();
        }

        int[] levelOrder = new int[n];
        for (int i = 0; i < n; i++) {
            levelOrder[i] = sc.nextInt();
        }

        maxDepthAndSum(n, inOrder, levelOrder);

        sc.close();
    }

    private static void maxDepthAndSum(int n, int[] inOrder, int[] levelOrder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inOrderMap.put(inOrder[i], i);
        }

        TreeNode root = buildTree(inOrder, 0, n - 1, levelOrder, inOrderMap);

        // inOrder(root);
        // System.out.println();

        int[] result = findMaxDepthAndSum(root);
        System.out.println(result[0] + " " + result[1]);
    }

    private static TreeNode buildTree(int[] inOrder, int left, int right, int[] levelOrder,
            Map<Integer, Integer> inOrderMap) {
        if (left > right)
            return null;

        int rootVal = -1;
        for (int num : levelOrder) {
            if (inOrderMap.get(num) >= left && inOrderMap.get(num) <= right) {
                rootVal = num;
                break;
            }
        }

        if (rootVal == -1)
            return null;

        TreeNode root = new TreeNode(rootVal);
        int rootIdx = inOrderMap.get(rootVal);
        // System.out.println("Root: " + rootVal + " rootIdx: " + rootIdx);

        root.left = buildTree(inOrder, left, rootIdx - 1, levelOrder, inOrderMap);
        root.right = buildTree(inOrder, rootIdx + 1, right, levelOrder, inOrderMap);

        return root;
    }

    private static int[] findMaxDepthAndSum(TreeNode root) {
        if (root == null)
            return new int[] { 0, 0 };

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0, sum = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0;
            depth++;

            while (size-- > 0) {
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }

        return new int[] { depth, sum };
    }

    // private static void inOrder(TreeNode root) {
    // if (root == null)
    // return;

    // inOrder(root.left);
    // System.out.print(root.val + " ");
    // inOrder(root.right);
    // }
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
