package Day32;

// In Hyderabad after a long pandemic gap, the Telangana Youth festival is 
// Organized at HITEX. In HITEX, there are a lot of programs planned. During 
// the festival in order to maintain the rules of Pandemic, they put a 
// constraint that one person can only attend any one of the programs in 
// one day according to planned days.

// Now it’s your aim to implement the "Solution" class in such a way that 
// you need to return the maximum number of programs you can attend according 
// to given constraints.

// Explanation:
// You have a list of programs 'p' and days 'd', where you can attend only 
// one program on one day. Programs [p] = [first day, last day], 
// p is the program's first day and the last day.

// Input Format:
// -------------
// Line-1: An integer N, number of programs.
// Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by space.

// Output Format:
// --------------
// An integer, the maximum number of programs you can attend.

// Sample Input-1:
// ---------------
// 4
// 1 2,2 4,2 3,2 2

// Sample Output-1:
// ----------------
// 4

// Sample Input-2:
// ---------------
// 6
// 1 5,2 3,2 4,2 2,3 4,3 5

// Sample Output-2:
// ----------------
// 5

import java.util.*;

public class MaximumPrograms {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        sc.nextLine();
        int[][] pairs = new int[n][2];
        String[] line = sc.nextLine().split(",");

        for (int i = 0; i < n; i++) {
            String[] l = line[i].split(" ");
            pairs[i][0] = Integer.parseInt(l[0]);
            pairs[i][1] = Integer.parseInt(l[1]);
        }

        System.out.println(countPrograms(pairs));

        sc.close();
    }

    private static int countPrograms(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int max = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            max = Math.max(max, pairs[i][1]);
        }

        SegmentTree st = new SegmentTree(max);

        int count = 0;
        for (int[] pair : pairs) {
            int available = st.query(1, 1, max, pair[0], pair[1]);

            if (available != -1) {
                st.update(1, 1, max, available);
                count++;
            }
        }

        return count;
    }
}

class SegmentTree {
    public boolean[] t;
    // private int n;

    SegmentTree(int n) {
        // this.n = n;
        t = new boolean[4 * (n + 1)];
        // buildTree(arr, 0, 0, n - 1);
        // Arrays.fill(t, true);
    }

    public int query(int node, int start, int end, int l, int r) {
        if (start > r || end < l)
            return -1;
        if (t[node])
            return -1;

        if (start == end)
            return start;

        int mid = (start + end) / 2;
        int left = query(2 * node, start, mid, l, r);
        if (left != -1)
            return left;

        return query(2 * node + 1, mid + 1, end, l, r);
    }

    public void update(int node, int start, int end, int day) {
        if (start == end) {
            t[node] = true;
            return;
        }

        int mid = (start + end) / 2;
        if (day <= mid) {
            update(2 * node, start, mid, day);
        } else {
            update(2 * node + 1, mid + 1, end, day);
        }

        t[node] = t[2 * node] && t[2 * node + 1];
    }
}