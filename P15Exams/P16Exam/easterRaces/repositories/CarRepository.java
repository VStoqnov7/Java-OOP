package P15Exams.P16Exam.easterRaces.repositories;

import P15Exams.P16Exam.easterRaces.entities.cars.Car;
import P15Exams.P16Exam.easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CarRepository implements Repository<Car> {


    private Collection<Car> models;

    public CarRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Car getByName(String name) {
        return this.models.stream().filter(car -> car.getModel().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Car model) {
        models.add(model);
    }

    @Override
    public boolean remove(Car model) {
        if (models.contains(model)){
            models.remove(model);
            return true;
        }
        return false;
    }
}
