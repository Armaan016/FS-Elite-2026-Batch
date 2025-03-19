package Day25;
// Imagine you are the curator of a historic library, where books are arranged in a 

// unique catalog system based on their publication years. The library’s archive is 
// structured like a hierarchical tree, with each book’s publication year stored at 
// a node. You are given the nodes of this catalog tree starting with main node
// and a list of query years.

// For each query year, you need to find two publication years:
// - The first is the latest year in the archive that is less than or equal to the 
//   query year. If no such book exists, use -1.
// - The second is the earliest year in the archive that is greater than or equal 
//   to the query year. If no such book exists, use -1.

// Display the results as an list of pairs, where each pair corresponds to a query.

// Example 1:
// ----------
// Input: 
// 2006 2002 2013 2001 2004 2009 2015 2014
// 2002 2005 2016

// Output:
// [[2002, 2002], [2004, 2006], [2015, -1]] 

// Archive Structure:
//           2006
//          /    \
//      2002     2013
//      /   \     /   \
//   2001  2004  2009  2015
//                      /
//                   2014

// Explanation:  
// - For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
//   the earliest publication year that is ≥ 2002 is also 2002.  
// - For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
//   the earliest publication year that is ≥ 2005 is 2006.  
// - For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
//   there is no publication year ≥ 2016, so we output -1 for the second value.

// Example 2:
// ----------
// Input:  
// 2004 2009
// 2003

// Output:
// [[-1, 2004]]

// Explanation:  
// - For the query 2003, there is no publication year ≤ 2003, while the earliest 
//   publication year that is ≥ 2003 is 2004.

// Constraints:
// - The total number of books in the archive is in the range [2, 10^5].
// - Each publication year is between 1 and 10^6.
// - The number of queries n is in the range [1, 10^5].
// - Each query year is between 1 and 10^6.

import java.util.*;

public class Catalogue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tree = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

        Node root = buildBST(tree);

        int[] queries = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

        int[][] ans = findMinMax(root, queries);
        for (int[] a : ans)
            System.out.printf(Arrays.toString(a) + " ");

        sc.close();
    }

    private static int[][] findMinMax(Node root, int[] queries) {
        int n = queries.length;
        int[][] res = new int[n][2];

        for (int i = 0; i < n; i++) {
            int query = queries[i];
            int min = findMin(root, query);
            int max = findMax(root, query);

            res[i] = (new int[] { max, min });
        }

        return res;
    }

    private static int findMin(Node root, int tgt) {
        int min = -1;
        while (root != null) {
            if (root.val >= tgt) {
                min = root.val;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return min;
    }

    private static int findMax(Node root, int tgt) {
        int max = -1;
        while (root != null) {
            if (root.val <= tgt) {
                max = root.val;
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return max;
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