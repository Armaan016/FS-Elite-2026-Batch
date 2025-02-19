package Day9;

/*  Imagine you are a librarian organizing books on vertical shelves in a grand 
library. The books are currently scattered across a tree-like structure, where 
each book (node) has a position determined by its shelf number (column) and row 
number (level).

Your task is to arrange the books on shelves so that:
1. Books are placed column by column from left to right.
2. Within the same column, books are arranged from top to bottom (i.e., by row).
3. If multiple books belong to the same shelf and row, they should be arranged 
from left to right, just as they appear in the original scattered arrangement.

Sample Input:
-------------
3 9 20 -1 -1 15 7

Sample Output:
--------------
[[9],[3,15],[20],[7]]

Explanation:
------------
         3
       /   \
      9     20
          /    \
         15     7

Shelf 1: [9]
Shelf 2: [3, 15]
Shelf 3: [20]
Shelf 4: [7]


Sample Input-2:
---------------
3 9 8 4 0 1 7

Sample Output-2:
----------------
[[4],[9],[3,0,1],[8],[7]]

Explanation:
------------

          3
       /     \
      9       8
    /   \   /   \
   4     0 1     7

Shelf 1: [4]
Shelf 2: [9]
Shelf 3: [3, 0, 1]
Shelf 4: [8]
Shelf 5: [7]
*/

import java.util.*;

public class VerticalOrderTraversal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int[] tree = new int[line.length];

        for (int i = 0; i < line.length; i++) {
            tree[i] = Integer.parseInt(line[i]);
        }

        Node root = buildTree(tree);
        List<List<Integer>> result = verticalOrder(root);
        System.out.println(result);

        sc.close();
    }

    private static List<List<Integer>> verticalOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Map<Integer, List<Integer>> cols = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair curr = q.poll();

            cols.putIfAbsent(curr.col, new ArrayList<>());
            cols.get(curr.col).add(curr.node.val);

            if (curr.node.left != null) {
                q.offer(new Pair(curr.node.left, curr.col - 1));
            }

            if (curr.node.right != null) {
                q.offer(new Pair(curr.node.right, curr.col + 1));
            }
        }

        for (int col : cols.keySet()) {
            res.add(cols.get(col));
        }

        return res;
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
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
    }
}

class Pair {
    Node node;
    int col;

    Pair(Node node, int col) {
        this.node = node;
        this.col = col;
    }
}