import java.util.*;

public class SlidingWindowExtra1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int p = sc.nextInt();

        int[] color = new int[n];
        for(int i = 0; i < n; i++) {
            color[i] = sc.nextInt();
        }

        int[] res = uniqueBalloons(color, n, p);
        System.out.println(Arrays.toString(res));

        sc.close();
    }

    private static int[] uniqueBalloons(int[] colors, int n, int p) {
        int[] unique = new int[n - p + 1];

        int i = 0, j = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while(j < n) {
            map.put(colors[j], map.getOrDefault(colors[j], 0) + 1);
            // System.out.println("Map: " + map);

            int len = j - i + 1;
            if(len == p) {
                unique[i] = map.size();
                // System.out.println("Unique: " + unique[i] + " i: " + i + " j: " + j);

                map.put(colors[i], map.get(colors[i]) - 1);
                if(map.get(colors[i]) == 0) {
                    map.remove(colors[i]);
                }

                i++;
            }

            j++;
        }

        return unique;
    }
}
