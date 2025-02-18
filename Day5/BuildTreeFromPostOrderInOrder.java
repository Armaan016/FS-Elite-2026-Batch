// 7
// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. For the given Q number of queries, 
// each query consists of a lower level and an upper level. 
// The output should list the nodes in the order they appear in a level-wise.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers representing the similar to in-order traversal.
// A space-separated list of N integers representing the similar to post-order traversal.
// An integer Q representing the number of queries.
// Q pairs of integers, each representing a query in the form:
// Lower level (L)
// Upper level (U)

// Output Format:
// For each query, print the nodes in order within the given depth range

// Example
// Input:
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1
// 2
// 1 2
// 2 3
// Output:
// [1, 2, 3]
// [2, 3, 4, 5, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// Query 1 (Levels 1 to 2): 1 2 3
// Query 2 (Levels 2 to 3): 2 3 4 5 6 7

import java.util.*;

public class BuildTreeFromPostOrderInOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] inOrder = new int[n];
        for (int i = 0; i < n; i++) {
            inOrder[i] = sc.nextInt();
        }

        int[] postOrder = new int[n];
        for (int i = 0; i < n; i++) {
            postOrder[i] = sc.nextInt();
        }

        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            int l = sc.nextInt();
            int u = sc.nextInt();
            queries[i] = new int[] { l, u };
        }

        getQueryResults(n, inOrder, postOrder, q, queries);

        sc.close();
    }

    private static void getQueryResults(int n, int[] inOrder, int[] postOrder, int q, int[][] queries) {
        int postIdx[] = new int[] { n - 1 };
        Map<Integer, Integer> inOrderMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            inOrderMap.put(inOrder[i], i);
        }

        TreeNode root = buildTree(inOrder, postOrder, 0, n - 1, inOrderMap, postIdx);

        Map<Integer, List<Integer>> tree = buildLevelOrder(root, n);
        for (int[] query : queries) {
            int l = query[0];
            int u = query[1];

            List<Integer> res = new ArrayList<>();
            for (int i = l; i <= u; i++) {
                if (tree.containsKey(i)) {
                    res.addAll(tree.get(i));
                }
            }

            System.out.println(res);
        }
    }

    private static Map<Integer, List<Integer>> buildLevelOrder(TreeNode root, int n) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int level = 1;
        while (!q.isEmpty()) {
            List<Integer> curr = new ArrayList<>();
            int size = q.size();

            while (size-- > 0) {
                TreeNode node = q.poll();
                curr.add(node.val);

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }

            tree.put(level++, curr);
        }

        return tree;
    }

    private static TreeNode buildTree(int[] inOrder, int[] postOrder, int l, int r, Map<Integer, Integer> inOrderMap,
            int[] postIdx) {
        if (l > r)
            return null;

        // System.out.println("l: " + l + " r: " + r + " postIdx: " + postIdx);
        TreeNode root = new TreeNode(postOrder[postIdx[0]--]);
        int idx = inOrderMap.get(root.val);

        root.right = buildTree(inOrder, postOrder, idx + 1, r, inOrderMap, postIdx);
        root.left = buildTree(inOrder, postOrder, l, idx - 1, inOrderMap, postIdx);

        return root;
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