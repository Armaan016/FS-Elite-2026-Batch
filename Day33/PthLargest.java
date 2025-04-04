package Day33;

// There are n football players standing in the ground, coach wants to know the 
// P-th largest height of the players. Given an array of heights[] and the value of P. 
// Help the coach to find the P'th largest height.

// Note: You are supposed to print the P'th largest height in the sorted order of heights[].
//       Not the P'th distinct height.

// Input Format:
// -------------
// Line-1: Size of array n and P value(space separated)
// Line-2: Array elements of size n.

// Output Format:
// --------------
// Print P'th largest height.

// Sample input-1:
// ---------------
// 8 2
// 1 2 1 3 4 5 5 5

// Sample output-1:
// ----------------
// 5

// Sample input-2:
// ---------------
// 6 3
// 2 4 3 1 2 5

// Sample output-2:
// ----------------
// 3

import java.util.*;

public class PthLargest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int p = sc.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        findPthLargest(nums, p);

        sc.close();
    }

    private static void findPthLargest(int[] nums, int p) {
        Treap tr = new Treap();
        for (int num : nums) {
            tr.insert(num);
        }

        tr.inOrder(p);
    }
}

class Treap {
    Node root;
    int k;

    private Node rotateRight(Node p) {
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        return q;
    }

    private Node rotateLeft(Node p) {
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        return q;
    }

    private Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key > node.key) {
            node.left = insert(node.left, key);
            if (node.left.priority > node.priority) {
                node = rotateRight(node);
            }
        } else {
            node.right = insert(node.right, key);
            if (node.right.priority > node.priority) {
                node = rotateLeft(node);
            }
        }

        return node;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    public void inOrder(int p) {
        this.k = p;

        inOrder(root);
    }

    private void inOrder(Node root) {
        if (root == null)
            return;

        // System.out.println(root);
        inOrder(root.left);

        k--;

        if (k == 0) {
            System.out.println(root.key);
            return;
        }

        inOrder(root.right);
    }
}

class Node {
    int key;
    int priority;
    Node left, right;

    private static Random rand = new Random();

    Node(int key) {
        this.key = key;
        this.priority = rand.nextInt(100);
        this.left = null;
        this.right = null;
    }

    public String toString() {
        return "key: " + this.key + " priority: " + this.priority + "\n";
    }
}