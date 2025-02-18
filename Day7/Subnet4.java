package Day7;

import java.util.*;

public class Subnet4 {
    public static void main (String[] args) {
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
        
        for(int i = 0; i < n; i++) {
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