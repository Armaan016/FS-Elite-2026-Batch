package Day78;

// Sreedhar is a farmer, and he started harvesting the watermelon crop, 
// the crop grown very well. There are several watermelons in the crop. 
// Sreedhar started picking up the watermelons one by one.
// After each pick, he keeps the watermelon in a truck placed inside the crop.

// The crop is in the from of 2D grid of size m*n.
// You will be given the positions of the truck, Sreedhar's, and the watermelons.  
// Positions are represented by the cells in the 2D grid. 

// Your task is to find the minimum distance for Sreedhar to collect 
// all the watermelons and put them inside the truck one by one. 

// Sreedhar can only take at most one watermelon at one time 
// and can move in four directions - up, down, left and right, to the adjacent cell. 
// The distance is represented by the number of moves.

// Input Format:
// -------------
// Line-1: Two space separated integers m and n, size of crop. 
// Line-2: Two space separated integers, position of the truck. 
// Line-3: Two space separated integers, position of Sreedhar.
// Line-4: An integer W, number of watermelons in the crop.
// Next W lines: Two space separated integers, positions of watermelon. 

// Output Format:
// --------------
// An integer, minimum distance covered by Sreedhar to pickup all the watermelons

// Sample Input-1:
// ---------------
// 5 7		
// 2 2		
// 4 4		
// 2		
// 3 0		
// 2 5

// Sample Output-1:
// ----------------
// 12

// !! This code gets 50/100 score only !!
import java.util.*;

public class HarvestingCrops {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[] truck = new int[2];
        truck[0] = sc.nextInt();
        truck[1] = sc.nextInt();

        int[] sridhar = new int[2];
        sridhar[0] = sc.nextInt();
        sridhar[1] = sc.nextInt();

        int w = sc.nextInt();
        int[][] melons = new int[w][2];
        for (int i = 0; i < w; i++) {
            melons[i] = new int[] { sc.nextInt(), sc.nextInt() };
        }

        System.out.println(getMinDistance(m, n, truck, sridhar, melons));
        sc.close();
    }

    private static int res;

    private static int getMinDistance(int m, int n, int[] truck, int[] sridhar, int[][] melons) {
        res = Integer.MAX_VALUE;

        backtrack(m, n, truck, sridhar[0], sridhar[1], melons, new boolean[melons.length], 0, 0);
        return res;
    }

    private static void backtrack(int m, int n, int[] truck, int x, int y, int[][] melons, boolean[] taken, int dist,
            int count) {
        if (count == melons.length) {
            res = Math.min(res, dist);
            return;
        }

        for (int i = 0; i < melons.length; i++) {
            if (!taken[i]) {
                taken[i] = true;
                backtrack(m, n, truck, truck[0], truck[1], melons, taken,
                        dist + calcDist(x, y, melons[i][0], melons[i][1])
                                + calcDist(melons[i][0], melons[i][1], truck[0], truck[1]),
                        count + 1);
                taken[i] = false;
            }
        }
    }

    private static int calcDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}