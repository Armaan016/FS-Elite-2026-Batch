package Day12;
// Imagine you’re decoding a secret message that outlines the hierarchical structure 

// of a covert spy network. The message is a string composed of numbers and parentheses. 
// Here’s how the code works:

// - The string always starts with an agent’s identification number, this is the 
//   leader of the network.
// - After the leader’s ID, there can be zero, one, or two segments enclosed in 
//   parentheses. Each segment represents the complete structure of one subordinate 
//   network.
// - If two subordinate networks are present, the one enclosed in the first (leftmost) 
//   pair of parentheses represents the left branch, and the second (rightmost) 
//   represents the right branch.

// Your mission is to reconstruct the entire spy network hierarchy based on this 
// coded message.

// Example 1:
// Input: 4(2(3)(1))(6(5))
// Output: [4, 2, 6, 3, 1, 5]

// Spy network:
//         4
//        / \
//       2   6
//      / \  /
//     3   1 5

// Explanation:
// Agent 4 is the leader.
// Agent 2 (with its own subordinates 3 and 1) is the left branch.
// Agent 6 (with subordinate 5) is the right branch.

// Example 2:
// Input: 4(2(3)(1))(6(5)(7))
// Output: [4, 2, 6, 3, 1, 5, 7]

// Spy network:
//          4
//        /   \
//       2     6
//      / \   / \
//     3   1 5   7

// Explanation:
// Agent 4 leads the network.
// Agent 2 with subordinates 3 and 1 forms the left branch.
// Agent 6 with subordinates 5 and 7 forms the right branch.

import java.util.*;

public class SpyNetwork {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String secret = sc.nextLine();

        decodeSecret(secret);

        sc.close();
    }

    private static void decodeSecret(String s) {
        idx = 0;
        Node root = helper(s);

        levelOrder(root);
    }

    private static int idx;

    private static Node helper(String s) {
        if (idx >= s.length())
            return null;

        int mul = 1;
        if (s.charAt(idx) == '-') {
            mul = -1;
            idx++;
        }

        int num = 0;
        while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
            num = num * 10 + (s.charAt(idx) - '0');
            idx++;
        }

        // System.out.println("idx: " + idx);
        Node root = new Node(num * mul);

        if (idx < s.length() && s.charAt(idx) == '(') {
            idx++;
            root.left = helper(s);
            idx++;
        }

        if (idx < s.length() && s.charAt(idx) == '(') {
            idx++;
            root.right = helper(s);
            idx++;
        }

        return root;
    }

    private static void levelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            Node curr = q.poll();
            // System.out.print(curr.val + " ");
            res.add(curr.val);

            if (curr.left != null)
                q.offer(curr.left);
            if (curr.right != null)
                q.offer(curr.right);
        }

        System.out.println(res);
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