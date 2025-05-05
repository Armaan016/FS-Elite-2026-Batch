package Day28;

// Imagine you're the chief engineer aboard a futuristic spaceship. The ship is 
// powered by N series of fuel cells arranged in a row, where each fuel cell holds
// a specific amount of energy measured in megajoules. Your job is to manage these 
// fuel cells by performing two main operations:

// Option 1: Calculate the total energy available in a consecutive block of fuel 
//           cells between indices start and end (inclusive).
// Option 2: Update the energy level with given 'newEnergy' of a specific 'index' 
//           fuel cell when it's refilled.

// Input Format:
// -------------
// Line-1: Two integers N and Q, where N is the number of fuel cells and Q is the number of operations.
// Line-2: N space separated integers.
// next Q lines: Three integers option, start/index and end/newEnergy.

// Output Format:
// --------------
// An integer result, for every totalEnergy.

// Example 1:
// -----------
// Input:
// 8 5
// 1 2 13 4 25 16 17 8
// 1 2 6		//totalEnerge
// 1 0 7		//totalEnerge
// 2 2 18		//recharge
// 2 4 17		//recharge
// 1 2 7		//totalEnerge

// Output:
// 75
// 86
// 80

// Example 2:
// ----------
// Input:
// 16 1
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
// 1 0 15

// Output:
// 136

// Constraints

// - 1 <= N <= 3*10^4  
// - -100 <= fuelCells[i] <= 100  
// - 0 <= index < fuelCells.length  
// - -100 <= newEnergy <= 100  
// - 0 <= start <= end < fuelCells.length  
// - At most 3*10^4 calls will be made to recharge and totalEnergy.

// */

import java.util.*;

public class TotalFuelEnergy {
    private static int[] fenwick;
    private static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();

        int[] fuels = new int[n];
        for (int i = 0; i < n; i++)
            fuels[i] = sc.nextInt();

        int[][] ops = new int[q][3];
        for (int i = 0; i < q; i++) {
            ops[i] = new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt() };
        }

        findTotalEnergies(fuels, ops);

        sc.close();
    }

    private static void findTotalEnergies(int[] fuels, int[][] ops) {
        int n = fuels.length;
        fenwick = new int[n + 1];
        arr = Arrays.copyOf(fuels, n);

        for (int i = 0; i < n; i++) {
            updateBIT(i + 1, fuels[i]);  
        }

        for (int[] op : ops) {
            if (op[0] == 1) { 
                int start = op[1];
                int end = op[2];
                System.out.println(getRangeSum(start, end));
            } else {
                int idx = op[1];
                int energy = op[2];
                int diff = energy - arr[idx];
                arr[idx] = energy;
                updateBIT(idx + 1, diff);  
            }
        }
    }

    private static int getRangeSum(int left, int right) {
        return getPrefix(right + 1) - getPrefix(left);  
    }

    private static int getPrefix(int idx) {
        int sum = 0;
        while (idx > 0) {  
            sum += fenwick[idx];
            idx -= (idx & -idx);
        }
        return sum;
    }

    private static void updateBIT(int idx, int val) {
        while (idx <= arr.length) {
            fenwick[idx] += val;
            idx += (idx & -idx);
        }
    }
}