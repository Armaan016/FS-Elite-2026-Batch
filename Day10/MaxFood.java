package Day10;

// You are a bird living in a vast forest. Every day, you fly across different locations 

// to collect food and store it in various nests. However, you must return to your home 
// nest before sunset to rest safely.  

// Your objective is to collect as much food as possible within a given time limit 
// while following the forest rules:
// 1. Each food location contains only one unit of food.  
// 2. The bird can carry only one unit of food at a time.  
// 3. The bird must deposit food into a nest before collecting more.  
// 4. Distance Calculation: The time taken to fly between two locations using 
// the Euclidean distance formula:  d = sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}
// 5. The total time spent is the sum of:  
//    - Travel time between locations.  
//    - Fixed time to deposit food in a nest (each nest has a different deposit 
//    time).  
// 6. The bird must return to the home nest before the total time limit.  

// Your goal is to determine the maximum number of food units that the bird
// can collect and store in different nests before sunset.  

// Input Format:
// -------------
// An integer representing the number of food locations.  
// An integer representing the number of nests.  
// A 2D array containing the coordinates of each food location as pairs (x, y) 
// A 2D array containing the coordinates of each nest as pairs (x, y).  
// A 1D array containing the starting coordinates (home nest) (x, y).  
// A floating-point number representing the total time available (before sunset).  

// Output Format:
// ---------------  
// The function must return an integer, representing the maximum number of 
// food units that can be collected and stored in nests within the given time.  

// Sample Input:
// --------------
// 2
// 2
// 3 3
// 4 6
// 5 5
// 6 1
// 1 4
// 13

// Sample Output:
// ---------------
// 2

// Explanation:
// ---------------
// The bird starts at (1,4).
// Moves to food location (3,3) (distance = sqrt(5)).
// Deposits food at a nest (5,5) (distance = sqrt(8)).
// Moves to food location (4,6) (distance = sqrt(2))
// and collects another unit of food.
// Deposits it at a nest (5,5) (distance = sqrt(2)). 
// Returns to the starting point (1,4) (distance = sqrt(17)).
// Total distance is: sqrt(5) + sqrt(8) + sqrt(2) + sqrt(2)+ sqrt(17) = 12.0160278526
// Since the total distance is within the allowed time (13), 
// the maximum food units collected are 2.

// Sample Input:
// --------------
// 4
// 1
// 3 3
// 5 7
// 7 8
// 8 4
// 7 7
// 1 5
// 22

// Sample Output:
// ---------------
// 3

import java.util.*;

public class MaxFood {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int f = sc.nextInt();
        int n = sc.nextInt();

        int[][] food = new int[f][2];
        for (int i = 0; i < f; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            food[i] = new int[] { a, b };
        }

        int[][] nests = new int[n][2];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            nests[i] = new int[] { a, b };
        }

        int x = sc.nextInt();
        int y = sc.nextInt();

        float maxTime = sc.nextFloat();

        System.out.println(findMaxFood(food, nests, x, y, maxTime));

        sc.close();
    }

    private static int[] st;
    private static int maxFood;

    private static int findMaxFood(int[][] food, int[][] nests, int x, int y, float maxTime) {
        int f = food.length;
        st = new int[] { x, y };

        boolean[] used = new boolean[f];

        maxFood = 0;
        backtrack(food, nests, x, y, used, maxTime, 0, 0);

        return maxFood;
    }

    private static void backtrack(int[][] food, int[][] nests, int currX, int currY,
            boolean[] used, float maxTime, float dist, int count) {
        float d = dist + calculateDistance(currX, currY, st[0], st[1]);
        if (d <= maxTime) {
            maxFood = Math.max(maxFood, count);
        }

        for (int i = 0; i < food.length; i++) {
            if (!used[i]) {
                used[i] = true;

                for (int j = 0; j < nests.length; j++) {
                    float newDist = dist + calculateDistance(currX, currY, food[i][0], food[i][1]) +
                            calculateDistance(food[i][0], food[i][1], nests[j][0], nests[j][1]);
                    backtrack(food, nests, nests[j][0], nests[j][1], used, maxTime, newDist, count + 1);
                }

                used[i] = false;
            }
        }
    }

    private static float calculateDistance(int x1, int y1, int x2, int y2) {
        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}