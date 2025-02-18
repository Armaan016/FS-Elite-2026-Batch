import java.util.*;

public class SlidingWindowExtra2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        System.out.println(containsNearbyDuplicate(nums, k));

        sc.close();
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;

        int i = 0, j = 0;
        Set<Integer> set = new HashSet<>();
        while (j < n) {
            if (set.contains(nums[j]))
                return true;

            set.add(nums[j]);

            while (j - i + 1 > k) {
                set.remove(nums[i]);
                i++;
            }
            
            j++;
        }

        return false;
    }
}