package Day72;

// Gopal would like to go on Tour, and planned a schedule.
// Airport authority has created a new way of issuing tickets.
// Gopal purchased a set of airline tickets, 
// each ticket contains the 'departure from' and 'arrival to'.

// Redesign the Gopal's tour schedule in an order.
// Gopal intially must begin his tour from BZA.
// Hence the tour schedule's start point should begin with BZA. 

// You are given a list of flight tickets which Gopal has purchased.
// Your task is to find out and return the tour schedule that has the least 
// lexical order. Gopal must use all the tickets and only once.

// Note:
// ------
// If there are multiple valid schedules, you should return the schedule 
// that has the smallest lexical order when read as a single string. 
// For example, the schedule ["BZA", "LGA"] has a smaller lexical order than ["BZA", "LGB"].

// All airports are represented by three capital letters.
// You may assume all tickets form at least one valid schedule.

// Input Format:
// -------------
// Single Line of tickets separated by comma, and each ticket separated by space, 
// Gopal's flight tickets.

// Output Format:
// --------------
// Print the schedule, which is smallest lexical order of tour schedule.

// Sample Input-1:
// ----------------
// DEL HYD,BZA DEL,BLR MAA,HYD BLR

// Sample Output-1:
// --------------------
// [BZA, DEL, HYD, BLR, MAA]

// Sample Input-2:
// ------------------
// BZA BLR,BZA CCU,BLR CCU,CCU BZA,CCU BLR

// Sample Output-2:
// ------------------
// [BZA, BLR, CCU, BZA, CCU, BLR]

import java.util.*;

public class AirportSchedule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tickets = sc.nextLine().split(",");

        System.out.println(getPath(tickets));
        sc.close();
    }

    private static String res;

    private static String getPath(String[] tickets) {
        res = "-1";

        Map<String, List<String>> adj = new HashMap<>();
        for (String ticket : tickets) {
            String[] curr = ticket.split(" ");

            adj.putIfAbsent(curr[0], new ArrayList<>());
            adj.get(curr[0]).add(curr[1]);
        }

        // System.out.println(adj);

        StringBuilder path = new StringBuilder();
        path.append("BZA ");
        backtrack(adj, "BZA", path, 0, tickets.length, new HashSet<>());
        return res;
    }

    private static void backtrack(Map<String, List<String>> adj, String node, StringBuilder path, int count, int n,
            Set<String> visited) {
        if (count == n) {
            if (res.equals("-1"))
                res = path.toString();
            else {
                if (res.compareTo(path.toString()) > 0) {
                    res = path.toString();
                }
            }

            return;
        }

        for (String ngbr : adj.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(node + "->" + ngbr)) {
                int l = path.length();
                path.append(ngbr).append(" ");
                visited.add(node + "->" + ngbr);

                backtrack(adj, ngbr, path, count + 1, n, visited);

                path.setLength(l);
                visited.remove(node + "->" + ngbr);
            }
        }
    }
}