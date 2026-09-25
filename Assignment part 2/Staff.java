import java.util.List;
import java.util.Random;

public abstract class Staff {
    private final String name;

    public Staff(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Handler extends Staff {
    private final String assignedFamily;

    public Handler(String name, String assignedFamily) {
        super(name);
        this.assignedFamily = assignedFamily;
    }

    public String getAssignedFamily() {
        return assignedFamily;
    }

    public void wakeAnimals(List<Animal> animals) {
        System.out.println("\n" + getName() + " the " + assignedFamily + " Handler walks through the enclosure, gently waking up the animals for a new day.");
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }

    public void feedAnimals(List<Animal> animals, Hospital hospital) {
        System.out.println("\n" + getName() + " the " + assignedFamily + " Handler begins the feeding routine, handing out breakfast to the hungry animals.");
        Random rand = new Random();
        for (int i = animals.size() - 1; i >= 0; i--) {
            Animal animal = animals.get(i);
            animal.eat();
            if (rand.nextBoolean()) {
                animal.setHealthy(false);
                System.out.println("ANNOUNCEMENT: " + animal.getName() + " isn't looking too good after eating and is being safely transported to the hospital for a checkup.");
                hospital.addAnimal(animal);
                animals.remove(i);
            }
        }
    }

    public void exerciseAnimals(List<Animal> animals) {
        System.out.println("\n" + getName() + " the " + assignedFamily + " Handler opens up the activity yard so the animals can get some fresh air and stretch their legs.");
        for (Animal animal : animals) {
            animal.roam();
        }
    }

    public void bedAnimals(List<Animal> animals) {
        System.out.println("\n" + getName() + " the " + assignedFamily + " Handler dims the habitat lights and tucks the animals in for a good night's rest.");
        for (Animal animal : animals) {
            animal.sleep();
        }
    }
}

class Veterinarian extends Staff {
    public Veterinarian(String name) {
        super(name);
    }

    private boolean matchesAnimalType(Enclosure enclosure, Animal animal) {
        try {
            java.lang.reflect.Method method = Enclosure.class.getMethod("getAnimalType");
            Object enclosureType = method.invoke(enclosure);
            return enclosureType != null && enclosureType.toString().equalsIgnoreCase(animal.getClass().getSimpleName());
        } catch (Exception e) {
            return false;
        }
    }

    public void treatAnimals(Hospital hospital, List<Enclosure> enclosures) {
        System.out.println("\n" + getName() + " the Vet starts their rounds in the hospital ward, carefully checking on all the sick animals.");
        List<Animal> patients = hospital.getPatients();
        Random rand = new Random();

        for (int i = patients.size() - 1; i >= 0; i--) {
            Animal animal = patients.get(i);
            if (rand.nextBoolean()) {
                animal.setHealthy(true);
                System.out.println(animal.getName() + " has made a full recovery and is happily heading back to its home enclosure!");
                hospital.removeAnimal(animal);
                boolean returned = false;
                for (Enclosure enc : enclosures) {
                    if (matchesAnimalType(enc, animal)) {
                        enc.addAnimal(animal);
                        returned = true;
                        break;
                    }
                }
                if (!returned) {
                    System.out.println("No matching enclosure was found for " + animal.getName() + ", so it remains in the hospital temporarily.");
                }
            } else {
                System.out.println(animal.getName() + " needs a little more rest and will stay in the hospital for further treatment.");
            }
        }
    }
}

class Vendor extends Staff {
    private final Shop assignedShop;
    private final SalesBehavior behavior;

    public Vendor(String name, Shop assignedShop, SalesBehavior behavior) {
        super(name);
        this.assignedShop = assignedShop;
        this.behavior = behavior;
    }

    public SalesBehavior getBehavior() {
        return behavior;
    }

    public void prepareShop() {
        if (assignedShop.getInventory() < 20) {
            System.out.println(getName() + " checks the shelves at " + assignedShop.getName() + " and realizes they are looking pretty bare, so they quickly restock 100 fresh items for the day.");
            assignedShop.addInventory(100);
        }
    }

    public boolean processVisitor() {
        return assignedShop.processVisitor(this);
    }
}