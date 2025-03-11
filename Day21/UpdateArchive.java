// Imagine you're the curator of an ancient manuscript archive. Each manuscript is
// assigned a unique significance score, and the archive is arranged so that every 
// manuscript on the left has a lower score and every manuscript on the right has a
// higher score, forming a special ordered display. Now, for an upcoming exhibition,
// scholars have decided that only manuscripts with significance scores between low 
// and high (inclusive) are relevant. Your task is to update the archive by removing
// any manuscripts whose scores fall outside the range [low, high]. Importantly, 
// while you remove some manuscripts, you must preserve the relative order of the 
// remaining ones—if a manuscript was originally displayed as a descendant of another, 
// that relationship should remain intact. It can be proven that there is a unique 
// way to update the archive.

// Display the manuscript of the updated archive. Note that the main manuscript 
// (the root) may change depending on the given score boundaries.

// Input format:
// Line 1: space separated scores to build the manuscript archive
// Line 2: two space seperated integers, low and high.

// Output format:
// space separated scores of the updated archive

// Example 1:
// input=
// 1 0 2
// 1 2
// output=
// 1 2

// Explanation:
// Initial archieve:
//       1
//      / \
//     0   2

// Updated archieve:
//     1
//      \
//       2
// After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1 
// and its right manuscript 2 remain.

// Example 2:
// input=
// 3 0 4 2 1
// 1 3
// output=
// 3 2 1

// Explanation:
// Initial archieve:
//           3
//          / \
//         0   4
//          \
//           2
//          /
//         1

// Updated archieve:
//       3
//      /
//     2
//    /
//   1

import java.util.*;
import java.util.LinkedList;

public class UpdateArchive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tree = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt((x))).toArray();
        int l = sc.nextInt(), h = sc.nextInt();

        Node root = buildBST(tree);

        Node newR = updateTree(root, l, h);

        levelOrder(newR);

        sc.close();
    }

    private static Node updateTree(Node root, int l, int h) {
        if (root == null)
            return null;

        Node left = updateTree(root.left, l, h);
        Node right = updateTree(root.right, l, h);

        if (root.val >= l && root.val <= h) {
            root.left = left;
            root.right = right;

            return root;
        }

        if (root.val < l)
            return right;
        else
            return left;
    }

    private static Node buildBST(int[] tree) {
        Node root = new Node(tree[0]);

        for (int i = 1; i < tree.length; i++) {
            helper(root, tree[i]);
        }

        return root;
    }

    private static Node prev;

    private static void helper(Node root, int val) {
        if (root == null)
            return;

        prev = null;
        if (val > root.val) {
            prev = root;
            helper(root.right, val);
        } else {
            prev = root;
            helper(root.left, val);
        }

        if (val > prev.val) {
            prev.right = new Node(val);
        } else {
            prev.left = new Node(val);
        }
    }

    private static void levelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();
            System.out.print(curr.val + " ");

            if (curr.left != null)
                q.offer(curr.left);
            if (curr.right != null)
                q.offer(curr.right);
        }
    }
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