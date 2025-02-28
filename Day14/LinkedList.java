// Cliff Shaw is working on the singly linked list.
// He is given a list of boxes arranged as singly linked list,
// where each box is printed a positive number on it.

// Your task is to help Mr Cliff to find the given list is equivalent to 
// the reverse of it or not. If yes, print "true", otherwise print "false"

// Input Format:
// -------------
// Line-1: space separated integers, boxes as list.

// Output Format:
// --------------
// Print a boolean a value.

// Sample Input-1:
// ---------------
// 3 6 2 6 3

// Sample Output-1:
// ----------------
// true

// Sample Input-2:
// ---------------
// 3 6 2 3 6

// Sample Output-2:
// ----------------
// false

import java.util.*;

public class LinkedList {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] line = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
        
        Node head = new Node(line[0]);
        Node temp = head;
        for(int i = 1; i < line.length; i++) {
            temp.next = new Node(line[i]);
            temp = temp.next;
        }
        
        System.out.println(checkPalindrome(head));

        sc.close();
    }
    
    private static boolean checkPalindrome(Node head) {
        Node slow = head;
        Node fast = head;
        
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        Node rev = reverseList(slow.next);
        slow.next = null;
        // System.out.println("reversed: " + rev.val);
        
        return compareLists(head, rev);
    }
    
    private static boolean compareLists(Node head, Node rev) {
        while(head != null && rev != null) {
            if(head.val != rev.val) return false;
            head = head.next;
            rev = rev.next;
        }
        
        // if(head != null || rev != null) return false;
        
        return true;
    }
    
    private static Node reverseList(Node head) {
        Node curr = head;
        Node prev = null;
        Node next;
        
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}

class Node {
    int val;
    Node next;
    
    Node(int val) {
        this.val = val;
        this.next = null;
    }
}