import java.util.Random;

// Pachyderm family 
public abstract class Pachyderm extends Animal {
    private final Random random = new Random();

    public Pachyderm(String name, Size size) {
        super(name, size);
    }

    // Have a 25% chance to charge while roaming
    @Override
    public void roam() {
        if (random.nextInt(100) < 25) {
            System.out.println(getName() + " is charging!");
        } else {
            System.out.println(getName() + " is roaming peacefully.");
        }
    }

}

class Rhino extends Pachyderm {
    public Rhino(String name, Size size) {
        super(name, size);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " trumpets loudly!");
    }
}

class Elephant extends Pachyderm {
    public Elephant(String name, Size size) {
        super(name, size);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " trumpets loudly!");
    }
}

class Hippo extends Pachyderm {
    public Hippo(String name, Size size) {
        super(name, size);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " grunts loudly!");
    }
}