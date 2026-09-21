public  abstract class Reptile extends Animal {
    public Reptile(String name, Size size) {
        super(name, size);
    }
    // Reptile family specific behaviors can be added here
    @Override
    public void roam() {
        System.out.println(getName() + " is crawling around.");
    }
}
class Lizard extends Reptile {
    public Lizard(String name, Size size) {
        super(name, size);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " makes a subtle hissing sound!");
    }

    public void sun() {
        System.out.println(getName() + " is basking in the sun.");
    }

    public void shed() {
        System.out.println(getName() + " is shedding its skin.");
    }
}

class Alligator extends Reptile {
    public Alligator(String name, Size size) {
        super(name, size);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " growls deeply!");
    }
}

class Chameleon extends Reptile {
    public Chameleon(String name, Size size) {
        super(name, size);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " makes a soft clicking sound!");
    }
} 

class Python extends Reptile {
    public Python(String name, Size size) {
        super(name, size);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " hisses loudly!");
    }
}
