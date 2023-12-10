package P15Exams.P11Exam.catHouse.repositories;

import P15Exams.P11Exam.catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;
public class ToyRepository implements Repository{

    private Collection<Toy> toys;

    public ToyRepository() {
        this.toys = new ArrayList<>();
    }

    @Override
    public void buyToy(Toy toy) {
        toys.add(toy);
    }

    @Override
    public boolean removeToy(Toy toy) {
        if (toys.contains(toy)){
            toys.remove(toy);
            return true;
        }
        return false;
    }

    @Override
    public Toy findFirst(String type) {
        return toys.stream().filter(toy -> toy.getClass().getSimpleName().equals(type)).findFirst().orElse(null);
    }
}
