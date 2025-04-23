package Day38;

// You are a stealthy archaeologist exploring a circular ring of ancient tombs 
// located deep within a jungle. Each tomb holds a certain number of precious 
// artifacts. 
// However, these tombs are protected by an ancient magical curse: 
// if you disturb two adjacent tombs during the same night, the entire ring 
// activates a trap that seals you in forever.

// The tombs are arranged in a perfect circle, meaning the first tomb is adjacent 
// to the last. You must plan your artifact retrieval carefully to maximize the 
// number of artifacts collected in a single night without triggering the curse.

// Given an integer array  artifacts  representing the number of artifacts in each 
// tomb, return the   maximum   number of artifacts you can collect without 
// disturbing any two adjacent tombs on the same night.

// Example 1:  
// Input:
// 2 4 3
// Output:  
// 4   

// Explanation: You cannot loot tomb 1 (artifacts = 2) and tomb 3 (artifacts = 3), 
// as they are adjacent in a circular setup.

// Example 2:  
// Input:
// 1 2 3 1
// Output:  
// 4

// Explanation: You can loot tomb 1 (1 artifact) and tomb 3 (3 artifacts) without 
// breaking the ancient rule.  
// Total = 1 + 3 = 4 artifacts.

// Example 3:  
// Input:
// 1 2 3
// Output:  
// 3 

// Constraints:  
// -  1 <= artifacts.length <= 100 
// -  0 <= artifacts[i] <= 1000 

import java.util.*;

public class CircularTombs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

        System.out.println(memo(arr));

        sc.close();
    }

    private static int memo(int[] arr) {
        int n = arr.length;

        Map<String, Integer> map = new HashMap<>();
        return Math.max(helper(arr, 0, n - 2, map), helper(arr, 1, n - 1, map));
    }

    private static int helper(int[] arr, int idx, int end, Map<String, Integer> dp) {
        if (idx > end)
            return 0;

        String key = idx + "," + end;
        if (dp.containsKey(key))
            return dp.get(key);

        int take = arr[idx] + helper(arr, idx + 2, end, dp);
        int skip = helper(arr, idx + 1, end, dp);

        int res = Math.max(take, skip);
        dp.put(key, res);
        return res;
    }
}