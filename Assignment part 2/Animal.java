// Jenna Johnson
// CPSP - Assignment 2 part 2
// Class to represent an animal with a name and size

import java.util.Random;

// Inheritance: Base class for animal hierarchy 
// Cohesion: Keeps state and actions related to an animal together
public abstract class Animal {
    private String name;
    private Size size;
    private boolean healthy;
    protected Random random = new Random();

    public Animal(String name, Size size) {
        this.name = name;
        this.size = size;
        this.healthy = true; // Default to healthy
    }

    public String getName() { return name; }
    public Size getSize() { return size; }
    public boolean isHealthy() { return healthy; }
    public String getStatus() { return healthy ? "Healthy" : "Sick"; }
    
    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    // Common sleep method shared with all animals 
    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    // Eating method shared with all animals with 10% chance edge cases
    public void eat() {
        int roll = random.nextInt(100);
        if (roll < 10) {
            System.out.println(name + " is not feeling well and refuses to eat.");
            healthy = false;
        } else if (roll < 20) {
            System.out.println(name + " ate way too much food and feels sick.");
            healthy = false;
        } else {
            System.out.println(name + " is eating.");
        }

        System.out.println(name + " is now " + (healthy ? "healthy." : "not healthy."));

    
    }
    
    // Concept: Polymorphism - Subclasses will provide specific implementations for actions like makeSound()
    public abstract void makeSound();
    public abstract void roam();
}