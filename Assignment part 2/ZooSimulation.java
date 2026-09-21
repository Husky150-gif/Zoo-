// updated ZooSimulation.java to include new features for Assignment 3: Shops, Vendors, and SalesBehavior strategy pattern.
// logic for Vendors to manage shops, track sales, and provide exit bonuses for visitors. Singleton pattern used for SalesTracker and Hospital classes to ensure a single instance is used throughout the simulation.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ZooSimulation {

    private List<Enclosure> enclosures = new ArrayList<>();
    private List<Handler> handlers = new ArrayList<>();
    private List<Shop> shops = new ArrayList<>();
    private List<Vendor> vendors = new ArrayList<>();
    private Veterinarian vet = new Veterinarian("Dr. Valerie");
    private Map<String, String> speciesToFamilyMap = new HashMap<>();
    private Random rand = new Random();

    public void initializeZoo() {
        // Map species to family group
        speciesToFamilyMap.put("Rhino", "Pachyderm");
        speciesToFamilyMap.put("Elephant", "Pachyderm");
        speciesToFamilyMap.put("Hippo", "Pachyderm");
        speciesToFamilyMap.put("Tiger", "Feline");
        speciesToFamilyMap.put("Lion", "Feline");
        speciesToFamilyMap.put("Cheetah", "Feline");
        speciesToFamilyMap.put("Parrot", "Birds");
        speciesToFamilyMap.put("Falcon", "Birds");
        speciesToFamilyMap.put("Owl", "Birds");
        speciesToFamilyMap.put("Alligator", "Reptile");
        speciesToFamilyMap.put("Chameleon", "Reptile");
        speciesToFamilyMap.put("Python", "Reptile");

        // Family handlers
        handlers.add(new Handler("Alice", "Pachyderm"));
        handlers.add(new Handler("Bob", "Feline"));
        handlers.add(new Handler("Charlie", "Birds"));
        handlers.add(new Handler("Diana", "Reptile"));

        // Enclosures with 3 animals each
        createEnclosureWithAnimals("Rhino", new Rhino("Rocco", Size.LARGE), new Rhino("Rita", Size.LARGE), new Rhino("Rocky", Size.LARGE));
        createEnclosureWithAnimals("Elephant", new Elephant("Ella", Size.EXTRA_LARGE), new Elephant("Eddie", Size.EXTRA_LARGE), new Elephant("Elmer", Size.EXTRA_LARGE));
        createEnclosureWithAnimals("Hippo", new Hippo("Hannah", Size.EXTRA_LARGE), new Hippo("Harry", Size.EXTRA_LARGE), new Hippo("Hazel", Size.EXTRA_LARGE));
        createEnclosureWithAnimals("Tiger", new Tiger("Tina", Size.LARGE), new Tiger("Tom", Size.LARGE), new Tiger("Toby", Size.LARGE));
        createEnclosureWithAnimals("Lion", new Lion("Leo", Size.LARGE), new Lion("Lara", Size.LARGE), new Lion("Liam", Size.LARGE));
        createEnclosureWithAnimals("Cheetah", new Cheetah("Chloe", Size.MEDIUM), new Cheetah("Charlie", Size.MEDIUM), new Cheetah("Cody", Size.MEDIUM));
        createEnclosureWithAnimals("Parrot", new Parrot("Polly", Size.SMALL), new Parrot("Pete", Size.SMALL), new Parrot("Penny", Size.SMALL));
        createEnclosureWithAnimals("Falcon", new Falcon("Fiona", Size.MEDIUM), new Falcon("Fred", Size.MEDIUM), new Falcon("Faith", Size.MEDIUM));
        createEnclosureWithAnimals("Owl", new Owl("Oscar", Size.SMALL), new Owl("Olivia", Size.SMALL), new Owl("Ollie", Size.SMALL));
        createEnclosureWithAnimals("Alligator", new Alligator("Ally", Size.LARGE), new Alligator("Albert", Size.LARGE), new Alligator("Ava", Size.LARGE));
        createEnclosureWithAnimals("Chameleon", new Chameleon("Charlie", Size.SMALL), new Chameleon("Cathy", Size.SMALL), new Chameleon("Carl", Size.SMALL));
        createEnclosureWithAnimals("Python", new Python("Penny", Size.MEDIUM), new Python("Paul", Size.MEDIUM), new Python("Pam", Size.MEDIUM));

        // Setup Assignment 3 Shops & Vendors
        String[] shopNames = {"Gifts", "Maps", "Drinks", "Food", "Toys"};
        SalesBehavior[] behaviors = {new NoSell(), new SoftSell(), new NormalSell(), new HardSell(), new SoftSell()};
        String[] vendorNames = {"Vince", "Val", "Victor", "Vera", "Vaughn"};

        for (int i = 0; i < 5; i++) {
            Shop newShop = new Shop(shopNames[i]);
            Vendor newVendor = new Vendor(vendorNames[i], newShop, behaviors[i]);
            newShop.setVendor(newVendor);
            shops.add(newShop);
            vendors.add(newVendor);
        }
    }

    private void createEnclosureWithAnimals(String type, Animal a1, Animal a2, Animal a3) {
        Enclosure enc = new Enclosure(type);
        enc.addAnimal(a1);
        enc.addAnimal(a2);
        enc.addAnimal(a3);
        enclosures.add(enc);
    }

    public void runSimulation(int days) {
        for (int day = 1; day <= days; day++) {
            System.out.println("==========================================");
            System.out.println("            START OF DAY " + day);
            System.out.println("==========================================");

            // Vendors prep shops at the start of the day
            for (Vendor vendor : vendors) { vendor.prepareShop(); }
            
            for (Handler handler : handlers) { handler.wakeAnimals(getAnimalsForHandler(handler)); }
            
            // Replaced "hospital" with Singleton "Hospital.getInstance()"
            for (Handler handler : handlers) { handler.feedAnimals(getAnimalsForHandler(handler), Hospital.getInstance()); }

            displayZooStatus();
            
            // New Event: sellItems triggers directly after zooStatus
            sellItems();

            for (Handler handler : handlers) { handler.exerciseAnimals(getAnimalsForHandler(handler)); }
            
            // Vet treats animals using the Singleton Hospital
            vet.treatAnimals(Hospital.getInstance(), enclosures);
            
            for (Handler handler : handlers) { handler.bedAnimals(getAnimalsForHandler(handler)); }

            System.out.println("\n==========================================");
            System.out.println("             END OF DAY " + day);
            System.out.println("==========================================\n");
        }
    }

    private void sellItems() {
        int visitors = 50 + rand.nextInt(51); 
        System.out.println("\nToday we have " + visitors + " visitors!");

        for (int i = 0; i < visitors; i++) {
            for (Vendor vendor : vendors) {
                boolean exited = vendor.processVisitor();
                if (exited) {
                    break;
                }
            }
        }
    }

    private List<Animal> getAnimalsForHandler(Handler handler) {
        List<Animal> handlerAnimals = new ArrayList<>();
        for (Enclosure enc : enclosures) {
            String family = speciesToFamilyMap.get(getEnclosureAnimalType(enc));
            if (family != null && family.equalsIgnoreCase(handler.getAssignedFamily())) {
                handlerAnimals.addAll(enc.getAnimals());
            }
        }
        return handlerAnimals;
    }
    
    private String getEnclosureAnimalType(Enclosure enclosure) {
        if (enclosure.getAnimals().isEmpty()) {
            return "Unknown";
        }
        return enclosure.getAnimals().get(0).getClass().getSimpleName();
    }

    private void displayZooStatus() {
        System.out.println("\n----------- ZOO STATUS REPORT -----------");
        for (Enclosure enc : enclosures) {
            List<String> animalNames = new ArrayList<>();
            for (Animal a : enc.getAnimals()) { animalNames.add(a.getName()); }
            System.out.printf("Enclosure: %-12s | Animals: %s%n", enc.getAnimalType(), animalNames.isEmpty() ? "[Empty]" : String.join(", ", animalNames));
        }

        System.out.println("\nHOSPITAL PATIENTS:");
        if (Hospital.getInstance().getPatients().isEmpty()) {
            System.out.println("  [None]");
        } else {
            for (Animal patient : Hospital.getInstance().getPatients()) {
                System.out.println("  - " + patient.getName());
            }
        }
        
        // Print observer stats from the Singleton SalesTracker
        SalesTracker.getInstance().printSummary(shops);
    }

    public static void main(String[] args) {
        ZooSimulation sim = new ZooSimulation();
        sim.initializeZoo();
        sim.runSimulation(30);
    }
}