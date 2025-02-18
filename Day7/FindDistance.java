package Day7;
// Bubloo is working with computer networks, where servers are connected

// in a hierarchical structure, represented as a Binary Tree. Each server (node)
// is uniquely identified by an integer value.

// Bubloo has been assigned an important task: find the shortest communication
// path (in terms of network hops) between two specific servers in the network.

// Network Structure:
// ------------------
// The network of servers follows a binary tree topology.
// Each server (node) has a unique identifier (integer).
// If a server does not exist at a certain position, it is represented as '-1'
// (NULL).

// Given the root of the network tree, and two specific server IDs (E1 & E2),
// determine the minimum number of network hops (edges) required to
// communicate between these two servers.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.

// Sample Input-1:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 4 8

// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]

// Sample Input-2:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 6 6

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// No edegs between 6 and 6.

import java.util.*;

public class FindDistance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] line = sc.nextLine().split(" ");

        int n = line.length;
        int[] tree = new int[n];

        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(line[i]);
        }

        int a = sc.nextInt();
        int b = sc.nextInt();

        Node root = buildTree(tree);

        // inOrder(root);

        Node lca = findLCA(root, a, b);

        // System.out.println(lca.val);

        int first = findDepth(lca, a, 0);
        int second = findDepth(lca, b, 0);

        // System.out.println("first: " + first + " second: " + second);
        System.out.println(first + second);

        sc.close();
    }

    private static Node findLCA(Node root, int a, int b) {
        if (root == null || root.val == a || root.val == b)
            return root;

        Node left = findLCA(root.left, a, b);
        Node right = findLCA(root.right, a, b);

        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }

    private static int findDepth(Node root, int a, int lvl) {
        if (root == null)
            return -1;
        if (root.val == a)
            return lvl;

        int left = findDepth(root.left, a, lvl + 1);
        int right = findDepth(root.right, a, lvl + 1);

        return left != -1 ? left : right;
    }

    private static Node buildTree(int[] tree) {
        int n = tree.length;
        Node root = new Node(tree[0]);

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (i < n && tree[i] != -1) {
                curr.left = new Node(tree[i]);
                q.offer(curr.left);
            }
            i++;

            if (i < n && tree[i] != -1) {
                curr.right = new Node(tree[i]);
                q.offer(curr.right);
            }
            i++;
        }

        return root;
    }

    // private static void inOrder(Node root) {
    // if(root == null) return;

    // inOrder(root.left);
    // System.out.print(root.val + " ");
    // inOrder(root.right);
    // }
}

class Node {
    int val;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}