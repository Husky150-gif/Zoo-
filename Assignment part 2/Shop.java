// Shop class representing a shop in the zoo simulation.
// It manages inventory, pricing, and visitor interactions, including sales and exits.
// The shop uses a Vendor to determine sales behavior and interacts with the SalesTracker to record events.
import java.util.Random;

public class Shop {
    private final String name;
    private int inventory;
    private final double price;
    private final double likelihood;
    private double cash;
    private Vendor vendor;
    private final Random rand = new Random();

    public Shop() {
        this("Zoo Shop");
    }

    public Shop(String name) {
        this.name = name;
        this.inventory = 100 + rand.nextInt(101);
        this.price = 1.0 + (rand.nextDouble() * 9.0);
        this.likelihood = 0.10 + (rand.nextDouble() * 0.15);
        this.cash = this.inventory * (this.price / 2.0);
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public int getInventory() {
        return inventory;
    }

    public double getCash() {
        return cash;
    }

    public void addInventory(int amount) {
        this.inventory += amount;
        this.cash -= (amount * (this.price / 2.0));
    }

    public boolean processVisitor() {
        return processVisitor(vendor);
    }

    public boolean processVisitor(Vendor visitorVendor) {
        if (visitorVendor == null) {
            return false;
        }

        SalesTracker tracker = SalesTracker.getInstance();
        tracker.recordVisit(this.name);

        double saleBonus = visitorVendor.getBehavior().getSalesBonus();
        double exitBonus = visitorVendor.getBehavior().getExitBonus();
        double finalSaleChance = Math.min(1.0, this.likelihood + saleBonus);
        double finalExitChance = Math.min(1.0, 0.05 + exitBonus);

        if (this.inventory > 0 && rand.nextDouble() < finalSaleChance) {
            this.inventory--;
            this.cash += this.price;
            tracker.recordSale(this.name, this.price);
        }

        if (rand.nextDouble() < finalExitChance) {
            tracker.recordExit(this.name);
            return true;
        }

        return false;
    }
}
