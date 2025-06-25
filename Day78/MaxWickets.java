package Day78;

// BCCI wants to select the group of bowlers for an upcoming test-series, 
// you want to choose the group with highest number of wickets, which is 
// the sum of wickets taken by all the bowlers in that group.

// However, the bowler group is not allowed to have any disputes. A dispute
// exists if a younger bowler has strictly highest wickets than an older bowler. 
// A dispute does not occur between bowlers of the same age.

// You are given information of N bowlers as two lists, wickets and ages, 
// where each wickets[i] and ages[i] represents the wickets and age of 
// the i-th bowler, respectively, return the highest number of wickets 
// of all possible bowler groups.

// Input Format:
// -------------
// Line-1: An integer N, number of bowlers.
// Line-2: N space separated integers, wickets[].
// Line-3: N space separated integers, ages[].

// Output Format:
// --------------
// An integer, highest number of wickets of all possible bowler groups.

// Sample Input-1:
// ---------------
// 4
// 5 3 5 6
// 3 5 4 2

// Sample Output-1:
// ----------------
// 10

// Explanation:
// ------------
// It is best to choose 2 bowlers of age 3 and 4. 

// Sample Input-2:
// ---------------
// 5
// 5 3 5 6 3
// 2 5 4 2 1

// Sample Output-2:
// ----------------
// 14

// Explanation:
// ------------
// It is best to choose 3 bowlers of age 1 and 2. 
// Notice that you are allowed to choose multiple bowlers of the same age.

// Sample Input-3:
// ---------------
// 5
// 1 3 5 10 15
// 1 2 3 4 5

// Sample Output-3:
// ----------------
// 34

// Explanation:
// ------------
// You can choose all the bowlers.

import java.util.*;

public class MaxWickets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] wickets = new int[n];
        for (int i = 0; i < n; i++)
            wickets[i] = sc.nextInt();

        int[] ages = new int[n];
        for (int i = 0; i < n; i++)
            ages[i] = sc.nextInt();

        System.out.println(getMaxWickets(wickets, ages));
        sc.close();
    }

    private static int getMaxWickets(int[] wickets, int[] ages) {
        int n = ages.length;
        // int sum = Arrays.stream(wickets).sum();
        
        List<Bowler> bowlers = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            bowlers.add(new Bowler(wickets[i], ages[i]));
        }
        
        Collections.sort(bowlers, (a, b) -> (a.age == b.age) ? a.wickets - b.wickets : a.age - b.age);
        
        Integer[][] dp = new Integer[n][n + 1];
        
        return memo(bowlers, 0, -1, dp);
    }

    private static int memo(List<Bowler> bowlers, int idx, int prev, Integer[][] dp) {
        if (idx == bowlers.size())
            return 0;

        if (dp[idx][prev + 1] != null)
            return dp[idx][prev + 1];

        int take = Integer.MIN_VALUE;
        // if(prev == -1 || (ages[idx] == ages[prev]) || (ages[idx] > ages[prev] &&
        // wickets[idx] >= wickets[prev])
        // || (ages[idx] < ages[prev] && wickets[idx] < wickets[prev])) {
        // take = wickets[idx] + memo(wickets, ages, idx + 1, idx, dp);
        // }

        if (prev == -1 || (bowlers.get(idx).wickets >= bowlers.get(prev).wickets)) {
            take = bowlers.get(idx).wickets + memo(bowlers, idx + 1, idx, dp);
        }

        int skip = memo(bowlers, idx + 1, prev, dp);

        return dp[idx][prev + 1] = Math.max(take, skip);
    }
}

class Bowler {
    int wickets;
    int age;

    Bowler(int wickets, int age) {
        this.wickets = wickets;
        this.age = age;
    }
}