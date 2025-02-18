package Day4;

// Given two IP addresses IP1 and IP2, and a subnet mask, your task is to check 
// whether IP-1 and IP-2 belongs to same host range or not.

// Input Format:
// ---------------
// Two space separated strings, IP1 and IP2.
// An integer, CIDR (subnet mask).

// Output Format:
// ---------------
// A boolean result.

// Sample Input-1:
// -----------------
// 192.168.1.10 192.168.1.20
// 24

// Sample Output-1:
// ------------------
// true

// Sample Input-2:
// -----------------
// 192.0.2.1 192.0.3.253
// 24

// Sample Output-2:
// ------------------
// false

import java.util.*;

public class Subnet3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String ip1 = sc.next();
        String ip2 = sc.next();
        int cidr = sc.nextInt();

        System.out.println(isSameHostRange(ip1, ip2, cidr));

        sc.close();
    }

    private static boolean isSameHostRange(String ip1, String ip2, int cidr) {
        int mask = 0xFFFFFFFF << (32 - cidr);
        int ip1Num = convertToInt(ip1);
        int ip2Num = convertToInt(ip2);

        return (ip1Num & mask) == (ip2Num & mask);
    }

    private static int convertToInt(String ip) {
        String[] ipParts = ip.split("\\.");
        return (Integer.parseInt(ipParts[0]) << 24) |
                (Integer.parseInt(ipParts[1]) << 16) |
                (Integer.parseInt(ipParts[2]) << 8) |
                Integer.parseInt(ipParts[3]);
    }
}
