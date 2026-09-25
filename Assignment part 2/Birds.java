// Birds with updated phrases for Parrot class

import java.util.Random;

public abstract class Birds extends Animal {
    protected static final Random rand = new Random();

    public Birds(String name, Size size) {
        super(name, size);
    }

    @Override
    public void roam() {
        System.out.println(getName() + " is flying around.");
    }
}

class Parrot extends Birds {
    public Parrot(String name, Size size) { super(name, size); }
    
    @Override
    public void makeSound() { 
        // Assignment 3 update: 5 random phrases
        String[] phrases = {
            "*Squawk!*", 
            "Polly wants a cracker!", 
            "Hello there!", 
            "Who's a good bird?", 
            "Zoo time!"
        };
        String saying = phrases[rand.nextInt(phrases.length)];
        System.out.println(getName() + " the Parrot says: " + saying); 
    }
}

class Falcon extends Birds {
    public Falcon(String name, Size size) { super(name, size); }
    @Override
    public void makeSound() { System.out.println(getName() + " the Falcon says: *Screech!*"); }
}

class Owl extends Birds {
    public Owl(String name, Size size) { super(name, size); }
    @Override
    public void makeSound() { System.out.println(getName() + " the Owl says: *Hoot!*"); }
}