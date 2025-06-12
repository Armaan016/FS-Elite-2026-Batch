package Day70;

// A group of students is forming a tech club, and they are 
// interviewing K × K students. Each student has two skill levels:
//  - Mathematics (M)
//  - Physics (P)
// These skill levels range from 0 to K-1.

// They want to form committees of size N under the following 
// conditions:
//  - All members of a committee must have different Mathematics 
//    skill levels.
//  - All members must have different Physics skill levels.
//  - For any two students in the committee, the difference of their 
//    Math skills must not equal the difference of their Physics skills, 
//    i.e., |M1 - M2| != |P1 - P2|

// Input Format:
// -------------
// input1: Integer N – desired committee size
// input2: Integer K – number of skill levels for Math and Physics 
// (total students = K × K)

// Output Fromat:
// --------------
// An integer representing the number of valid committees of size N 
// that can be formed from the K × K students. Return the result 
// modulo 10⁹ + 7.

// Sample Input: 
// -------------
// 2
// 3

// Sample Output: 
// --------------
// 8

// Explanation:
// ------------
// Examples of some valid pairs:
// (0,0) and (1,2) → |0-1| = 1, |0-2| = 2 ✅
// (0,1) and (1,0) → |0-1| = 1, |1-0| = 1 ❌ (same diff → invalid)
// (0,0) and (2,1) → |0-2| = 2, |0-1| = 1 ✅

// 8 Valid Pairs: 1. (0,0) - (1,2)
// 2. (0,0) - (2,1)
// 3. (0,1) - (2,0)
// 4. (0,2) - (1,0)
// 5. (0,2) - (2,1)
// 6. (1,0) - (2,2)
// 7. (1,2) - (0,0)
// 8. (1,2) - (2,0)

// Sample Input: 
// -------------
// 2
// 2

// Sample Output: 
// --------------
// 0

// ! This code pass 5/8 test cases only !
import java.util.*;

public class SkillLevels {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(countValidCommittees(n, k));
        sc.close();
    }

    private static int countValidCommittees(int n, int k) {
        res = 0;
        set = new HashSet<>();
        memo(n, k, 0, 0, 0, new HashSet<>(), new HashSet<>(), 0);

        // System.out.println(set);
        return (res * n) % MOD;
    }

    private static int res;
    private static int MOD = 1000_000_007;
    private static Set<String> set;

    private static void memo(int n, int k, int m_, int p_, int size, Set<Integer> maths, Set<Integer> physics,
            int idx) {
        if (idx >= k * k)
            return;

        if (size == n) {
            String key = maths + "," + physics;
            if (!set.contains(key))
                res++;
            set.add(key);

            memo(n, k, 0, 0, 0, new HashSet<>(), new HashSet<>(), idx + n);
            // return;
        }

        for (int m = m_; m < k; m++) {
            if (maths.contains(m))
                continue;

            for (int p = p_; p < k; p++) {
                if (physics.contains(p))
                    continue;

                if (canTake(m, p, maths, physics)) {
                    maths.add(m);
                    physics.add(p);

                    // System.out.println("maths: " + maths + " physics: " + physics + " idx: " +
                    // idx);
                    memo(n, k, m + 1, p + 1, size + 1, maths, physics, idx);

                    maths.remove(m);
                    physics.remove(p);
                }
            }
        }
    }

    private static boolean canTake(int m, int p, Set<Integer> maths, Set<Integer> physics) {
        if (maths.isEmpty())
            return true;

        for (int mth : maths) {
            for (int phy : physics) {
                if (Math.abs(m - mth) == Math.abs(p - phy))
                    return false;
            }
        }

        return true;
    }

    // private static int helper(int n, int k) {
    //     res = 0;

    //     List<int[]> combos = new ArrayList<>();
    //     for (int i = 0; i < k; i++) {
    //         for (int j = 0; j < k; j++) {
    //             combos.add(new int[] { i, j });
    //         }
    //     }

    //     backtrack(0, combos, n, new boolean[k], new boolean[k], new ArrayList<>());
    //     return res % MOD;
    // }

    // private static void backtrack(int idx, List<int[]> combos, int n, boolean[] maths, boolean[] physics,
    //         List<int[]> path) {
    //     if (path.size() == n) {
    //         res = (res + 1) % MOD;
    //         return;
    //     }

    //     for (int i = idx; i < combos.size(); i++) {
    //         int[] combo = combos.get(i);
    //         int m = combo[0], p = combo[1];

    //         if (maths[m] || physics[p])
    //             continue;

    //         boolean flag = true;
    //         for (int[] c : path) {
    //             if (Math.abs(m - c[0]) == Math.abs(p - c[1])) {
    //                 flag = false;
    //                 break;
    //             }
    //         }

    //         if (!flag)
    //             continue;

    //         path.add(combo);
    //         maths[m] = true;
    //         physics[p] = true;

    //         backtrack(i + 1, combos, n, maths, physics, path);

    //         path.remove(path.size() - 1);
    //         maths[m] = false;
    //         physics[p] = false;
    //     }
    // }
}