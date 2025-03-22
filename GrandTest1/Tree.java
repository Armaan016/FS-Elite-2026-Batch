package GrandTest1;

// Given a tree, find the number of elements where the value of the node is the sum of the values of and successors below it.

public class Tree {
    public static void main(String[] args) {
        Node root = new Node(11);
        root.left = new Node(3);
        root.right = new Node(5);
        root.left.left = new Node(2);
        root.left.right = new Node(1);

        System.out.println(countNodes(root));
    }

    public static int countNodes(Node root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return (root.val == 0) ? 1 : 0;

        int left = sum(root.left);
        int right = sum(root.right);

        if (root.val == left + right)
            return 1 + countNodes(root.left) + countNodes(root.right);
        return countNodes(root.left) + countNodes(root.right);
    }

    public static int sum(Node root) {
        if (root == null)
            return 0;
        return root.val + sum(root.left) + sum(root.right);

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