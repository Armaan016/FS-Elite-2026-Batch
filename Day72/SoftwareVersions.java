package Day72;

// You are given a set of releases of a software and you are asked to find the most
// recent release. There may be multiple checkins of the software in a given branch. 
// Each branch may also have sub branches. For example release 3-5-4 refers to the 
// fourth checkin in the fifth sub branch of the third main branch. 
// This hierarchy can go upto any number of levels. 

// If a level is missing it is considered as level 0 in that hierarchy. 
// For example 3-5-7 is  same as 3-5-7-0 or even same as 3-5-7-0-0. 
// The higher numbers denote more recent releases. 

// For example 3-5-7-1 is more recent than 3-5-7 but less recent than 3-6.

// Input Format:
// -------------
// A single line space separated strings, list of releases 

// Output Format:
// --------------
// Print the latest release of the software.

// Sample Input-1:
// ---------------
// 1-2 1-2-3-0-0 1-2-3

// Sample Output-1:
// ----------------
// 1-2-3

// Sample Input-2:
// ---------------
// 3-5-4 3-5-7 3-5-7-1 3-5-7-0-0 3-6

// Sample Output-2:
// ----------------
// 3-6

import java.util.*;

public class SoftwareVersions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] versions = sc.nextLine().split(" ");

        System.out.println(getLatestVersion(versions));
        sc.close();
    }

    private static String getLatestVersion(String[] versions) {
        List<String> trimmed = new ArrayList<>();
        Map<String, String> untrimmed = new HashMap<>();

        for (String version : versions) {
            String t = trimVersion(version);

            untrimmed.put(version, t);
            trimmed.add(t);
        }

        Collections.sort(trimmed, (a, b) -> b.compareTo(a));
        // System.out.println(trimmed);
        // System.out.println(untrimmed);

        String latest = trimmed.get(0);
        String res = "-1";

        for (Map.Entry<String, String> e : untrimmed.entrySet()) {
            if (e.getValue().equals(latest)) {
                if (res.equals("-1"))
                    res = e.getKey();
                else if (res.length() > e.getKey().length()) {
                    res = e.getKey();
                }
            }
        }

        return res;
    }

    private static String trimVersion(String s) {
        // while(s.indexOf("-0") == s.length() - 2) {
        // s = s.replace("-0", "");
        // }

        s = s.replace("-0", "");
        return s;
    }
}