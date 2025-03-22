    package GrandTest1;

    // Given an array coupons[] containing n coupons, a starting amount x, and 0 gold to start with, for every coupon coupons[i], you can either:
    // 1. Take the coupon if your current amount >= coupons[i]: You lose coupons[i] amount and gain 1 gold.
    // 2. Give 1 gold if your gold > 0: You gain coupons[i] amount and lose 1 gold.
    // Find the maximum gold you can have at the end.

    import java.util.*;
    
    public class TwoPointers {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int x = sc.nextInt();

            int[] coupons = new int[n];
            for (int i = 0; i < n; i++) {
                coupons[i] = sc.nextInt();
            }

            System.out.println(maxGold(n, x, coupons));

            sc.close();
        }

        private static int maxGold(int n, int x, int[] coupons) {
            Arrays.sort(coupons);

            int gold = 0;
            int i = 0;
            int j = n - 1;

            int max = 0;
            while (i <= j) {
                if (x >= coupons[i]) {
                    x -= coupons[i];
                    gold++;
                    i++;
                    max = Math.max(max, gold);
                } else if (gold > 0) {
                    x += coupons[j];
                    gold--;
                    j--;
                } else {
                    break;
                }
            }

            return max;
        }
    }
