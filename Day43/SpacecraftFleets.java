package Day43;

// You are managing a fleet of exploratory spacecraft, each carrying containers
// composed of two types of critical resources:
// fuel units (represented by '0') and oxygen tanks (represented by '1').
// You're given a list 'containers', where each container is represented by a
// binary string indicating its resource composition,
// along with two integers: 'fuelLimit' (maximum allowed fuel units) and
// 'oxygenLimit' (maximum allowed oxygen tanks).

// Your goal is to select the largest possible subset of containers such that
// the
// total number of fuel units does not exceed 'fuelLimit' and the total number
// of
// oxygen tanks does not exceed 'oxygenLimit'.

// Input format:
// -------------
// Line 1: Space seperated strings, represents the container strings
// Line 2: Two space separated integers, represents fuelLimit & oxygenLimit

// Output format:
// --------------
// An integer, largest possible subset of containers.

// Example 1:
// ----------
// Input=
// 10 0001 111001 1 0
// 5 3

// Output=
// 4

// Explanation: The largest subset meeting the constraints is {"10", "0001",
// "1", "0"}.
// - Total fuel units = 5 (within limit)
// - Total oxygen tanks = 3 (within limit)
// Container "111001" cannot be included as it exceeds the oxygen tank limit.

// Example 2:
// ----------
// Input=
// 10 0 1
// 1 1

// Output=
// 2

// Explanation: The largest subset meeting the constraints is {"0", "1"}.
// - Total fuel units = 1
// - Total oxygen tanks = 1

// Constraints:
// - 1 <= containers.length <= 600
// - 1 <= containers[i].length <= 100
// - 'containers[i]' consists only of digits '0' and '1'.
// - 1 <= fuelLimit, oxygenLimit <= 100

import java.util.*;

public class SpacecraftFleets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] containers = sc.nextLine().split(" ");

        int fuelLimit = sc.nextInt();
        int oxygenLimit = sc.nextInt();

        System.out.println(maxContainers(containers, fuelLimit, oxygenLimit));

        sc.close();
    }

    private static int maxContainers(String[] c, int f, int o) {
        int n = c.length;

        int[] ones = new int[n];
        int[] zeros = new int[n];

        for (int i = 0; i < n; i++) {
            ones[i] = countChars(c[i], '1');
            zeros[i] = countChars(c[i], '0');
        }

        int[][][] dp = new int[n][f + 1][o + 1];
        for (int[][] a : dp) {
            for (int[] b : a)
                Arrays.fill(b, -1);
        }

        return memo(ones, zeros, 0, f, o, 0, 0, dp);
    }

    private static int memo(int[] ones, int[] zeros, int idx, int f, int o, int currF, int currO, int[][][] dp) {
        if (idx == ones.length)
            return 0;

        if (dp[idx][currF][currO] != -1)
            return dp[idx][currF][currO];

        int take = Integer.MIN_VALUE;
        if (ones[idx] + currO <= o && zeros[idx] + currF <= f) {
            take = 1 + memo(ones, zeros, idx + 1, f, o, currF + zeros[idx], currO + ones[idx], dp);
        }

        int skip = memo(ones, zeros, idx + 1, f, o, currF, currO, dp);

        return dp[idx][currF][currO] = Math.max(take, skip);
    }

    private static int countChars(String s, char ch) {
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == ch)
                count++;
        }

        return count;
    }
}