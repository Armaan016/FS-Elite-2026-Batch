package Day12;

// Imagine a company where each employee has a performance score, and
// the organizational chart is structured as a binary tree with the CEO at the
// top.
// An employee is considered "outstanding" if, along the chain of command from
// the
// CEO down to that employee, no one has a performance score higher than that
// employee's score. Your task is to determine the total number of outstanding
// employees in the company.

// Example 1:
// Input: 3 1 4 3 -1 1 5
// Output: 4

// Chart formed:
// 3
// / \
// 1 4
// / / \
// 3 1 5

// Explanation:
// - The CEO (score 3) is automatically outstanding.
// - The employee with score 4, whose chain is [3,4], is outstanding because 4
// is higher than 3.
// - The employee with score 5, following the path [3,4,5], is outstanding as 5
// is the highest so far.
// - The subordinate with score 3, along the path [3,1,3], is outstanding
// because
// none of the managers in that chain have a score exceeding 3.

// Example 2:
// Input: 3 3 -1 4 2
// Output: 3

// Chart formed:
// 3
// /
// 3
// / \
// 4 2

// Explanation:
// - The CEO (score 3) is outstanding.
// - The subordinate with score 3 (chain: [3,3]) is outstanding.
// - The employee with score 2 (chain: [3,3,2]) is not outstanding because the
// managers have higher scores.
// Imagine a company where each employee has a performance score, and
// the organizational chart is structured as a binary tree with the CEO at the
// top.
// An employee is considered "outstanding" if, along the chain of command from
// the
// CEO down to that employee, no one has a performance score higher than that
// employee's score. Your task is to determine the total number of outstanding
// employees in the company.

// Example 1:
// Input: 3 1 4 3 -1 1 5
// Output: 4

// Chart formed:
// 3
// / \
// 1 4
// / / \
// 3 1 5

// Explanation:
// - The CEO (score 3) is automatically outstanding.
// - The employee with score 4, whose chain is [3,4], is outstanding because 4
// is higher than 3.
// - The employee with score 5, following the path [3,4,5], is outstanding as 5
// is the highest so far.
// - The subordinate with score 3, along the path [3,1,3], is outstanding
// because
// none of the managers in that chain have a score exceeding 3.

// Example 2:
// Input: 3 3 -1 4 2
// Output: 3

// Chart formed:
// 3
// /
// 3
// / \
// 4 2

// Explanation:
// - The CEO (score 3) is outstanding.
// - The subordinate with score 3 (chain: [3,3]) is outstanding.
// - The employee with score 2 (chain: [3,3,2]) is not outstanding because the
// managers have higher scores.

import java.util.*;

public class OutstandingEmployees {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] tree = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
        
        Node root = buildTree(tree);
        
        System.out.println(countOutstanding(root));

        sc.close();
    }
    
    private static int count;
    private static int countOutstanding(Node root) {
        count = 1;
        
        helper(root.left, root.val);
        helper(root.right, root.val);
        
        return count;
    }
    
    private static void helper(Node root,  int max) {
        if(root == null) return;
        
        if(root.val >= max) count++;
        
        max = Math.max(max, root.val);
        
        
        helper(root.left, max);
        helper(root.right, max);
    }
    
    private static Node buildTree(int[] tree) {
        int n = tree.length;
        Node root = new Node(tree[0]);
        
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        
        int i = 1;
        while(!q.isEmpty()) {
            Node curr = q.poll();
            
            if(i < n && tree[i] != -1) {
                curr.left = new Node(tree[i]);
                q.offer(curr.left);
            }
            i++;
            
            if(i < n && tree[i] != -1) {
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