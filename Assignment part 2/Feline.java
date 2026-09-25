// Feline family and their specific behaviors.

import java.util.Random;

public abstract class Feline extends Animal {
    private static final Random rand = new Random();

    public Feline(String name, Size size) {
        super(name, size);
    }

    @Override
    public void roam() {
        int roll = rand.nextInt(100);
        // Assignment 3 update: 10% chance to charge, 50% chance to sleep
        if (roll < 10) {
            System.out.println(getName() + " is roaming – and charged!");
        } else if (roll < 50) {
            System.out.println(getName() + " decided to sleep instead of roaming.");
        } else {
            System.out.println(getName() + " is prowling around.");
        }
    }
}

class Tiger extends Feline {
    public Tiger(String name, Size size) { super(name, size); }
    @Override
    public void makeSound() { System.out.println(getName() + " the Tiger says: *Roar!*"); }
}

class Lion extends Feline {
    public Lion(String name, Size size) { super(name, size); }
    @Override
    public void makeSound() { System.out.println(getName() + " the Lion says: *Loud Roar!*"); }
}

class Cheetah extends Feline {
    public Cheetah(String name, Size size) { super(name, size); }
    @Override
    public void makeSound() { System.out.println(getName() + " the Cheetah says: *Purr!*"); }
}