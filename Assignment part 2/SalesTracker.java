import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesTracker { 
    // Assignment 3 update: New SalesTracker class added to track sales and exit bonuses for Vendors.
    private static SalesTracker instance = null; 

    private Map<String, Integer> visits = new HashMap<>();
    private Map<String, Integer> sales = new HashMap<>();
    private Map<String, Double> moneyMade = new HashMap<>();
    private Map<String, Integer> exits = new HashMap<>();

    private SalesTracker() { }

    public static SalesTracker getInstance() {
        if (instance == null) {
            instance = new SalesTracker();
        }
        return instance;
    }

// Event receivers for tracking visits, sales, and exits
    public void recordVisit(String shopName) { 
        
        visits.put(shopName, visits.getOrDefault(shopName, 0) + 1);
    }

    public void recordSale(String shopName, double amount) { 
        sales.put(shopName, sales.getOrDefault(shopName, 0) + 1);
        moneyMade.put(shopName, moneyMade.getOrDefault(shopName, 0.0) + amount);
    }

    public void recordExit(String shopName) { 
        exits.put(shopName, exits.getOrDefault(shopName, 0) + 1);
    }

    public void printSummary(List<Shop> shops) {
        System.out.println("\n--- DAILY SHOP SALES SUMMARY ---");
        for (Shop shop : shops) {
            String name = shop.getName();
            System.out.printf("Shop: %s | Visits: %d | Sales: %d | Money Made: $%.2f | Exits: %d%n",
                    name,
                    visits.getOrDefault(name, 0),
                    sales.getOrDefault(name, 0),
                    moneyMade.getOrDefault(name, 0.0),
                    exits.getOrDefault(name, 0));

                    visits.put(name, 0);
                    sales.put(name, 0);
                    moneyMade.put(name, 0.0);
                    exits.put(name, 0);
    
        }
        System.out.println("--- END OF SUMMARY ---\n");
    }
    }
