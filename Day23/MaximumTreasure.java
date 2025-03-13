// Imagine you are designing a grand castle where each room holds a specific amount 
// of treasure. The castle is built in a binary layout, meaning every room may lead 
// to two adjacent wings—a left wing and a right wing. 

// An "organized section" of the castle follows this rule: for any given room, 
// every room in its left wing contains a treasure value that is strictly less 
// than the current room’s value, and every room in its right wing contains a 
// value that is strictly greater. Additionally, each wing must itself be organized
// according to the same rule.

// Your challenge is to determine the maximum total treasure (i.e., the sum of 
// treasure values) that can be gathered from any such organized section of the castle.

// Example 1:
// input=
// 1 4 3 2 4 2 5 -1 -1 -1 -1 -1 -1 4 6
// output=
// 20

// Castle:
//           1
//         /   \
//        4     3
//       / \   / \
//      2   4 2   5
//               / \
//              4   6

// Explanation: The best organized section starts at the room with a treasure value 
// of 3, yielding a total treasure of 20.

// Example 2:
// input=
// 4 3 -1 1 2
// output=
// 2

// Castle:
//     4
//    /
//   3
//  / \
// 1   2

// Explanation: The optimal organized section is just the single room with a 
// treasure value of 2.

// Example 3:
// input=
// -4 -2 -5
// output=
// 0

// Castle:
//    -4
//   /  \
// -2   -5

// Explanation: Since all rooms contain negative treasure values, no beneficial 
// organized section exists, so the maximum total treasure is 0.

// Constraints:

// - The number of rooms in the castle ranges from 1 to 40,000.
// - Each room’s treasure value is an integer between -40,000 and 40,000.

import java.util.*;
import java.util.LinkedList;

public class MaximumTreasure {

    private static int maxTreasure;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] tree = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Node root = buildTree(tree);

        computeBSTValues(root);
        findMaxTreasure(root);

        System.out.println(maxTreasure < 0 ? 0 : maxTreasure);
        sc.close();
    }

    private static void findMaxTreasure(Node root) {
        if (root == null)
            return;

        if (root.isBSTSum != Integer.MIN_VALUE) {
            maxTreasure = Math.max(maxTreasure, root.isBSTSum);
        }

        findMaxTreasure(root.left);
        findMaxTreasure(root.right);
    }

    private static int validateAndSumBST(Node root, List<Integer> values) {
        if (root == null)
            return 0;

        validateAndSumBST(root.left, values);
        values.add(root.val);
        validateAndSumBST(root.right, values);

        int sum = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) <= values.get(i - 1)) {
                return Integer.MIN_VALUE;
            }
            sum += values.get(i);
        }
        return sum;
    }

    private static void computeBSTValues(Node root) {
        if (root == null)
            return;

        computeBSTValues(root.left);
        computeBSTValues(root.right);

        List<Integer> values = new ArrayList<>();
        root.isBSTSum = validateAndSumBST(root, values);
    }

    private static Node buildTree(int[] tree) {
        if (tree.length == 0 || tree[0] == -1)
            return null;

        Queue<Node> q = new LinkedList<>();
        Node root = new Node(tree[0]);
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
    int isBSTSum;

    Node(int val) {
        this.val = val;
        this.isBSTSum = Integer.MIN_VALUE;
    }
}
