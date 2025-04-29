package Day41;

// You are participating in a futuristic space exploration mission where you must 
// navigate through a series of planets aligned in a straight line.
// The planets are numbered from 0 to n-1, and you start your journey on planet 0.

// You are given a 0-indexed array planets, where each element planets[i] represents 
// the maximum number of planets you can move forward from planet i. In simpler terms, 
// standing on planet i, you can move to any planet from i+1 to i+planets[i], 
// as long as you don't exceed the last planet.

// Your goal is to reach the final planet (planet n-1) in the fewest number of 
// moves possible.

// It is guaranteed that a path to the final planet always exists.

// Return the minimum number of moves needed to reach planet n-1.

// Example 1
// ----------
// Input:
// 2 3 1 0 4
// Output:
// 2

// Explanation:
// - Move from planet 0 to planet 1.
// - Move from planet 1 to planet 4 (last planet).

import java.util.*;

public class PlanetMission {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] planets = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
        
        // System.out.println(minMoves(planets));
        System.out.println(minMovesDP(planets));

        sc.close();
    }
    
    // private static int minMoves(int[] n8ums) {
    //     int max = 0, n = nums.length, steps = 0;
    //     if(n == 1) return 0;
        
    //     for(int i = 0; i < n; i++) {
    //         if(max >= n - 1) return steps;
            
    //         if(i + nums[i] > max) {
    //             max = i + nums[i];
    //             steps++;
    //         }
            
    //     }
        
    //     return steps;
    // }
    
    private static int minMovesDP(int[] nums) {
        Map<String, Integer> dp = new HashMap<>();
        return memo(nums, 0, 0, dp);
    }
    
    private static int memo(int[] nums, int idx, int max, Map<String, Integer> dp) {
        if(idx >= nums.length) return 0;
        if(idx == nums.length - 1) return 0;
        
        String key = idx + "," + max;
        if(dp.containsKey(key)) return dp.get(key);
        
        int skip = Integer.MAX_VALUE;
        if(max > idx) skip = memo(nums, idx + 1, max, dp);
        
        int take = 1 + memo(nums, idx + 1, Math.max(max, idx + nums[idx]), dp);
        
        int res = Math.min(take, skip);
        
        dp.put(key, res);
        return res;
    }
}