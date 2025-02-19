package Day9;

/*Balbir Singh is working with Binary Trees.
The elements of the tree is given in the level order format.
Balbir has a task to split the tree into two parts by removing only one edge
in the tree, such that the product of sums of both the splitted-trees should be maximum.

You will be given the root of the binary tree.
Your task is to help the Balbir Singh to split the binary tree as specified.
print the product value, as the product may be large, print the (product % 1000000007)
	
NOTE: 
Please do consider the node with data as '-1' as null in the given trees.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6

Sample Output-1:
----------------
110

Explanation:
------------
if you split the tree by removing edge between 1 and 4, 
then the sums of two trees are 11 and 10. So, the max product is 110.


Sample Input-2:
---------------
3 2 4 3 2 -1 6

Sample Output-2:
----------------
100
*/

import java.util.*;

public class MaximumProductAfterSplit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int[] tree = new int[line.length];

        for (int i = 0; i < line.length; i++) {
            tree[i] = Integer.parseInt(line[i]);
        }

        Node root = buildTree(tree);
        System.out.println(maximumProduct(root));

        sc.close();
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

    private static long maxProd;

    private static int maximumProduct(Node root) {
        maxProd = 0;

        int totalSum = calculateSum(root);
        helper(root, totalSum);

        return (int) (maxProd % 1000000007);
    }

    private static int calculateSum(Node root) {
        if (root == null)
            return 0;
        return root.val + calculateSum(root.left) + calculateSum(root.right);
    }

    private static long helper(Node root, int totalSum) {
        if (root == null)
            return 0;

        long curr = root.val + helper(root.left, totalSum) + helper(root.right, totalSum);
        long rem = totalSum - curr;

        maxProd = Math.max(maxProd, curr * rem);

        return curr;
    }
}

class Node {
    int val;
    Node left, right;

    Node(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}