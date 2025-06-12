package Day71;

// You are building memory management for a smart home hub where apps are loaded on-demand. 
// The hub has limited memory (cache size). If an app is not in memory (cache miss), 
// it is loaded and a page fault occurs. If memory is full, the Least Recently Used 
// (LRU) app is removed.

// Simulate the LRU page replacement and count total page faults.

// Input Format:
// -------------
// - Cache size
// - Space-separated app access sequence (e.g., Thermostat Camera Lights)

// Output Format:
// --------------
// Total page faults
// Final cache contents

// Sample Input:
// -------------
// 3
// Thermostat Camera Lights Thermostat Camera Doorbell Lights Thermostat

// Sample Output:
// --------------
// Total Page Faults: 6
// Final Cache: [Doorbell, Lights, Thermostat]

// Sample Input:
// --------------
// 2
// AC Light Fan AC Heater Light

// Sample Output:
// --------------
// Total Page Faults: 6
// Final Cache: [Heater, Light]

import java.util.*;

public class LRUCache {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cacheSize = sc.nextInt();

        sc.nextLine();
        String[] seq = sc.nextLine().split(" ");

        getLRU(cacheSize, seq);
        sc.close();
    }

    private static void getLRU(int size, String[] seq) {
        List<String> cache = new LinkedList<>();
        int faults = 0;

        // int idx = 0;
        for (int i = 0; i < seq.length; i++) {
            String s = seq[i];

            if (cache.contains(s)) {
                cache.remove(s);
                cache.add(s);
                continue;
            } else {
                faults++;
                if (cache.size() == size)
                    cache.remove(0);

                // System.out.println("Adding: " + s + " cache: " + cache);
                cache.add(s);
            }
        }

        System.out.println("Total Page Faults: " + faults);
        System.out.println("Final Cache: " + cache);
    }
}