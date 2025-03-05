package Day17;
// There are N people in a private party. Initially all are strangers to each other,

// and the people are identified by unique ID from 0 to N-1.

// In the party, whenever two persons (person-A and person-B) become friends, they 
// took a photo. Each of the photo has some information, photos[i]=[T-i, P-j,P-k],
// here T-i indicates time of the photo taken, P-j person with ID 'j', and 
// P-k indicates person with ID 'k'.

// Friendship is symmetric[i.e., If P-j is friend of P-k, then P-k is a friend of P-j].
// Additionally, if person-A is "a friend of person-B OR a friend of someone who is 
// friend of person-B", then person-A is friend of person-B.

// You are given L photos information, Your task is to find the earliest time 
// for which every person became friend with every other person in the party.
// If there is no such earliest time, return -1.

// Input Format:
// -------------
// Line-1: Two space separated integers, N and L.
// Next L lines: Three space separated integers, log[i], 0<=i<L.

// Output Format:
// --------------
// Print an integer result.

// Sample Input-1:
// ---------------
// 6 8
// 5 0 1
// 7 3 4
// 12 2 3
// 21 1 5
// 34 2 4
// 37 0 3
// 42 1 2
// 93 4 5

// Sample Output-1:
// ----------------
// 37

// Sample Input-2:
// ---------------
// 7 6
// 2 0 3
// 5 1 5
// 8 2 5
// 7 3 6
// 9 4 6
// 6 4 5

// Sample Output-2:
// ----------------
// 9

import java.util.*;

public class PartyFriends {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int l = sc.nextInt();

        int[][] photos = new int[l][3];
        for (int i = 0; i < l; i++) {
            photos[i] = new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt() };
        }

        System.out.println(minTime(photos, n, l));

        sc.close();
    }

    private static int[] parent;

    private static int minTime(int[][] photos, int n, int l) {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        Arrays.sort(photos, (a, b) -> a[0] - b[0]);
        for (int[] photo : photos) {
            int t = photo[0];
            int a = photo[1];
            int b = photo[2];

            // System.out.println(Arrays.toString((parent)));
            // if(!union(a, b)) return t;

            union(a, b);

            Set<Integer> set = new HashSet<>();
            for (int p : parent) {
                set.add(find(p));
            }

            if (set.size() == 1)
                return t;
        }

        return -1;
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

        if (rootA < rootB)
            parent[rootA] = rootB;
        else
            parent[rootB] = rootA;

        return true;
    }
}