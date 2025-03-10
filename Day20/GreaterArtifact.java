// Imagine you're the chief curator at a renowned museum that houses a rare
// collection
// of ancient artifacts. These artifacts are arranged in a special display that
// follows a strict rule: any artifact positioned to the left of another has a
// lower
// value, and any artifact on the right has a higher value.

// Your task is to transform this display into what is known as a "Greater
// Artifact
// Display." In this new arrangement, each artifact’s new value will be its
// original
// value plus the sum of the values of all artifacts that are more valuable than
// it.

// Example 1:
// ----------
// input=
// 4 2 6 1 3 5 7
// output=
// 22 27 13 28 25 18 7

// Explanation:
// Input structure
// 4
// / \
// 2 6
// / \ / \
// 1 3 5 7

// Output structure:
// 22
// / \
// 27 13
// / \ / \
// 28 25 18 7

// Reverse updates:
// - Artifact 7: 7
// - Artifact 6: 6 + 7 = 13
// - Artifact 5: 5 + 13 = 18
// - Artifact 4: 4 + 18 = 22
// - Artifact 3: 3 + 22 = 25
// - Artifact 2: 2 + 25 = 27
// - Artifact 1: 1 + 27 = 28

import java.util.*;
import java.util.LinkedList;

public class GreaterArtifact {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tree = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

        Node root = buildTree(tree);

        sum = 0;
        processBST(root);

        levelOrder(root);

        sc.close();
    }

    private static int sum;

    private static void processBST(Node root) {
        if (root == null)
            return;

        processBST(root.right);

        root.val += sum;
        sum = root.val;

        processBST(root.left);
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