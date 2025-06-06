package Day22;

// Imagine a mystical garden with eight enchanted flower beds arranged in a straight 
// line. In each bed, a magical blossom can either be in full bloom (represented by 1) 
// or remain dormant (represented by 0). 

// Every day, the garden performs its magic ritual: 
// - The state of each blossom is updated based on the state of the two neighboring 
//   flower beds. 
// - Specifically, if both neighbors of a flower bed are either blooming or both 
//   are dormant, then the blossom in that bed will bloom the next day; 
// - otherwise, it will remain dormant. 

// Note that since the garden is arranged in a single row, the first and the last 
// flower beds have only one neighbor each and thus cannot satisfy the two-neighbor
// condition—they will always become dormant the next day.

// You are provided with an integer array plots of length 8 where plots[i] == 1 
// indicates that the blossom in the ith bed is blooming, and plots[i] == 0 
// means it is dormant. You are also given an integer n which represents the number 
// of days this transformation ritual takes place.

// Your task is to determine the state of the garden after n days of magical 
// transformation.

// Example 1:
// input=
// 0 1 0 1 1 0 0 1
// 7
// output=
// [0, 0, 1, 1, 0, 0, 0, 0]

// Explanation: 
// The garden transforms as follows over 7 days:  
//   - Day 0: [0, 1, 0, 1, 1, 0, 0, 1]  
//   - Day 1: [0, 1, 1, 0, 0, 0, 0, 0]  
//   - Day 2: [0, 0, 0, 0, 1, 1, 1, 0]  
//   - Day 3: [0, 1, 1, 0, 0, 1, 0, 0]  
//   - Day 4: [0, 0, 0, 0, 0, 1, 0, 0]  
//   - Day 5: [0, 1, 1, 1, 0, 1, 0, 0]  
//   - Day 6: [0, 0, 1, 0, 1, 1, 0, 0]  
//   - Day 7: [0, 0, 1, 1, 0, 0, 0, 0]

// Example 2:
// input=
// 1 0 0 1 0 0 1 0
// 1000000000
// output=
// [0, 0, 1, 1, 1, 1, 1, 0] 

// Explanation: After one billion days of transformation, the garden settles 
// into the final state [0, 0, 1, 1, 1, 1, 1, 0].

// Constraints:

// - plots.length == 8  
// - plots[i] is either 0 or 1.  
// - 1 <= n <= 10^9

import java.util.*;

public class GardenState {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] garden = new int[8];
        for(int i = 0; i < 8; i++) {
            garden[i] = sc.nextInt();
        }
        
        int k = sc.nextInt();
        
        System.out.println(Arrays.toString(findGardenState(garden, k)));

        sc.close();
    }
    
    private static int[] findGardenState(int[] arr, int k) {
        // System.out.println("Day: " + k + " arr: " + Arrays.toString(arr));
        
        while(k-- > 0) {
            int[] temp = new int[arr.length];
            for(int i = 0; i < 8; i++) temp[i] = arr[i];
            
            for(int i = 1; i < 7; i++) {
                if(arr[i - 1] == arr[i + 1]) temp[i] = 1;
                else if(arr[i] == 1) temp[i] = 0;
            }
            
            if(temp[0] == 1) temp[0] = 0;
            if(temp[7] == 1) temp[7] = 0;
            for(int i = 0; i < 8; i++) arr[i] = temp[i];
            // System.out.println("Day: " + k + " arr: " + Arrays.toString(arr));
        }
        
        return arr;
    }
}