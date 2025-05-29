package Day62;

// Mr Shravan is playing a Match-the-Pair Game.

// Initially, He is given two teams team1 and team2.
// Each team has some persons in it, each person is identified with an ID,
// ID's are positive numbers, duplicates are allowed.

// Your task is to match the persons from team1 to team2.
// Team1 consist of M persons and Team2 consist of N persons.
// And the rules for matching the persons are as follows:
// 	- You are allowed to match the persons from team1 with team2,
// 	only if the IDs are equal and you have to draw a line between the pair.
// 	- You are not allowed draw the line which is passing through another line.
// 	- If you are not allowed to draw the line, you cannot match the pair.
// 	- Each person must connected with atmost one person only.

// Finally, count the maximum numbers of pairs can be formed between the teams.

// Input Format:
// -------------
// Line-1: Two space separated integers, M and N.
// Line-2: M space separated integers, team1[].
// Line-3: N space separated integers, team2[]. 

// Output Format:
// --------------
// Print an integer result.

// Sample Input-1:
// ---------------
// 4 4
// 1 2 3 5
// 3 1 5 2

// Sample Output-1:
// ----------------
// 2

// Explanation: 
// ------------
// You can Match the pair as follows: 
// S1 - team1[0]-team2[1], team1[1]-team2[3]
// S2 - team1[0]-team2[1], team1[3]-team2[2]
// S3 - team1[2]-team2[0], team1[3]-team2[2]
// In S1 you cannot match 3 with 3 or 5 with 5, because you are
// not allowed to draw a line. same for S2 and S3.

// Sample Input-2:
// ---------------
// 6 7
// 6 5 2 4 2 5
// 2 5 3 4 6 2 4

// Sample Output-2:
// ----------------
// 3

import java.util.*;

public class MaximumPairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[] team1 = new int[m];
        for (int i = 0; i < m; i++) {
            team1[i] = sc.nextInt();
        }

        int[] team2 = new int[n];
        for (int i = 0; i < n; i++) {
            team2[i] = sc.nextInt();
        }

        System.out.println(countMaxPairs(team1, team2));
        sc.close();
    }

    private static int countMaxPairs(int[] team1, int[] team2) {
        Integer[][] dp = new Integer[team1.length][team2.length];

        return memo(team1, team2, 0, 0, dp);
    }

    private static int memo(int[] team1, int[] team2, int i, int j, Integer[][] dp) {
        if (i == team1.length || j == team2.length)
            return 0;

        if (dp[i][j] != null)
            return dp[i][j];

        int take = 0, skip = 0;
        if (team1[i] == team2[j])
            take = 1 + memo(team1, team2, i + 1, j + 1, dp);

        skip = Math.max(memo(team1, team2, i + 1, j, dp), memo(team1, team2, i, j + 1, dp));

        int res = Math.max(take, skip);
        return dp[i][j] = res;
    }
}