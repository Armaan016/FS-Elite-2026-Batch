package Day33;

// In a magical kingdom, each wizard carries a certain number of enchanted crystals. 
// A pair of wizards is said to have a "dominant wizard" if the first wizard, who 
// \arrived earlier at the royal gathering, possesses more than twice as many 
// crystals as the second wizard, who arrived later.

// Given an list of crystals, representing the number of enchanted crystals carried 
// by each wizard in their order of arrival at the gathering, calculate the number 
// of "dominant wizard" pairs.
// A pair of wizards (x, y) is considered dominant if:

// - 0 ≤ x < y < crystals.length and
// - crystals[x] > 2 × crystals[y]

// Example 1:
// Input: 
// 1 3 2 3 1
// Output: 2

// Explanation: The dominant wizard pairs are:
// - Wizard 1 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1
// - Wizard 3 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1

// Example 2:
// Input:
// 2 4 3 5 1
// Output: 3

// Explanation: The dominant wizard pairs are:
// - Wizard 1 (4 crystals) and Wizard 4 (1 crystal), since 4 > 2 × 1
// - Wizard 2 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1
// - Wizard 3 (5 crystals) and Wizard 4 (1 crystal), since 5 > 2 × 1

// Constraints:
// - 1 ≤ crystals.length ≤ 5 × 10^4
// - -2^31 ≤ crystals[i] ≤ 2^31 - 1

import java.util.*;

public class DominantWizards {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

        System.out.println(countWizards(nums));

        sc.close();
    }

    private static int countWizards(int[] nums) {
        Treap tr = new Treap();
        int count = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            count += tr.countDominant(nums[i] / 2.0);
            tr.insert(nums[i]);
        }

        return count;
    }
}

class Node {
    int key, priority;
    Node left, right;
    int count;

    Node(int key) {
        this.key = key;
        this.priority = new Random().nextInt();
        this.count = 1;
    }
}

class Treap {
    Node root;

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

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key == node.key) {
            node.count++;
        } else if (key < node.key) {
            node.left = insert(node.left, key);
            if (node.left.priority > node.priority)
                node = rotateRight(node);
        } else {
            node.right = insert(node.right, key);
            if (node.right.priority > node.priority)
                node = rotateLeft(node);
        }

        return node;
    }

    public int countDominant(double val) {
        return countDominant(root, val);
    }

    private int countDominant(Node node, double val) {
        if (node == null)
            return 0;

        if (node.key < val) {
            return node.count + countAll(node.left) + countDominant(node.right, val);
        } else {
            return countDominant(node.left, val);
        }
    }

    private int countAll(Node node) {
        if (node == null)
            return 0;
        return node.count + countAll(node.left) + countAll(node.right);
    }
}