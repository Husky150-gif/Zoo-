import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private static Hospital instance;
    private final List<Animal> patients = new ArrayList<>();

    private Hospital() {
    }

    public static Hospital getInstance() {
        if (instance == null) {
            instance = new Hospital();
        }
        return instance;
    }

    public List<Animal> getPatients() {
        return patients;
    }

    public void addAnimal(Animal animal) {
        patients.add(animal);
    }

    public void removeAnimal(Animal animal) {
        patients.remove(animal);
    }
}
