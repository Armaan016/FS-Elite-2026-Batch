package Day13;
// Imagine you are navigating a maze where each path you take has a section with a 

// code. The maze is structured as a series of interconnected rooms, 
// represented as a tree structure. Each room in the maze has a code (integral value)
// associated with it, and you are trying to check if a given sequence of code 
// matches a valid path from the entrance to an exit. 

// You are provide with the maze structure, where you have to build the maze and then,
// you are provided with a series of space seperated digits, representing a journey 
// starting from the entrance and passing through the rooms along the way. 
// The task is to verify whether the path corresponding to this array of codes 
// exists in the maze.

// Example 1:
// Input:
// 0 1 0 0 1 0 -1 -1 1 0 0         //maze structure
// 0 1 0 1                         //path to verify

// Output: true

// Explanation:
//                0
//              /   \
//             1     0
//            / \    /
//           0   1  0
//            \  / \
//             1 0  0

// The given path 0 → 1 → 0 → 1 is a valid path in the maze. 
// Other valid sequences in the maze include 0 → 1 → 1 → 0 and 0 → 0 → 0.

// Example 2:
// Input:
// 1 2 3
// 1 2 3

// output: false

// Explanation:
// The proposed path 1 → 2 → 3 does not exist in the maze, 
// so it cannot be a valid path.

import java.util.*;

public class ValidPath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tree = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
        int[] p = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

        List<Integer> path = new ArrayList<>();
        for (int x : p) {
            path.add(x);
        }

        Node root = buildTree(tree);

        System.out.println(checkPath(root, path));

        sc.close();
    }

    private static boolean checkPath(Node root, List<Integer> path) {
        List<Integer> curr = new ArrayList<>();
        return helper(root, path, curr);
    }

    private static boolean helper(Node root, List<Integer> path, List<Integer> curr) {
        if (curr.size() == path.size()) {
            if (equals(path, curr))
                return true;
        }

        if (root == null)
            return false;

        curr.add(root.val);
        // System.out.println("Adding root: " + root.val + " curr: " + curr);
        boolean ans = helper(root.left, path, curr) || helper(root.right, path, curr);
        curr.remove(curr.size() - 1);

        return ans;
    }

    private static boolean equals(List<Integer> a, List<Integer> b) {
        // System.out.println("Checking: " + a + ", " + b);
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != b.get(i))
                return false;
        }

        return true;
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