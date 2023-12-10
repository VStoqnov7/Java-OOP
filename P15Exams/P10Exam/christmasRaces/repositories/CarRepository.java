package P15Exams.P10Exam.christmasRaces.repositories;


import P15Exams.P10Exam.christmasRaces.entities.cars.Car;
import P15Exams.P10Exam.christmasRaces.repositories.interfaces.Repository;

import java.util.*;


public class CarRepository implements Repository<Car> {

    private Map<String,Car> cars;

    public CarRepository() {
        this.cars = new LinkedHashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return cars.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(this.cars.values());
    }

    @Override
    public void add(Car car) {
        cars.putIfAbsent(car.getModel(),car);
    }

    @Override
    public boolean remove(Car car) {
       return cars.remove(car.getModel()) != null;
    }
}
