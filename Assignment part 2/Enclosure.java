import java.util.ArrayList;
import java.util.List;

public class Enclosure {
    private final String name;
    private final List<Animal> animals;

    public Enclosure(String name) {
        this.name = name;
        this.animals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public String getAnimalType() {
        if (animals.isEmpty()) {
            return "Unknown";
        }
        return animals.get(0).getClass().getSimpleName();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }
}