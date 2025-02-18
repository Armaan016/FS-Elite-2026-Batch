package Day4;
/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the subnet mask in dotted decimal 
format.

Input Format:
---------------
An integer, CIDR

Output Format:
---------------
String, Subnet's IP Address


Sample Input-1:
-----------------
25

Sample Output-1:
------------------
255.255.255.128


Sample Input-2:
-----------------
22

Sample Output-2:
------------------
255.255.252.0
*/

import java.util.*;

public class Subnet1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(getSubnetMask(n));

        sc.close();
    }

    private static String getSubnetMask(int n) {
        int mask = 0xFFFFFFFF << (32 - n);
        // System.out.println("mask: " + mask);

        return ((mask >> 24) & 255) + "." +
                ((mask >> 16) & 255) + "." +
                ((mask >> 8) & 255) + "." +
                (mask & 255);
    }
}
