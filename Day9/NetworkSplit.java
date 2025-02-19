package Day9;
// Balbir Singh is working with networked systems, where servers are connected 

// in a hierarchical structure, represented as a Binary Tree. Each server (node) has 
// a certain processing load (integer value).

// Balbir has been given a critical task: split the network into two balanced 
// sub-networks by removing only one connection (edge). The total processing 
// load in both resulting sub-networks must be equal after the split.

// Network Structure:
// - The network of servers follows a binary tree structure.
// - Each server is represented by an integer value, indicating its processing load.
// - If a server does not exist at a particular position, it is represented as '-1' (NULL).

// Help Balbir Singh determine if it is possible to split the network into two equal 
// processing load sub-networks by removing exactly one connection.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print a boolean value.

// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// true

// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 7

// Sample Output-2:
// ----------------
// false

import java.util.*;

public class NetworkSplit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int[] tree = new int[line.length];

        for (int i = 0; i < line.length; i++) {
            tree[i] = Integer.parseInt(line[i]);
        }

        Node root = buildTree(tree);
        System.out.println(canSplit(root));

        sc.close();
    }

    private static boolean canSplit(Node root) {
        if (root == null)
            return false;

        int totalSum = getTreeSum(root);

        if (totalSum % 2 != 0)
            return false;

        boolean[] found = new boolean[1];
        checkSubtreeSum(root, totalSum / 2, found);

        return found[0];
    }

    private static int checkSubtreeSum(Node root, int target, boolean[] found) {
        if (root == null)
            return 0;

        int sum = root.val + checkSubtreeSum(root.left, target, found) + checkSubtreeSum(root.right, target, found);

        if (sum == target)
            found[0] = true;

        return sum;
    }

    private static int getTreeSum(Node root) {
        if (root == null)
            return 0;
        return root.val + getTreeSum(root.left) + getTreeSum(root.right);
    }

    private static Node buildTree(int[] tree) {
        Node root = new Node(tree[0]);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (!q.isEmpty() && i < tree.length) {
            Node curr = q.poll();

            if (i < tree.length && tree[i] != -1) {
                curr.left = new Node(tree[i]);
                q.offer(curr.left);
            }
            i++;

            if (i < tree.length && tree[i] != -1) {
                curr.right = new Node(tree[i]);
                q.offer(curr.right);
            }
            i++;
        }
        return root;
    }
}

class Node {
    int val;
    Node left, right;

    Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}