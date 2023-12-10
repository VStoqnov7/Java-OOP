package P15Exams.P10Exam.christmasRaces.repositories;


import P15Exams.P10Exam.christmasRaces.entities.drivers.Driver;
import P15Exams.P10Exam.christmasRaces.repositories.interfaces.Repository;

import java.util.*;

public class DriverRepository implements Repository<Driver> {

    private Map<String,Driver> drivers;

    public DriverRepository() {
        this.drivers = new LinkedHashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        return drivers.get(name);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(this.drivers.values());
    }

    @Override
    public void add(Driver driver) {
        drivers.putIfAbsent(driver.getName(),driver);
    }

    @Override
    public boolean remove(Driver driver) {
       return drivers.remove(driver.getName()) != null;
    }
}
