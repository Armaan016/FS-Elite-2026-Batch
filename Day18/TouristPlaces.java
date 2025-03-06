// Budget Padmanabham planned to visit the tourist places. There are N tourist 
// places in the city. The tourist places are numbered from 1 to N.

// You are given the routes[] to travel between the tourist places in the city.
// where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
// You can travel from place-1 to place-2 or place-2 to place-1 with the given price.

// Your task is to help Budget Padmanabham to find the cheapest route plan, to 
// visit all the tourist places in the city. If you are not able to find such plan, 
// print -1.

// Input Format:
// -------------
// Line-1: Two space separated integers N and R,number of places and routes.
// Next R lines: Three space separated integers, start, end and price.

// Output Format:
// --------------
// Print an integer, minimum cost to visit all the tourist places.

// Sample Input-1:
// ---------------
// 4 5
// 1 2 3
// 1 3 5
// 2 3 4
// 3 4 1
// 2 4 5

// Sample Output-1:
// ----------------
// 8

// Explanation:
// ------------
// The cheapest route plan is as follows:
// 1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8

// Sample Input-2:
// ---------------
// 4 3
// 1 2 3
// 1 3 5
// 2 3 4

// Sample Output-2:
// ----------------
// -1

import java.util.*;

public class TouristPlaces {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int r = sc.nextInt();

        int[][] routes = new int[r][3];
        for (int i = 0; i < r; i++) {
            routes[i] = new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt() };
        }

        System.out.println(findMinCost(routes, n));

        sc.close();
    }

    private static int[] parent;

    private static int findMinCost(int[][] routes, int n) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        Arrays.sort(routes, (a, b) -> a[2] - b[2]);

        int cost = 0, edges = 0;
        for (int[] route : routes) {
            int a = route[0];
            int b = route[1];
            int c = route[2];

            // System.out.println("Parent: " + Arrays.toString(parent));
            if (union(a, b)) {
                edges++;
                cost += c;
            }
        }

        return edges == n - 1 ? cost : -1;
    }

    private static int find(int p) {
        if (parent[p] != p)
            parent[p] = find(parent[p]);
        return parent[p];
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB)
            return false;

        if (rootA < rootB) {
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }

        return true;
    }
}