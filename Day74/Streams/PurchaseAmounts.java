package Day74.Streams;

// Given a list of CustomerPurchase objects, each representing a purchase 
// with customerId, customerName, purchaseAmount, and category. 
// Compute the tier of customers based on total purchases 
// (only counting purchases ≥ 500.0).

// Tiers:
// - Platinum ≥ 5000
// - Gold ≥ 2000 and < 5000
// - Silver < 2000

// Note: Display the customers in descending order of their purchases of same tier.

// Example 1
// ---------

// Input:
// 4
// C101 Alice 1200 Electronics
// C102 Bob 499 Books
// C101 Alice 4500 Travel
// C103 Charlie 800 Furniture

// Output:
// C101 Alice : Platinum
// C103 Charlie : Silver

// Example 2
// ----------
// Input:
// 8
// C801 Mia 1000 Electronics
// C801 Mia 1200 Furniture
// C801 MIA 3000 Lighting
// C802 Olivia 1001 Apparel
// C803 Emma 1999 Jewelry
// C803 Emma 1 Books
// C804 Ava 2000 Appliances
// C805 Mia 1000 Garden

// Output:
// C801 Mia : Platinum
// C804 Ava : Gold
// C803 Emma : Silver
// C802 Olivia : Silver
// C805 Mia : Silver

import java.util.*;
import java.util.stream.*;

public class PurchaseAmounts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        List<CustomerPurchase> customers = new ArrayList<>();

        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String[] l = sc.nextLine().split(" ");

            customers.add(new CustomerPurchase(l[0], l[1], Double.parseDouble(l[2]), l[3]));
        }

        // System.out.println();
        sortPurchases(customers);
        sc.close();
    }

    private static void sortPurchases(List<CustomerPurchase> customers) {
        Map<String, Double> map = customers.stream().filter(c -> c.amount >= 500.0)
                .collect(Collectors.groupingBy(c -> (c.customerID + " " + c.name),
                        Collectors.summingDouble(c -> c.amount)));

        Map<String, Double> map2 = map.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        // System.out.println(map2);

        map2.forEach((key, val) -> {
            // - Platinum ≥ 5000
            // - Gold ≥ 2000 and < 5000
            // - Silver < 2000

            String tier;
            if (val >= 5000) {
                tier = "Platinum";
            } else if (val >= 2000) {
                tier = "Gold";
            } else {
                tier = "Silver";
            }

            System.out.println(key + " : " + tier);
        });
    }
}

class CustomerPurchase {
    String customerID;
    String name;
    double amount;
    String category;

    public CustomerPurchase(String customerID, String name, double amount, String category) {
        this.customerID = customerID;
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public Double getAmount() {
        return this.amount;
    }
}