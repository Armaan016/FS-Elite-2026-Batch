package Day34;

// Imagine a unique circular conveyor belt in a sushi restaurant, which serves 
// exactly 26 distinct dishes labeled by 26 unique lowercase English letters 
// ('a' to 'z'). The dishes are arranged linearly along the conveyor belt, indexed 
// from 0 to 25 according to the given order. Initially, a robotic serving arm is 
// positioned at index 0.

// Whenever a customer orders a specific dish, the robotic arm moves from its current 
// position to the position of the desired dish along the belt. The robotic arm takes 
// exactly one second per unit distance to move along the conveyor belt (the time 
// taken from index i to index j is |i - j| seconds).

// Given the conveyor belt layout (order of dishes) and a customer's order represented 
// as a word (sequence of dishes), write a code to calculate the total time the robotic 
// serving arm will take to serve the customer's entire order.

// Input Format:
// -------------
// Line-1: A String, belt layout.
// Line-2: A String, word: customer's order.

// Output Format:
// --------------
// An integer T, time to serve.

// Sample Output-1:
// ----------------
// a b c d e f g h i j k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
// code
// (2 - 0) + (14 - 2) + (14 - 3) + (3 - 4) = 26

// Sample Output-1:
// ----------------
// 26

import java.util.*;

public class ConveyorBelt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String belt = sc.nextLine();
        String word = sc.nextLine();

        System.out.println(calculateTime(belt, word));

        sc.close();
    }

    private static int calculateTime(String belt, String word) {
        int time = 0;
        int pos = 0;

        for (char c : word.toCharArray()) {
            int idx = belt.indexOf(c);

            time += Math.abs(idx - pos);
            pos = idx;
        }

        return time;
    }
}