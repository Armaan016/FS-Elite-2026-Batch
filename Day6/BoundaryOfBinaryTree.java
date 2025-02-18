package Day6;
// The Indian Army has established multiple military camps at strategic locations 

// along the Line of Actual Control (LAC) in Galwan. These camps are connected in 
// a hierarchical structure, with a main base camp acting as the root of the network.

// To fortify national security, the Government of India has planned to deploy 
// a protective S.H.I.E.L.D. that encloses all military camps forming the outer 
// boundary of the network.

// Structure of Military Camps:
//     - Each military camp is uniquely identified by an integer ID.
//     - A camp can have at most two direct connections:
//         - Left connection → Represents a subordinate camp on the left.
//         - Right connection → Represents a subordinate camp on the right.
//     - If a military camp does not exist at a specific position, it is 
//       represented by -1

// Mission: Deploying the S.H.I.E.L.D.
// Your task is to determine the list of military camp IDs forming the outer 
// boundary of the military network. This boundary must be traversed in 
// anti-clockwise order, starting from the main base camp (root).

// The boundary consists of:
// 1. The main base camp (root).
// 2. The left boundary:
//     - Starts from the root’s left child and follows the leftmost path downwards.
//     - If a camp has a left connection, follow it.
//     - If no left connection exists but a right connection does, follow the right connection.
//     - The leftmost leaf camp is NOT included in this boundary.
// 3. The leaf camps (i.e., camps with no further connections), ordered from left to right.
// 4. The right boundary (in reverse order):
//     - Starts from the root’s right child and follows the rightmost path downwards.
//     - If a camp has a right connection, follow it.
//     - If no right connection exists but a left connection does, follow the left connection.
//     - The rightmost leaf camp is NOT included in this boundary.

// Input Format:
// -------------
// space separated integers, military-camp IDs.

// Output Format:
// --------------
// Print all the military-camp IDs, which are at the edge of S.H.I.E.L.D.

// Sample Input-1:
// ---------------
// 5 2 4 7 9 8 1

// Sample Output-1:
// ----------------
// [5, 2, 7, 9, 8, 1, 4]

// Sample Input-2:
// ---------------
// 11 2 13 4 25 6 -1 -1 -1 7 18 9 10

// Sample Output-2:
// ----------------
// [11, 2, 4, 7, 18, 9, 10, 6, 13]

// Sample Input-3:
// ---------------
// 1 2 3 -1 -1 -1 5 -1 6 7

// Sample Output-3:
// ----------------
// [1, 2, 7, 6, 5, 3]

import java.util.*;

public class BoundaryOfBinaryTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] line = sc.nextLine().split(" ");

        int n = line.length;

        int[] tree = new int[n];
        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(line[i]);
        }

        TreeNode root = buildTree(tree);

        System.out.println(boundaryOfTree(root));

        sc.close();
    }

    private static List<Integer> boundary;

    private static List<Integer> boundaryOfTree(TreeNode root) {
        boundary = new ArrayList<>();

        boundary.add(root.val);
        addLeft(root.left);

        addLeaves(root.left);
        addLeaves(root.right);

        addRight(root.right);

        return boundary;
    }

    private static void addLeft(TreeNode root) {
        if (root == null)
            return;
        if (root.left == null && root.right == null)
            return;

        boundary.add(root.val);
        if (root.left != null) {
            addLeft(root.left);
        } else if (root.left == null && root.right != null) {
            addLeft(root.right);
        }
    }

    private static void addLeaves(TreeNode root) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            boundary.add(root.val);
            return;
        }

        addLeaves(root.left);
        addLeaves(root.right);
    }

    private static void addRight(TreeNode root) {
        if (root == null)
            return;
        if (root.left == null && root.right == null)
            return;

        if (root.right != null) {
            addRight(root.right);
        } else if (root.right == null && root.left != null) {
            addRight(root.left);
        }

        boundary.add(root.val);
    }

    private static TreeNode buildTree(int[] tree) {
        int n = tree.length;
        TreeNode root = new TreeNode(tree[0]);

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

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