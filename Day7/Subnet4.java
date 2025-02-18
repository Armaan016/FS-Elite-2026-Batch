package Day7;
// In computer networking, subnetting is the process of dividing a larger IP network

// into multiple smaller subnetworks (subnets). Given a base network IP address, 
// a CIDR (Classless Inter-Domain Routing) prefix, and the number of subnets required, 
// write a Java program that calculates the starting addresses of all the subnets.

// Your program should take as input:
// 	- A base network address (e.g., 192.168.1.0).
// 	- A CIDR prefix (e.g., /26 means a subnet mask of 255.255.255.192).
// 	- The number of subnets to generate.

// The program should then compute and return the starting addresses of each subnet.

// Input Format:
// -------------
// A string NIP: The base network IP address (e.g., "192.168.1.0").
// An integer CIDR: The subnet mask prefix (e.g., 26 for /26).
// An integer numSubnets: The number of subnets to be generated.

// Output Format:
// --------------
// A list of subnet addresses, each representing the starting address of a subnet.

// Sample Input:
// -------------
// 192.168.1.0
// 26
// 4

// Sample Output:
// --------------
// [192.168.1.0/28, 192.168.1.16/28, 192.168.1.32/28, 192.168.1.48/28]

// Explanation:
// ------------
// A /26 subnet has 64 IP addresses. The starting addresses of 
// the first four subnets are:
// 192.168.1.0/28, 
// 192.168.1.16/28, 
// 192.168.1.32/28, 
// 192.168.1.48/28

import java.util.*;

public class Subnet4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] ip = sc.nextLine().split("\\.");
        int cidr = sc.nextInt();
        int numSubnets = sc.nextInt();

        System.out.println(fetchSubnets(ip, cidr, numSubnets));

        sc.close();
    }

    private static List<String> fetchSubnets(String[] ip, int cidr, int n) {
        List<String> res = new ArrayList<>();

        int ipNum = (Integer.parseInt(ip[0]) << 24) |
                (Integer.parseInt(ip[1]) << 16) |
                (Integer.parseInt(ip[2]) << 8) |
                (Integer.parseInt(ip[3]));

        int bitsNeeded = (int) Math.ceil(Math.log(n) / Math.log(2));

        int newCidr = cidr + bitsNeeded;

        int perSubnet = (int) Math.pow(2, 32 - newCidr);

        for (int i = 0; i < n; i++) {
            int newIp = ipNum + (i * perSubnet);
            String intIp = convertToIp(newIp) + "/" + newCidr;

            res.add(intIp);
        }

        return res;
    }

    private static String convertToIp(int ip) {
        return (((ip >> 24) & 255) + "." + ((ip >> 16) & 255) + "." + ((ip >> 8) & 255) + "." + (ip & 255));
    }
}