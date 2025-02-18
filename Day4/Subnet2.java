package Day4;
/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

Input Format:
---------------
A String, IPAddress
An integer, CIDR

Output Format:
---------------
Space separated IP addresses, network IP and broadcast IP.


Sample Input-1:
-----------------
192.168.1.10
24

Sample Output-1:
------------------
192.168.1.0 192.168.1.255


Sample Input-2:
-----------------
192.0.2.1
24

Sample Output-2:
------------------
192.0.2.0 192.0.2.255

*/

import java.util.*;

public class Subnet2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String ip = sc.nextLine();
        int cidr = sc.nextInt();

        System.out.println(getAddresses(ip, cidr));

        sc.close();
    }

    private static String getAddresses(String ip, int cidr) {
        String[] ipParts = ip.split("\\.");
        int ipNum = (Integer.parseInt(ipParts[0]) << 24) |
                (Integer.parseInt(ipParts[1]) << 16) |
                (Integer.parseInt(ipParts[2]) << 8) |
                Integer.parseInt(ipParts[3]);

        int mask = 0xFFFFFFFF << (32 - cidr);
        int network = ipNum & mask;
        int broadcast = network | ~mask;

        return (((network >> 24) & 255) + "." +
                ((network >> 16) & 255) + "." +
                ((network >> 8) & 255) + "." +
                (network & 255) + " " +
                ((broadcast >> 24) & 255) + "." +
                ((broadcast >> 16) & 255) + "." +
                ((broadcast >> 8) & 255) + "." +
                (broadcast & 255));
    }
}
