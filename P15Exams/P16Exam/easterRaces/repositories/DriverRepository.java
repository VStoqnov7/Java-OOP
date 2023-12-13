package P15Exams.P16Exam.easterRaces.repositories;

import P15Exams.P16Exam.easterRaces.entities.drivers.Driver;
import P15Exams.P16Exam.easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DriverRepository implements Repository<Driver> {

    private Collection<Driver> models;


    public DriverRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Driver getByName(String name) {
        return this.models.stream().filter(driver -> driver.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Driver model) {
        models.add(model);
    }

    @Override
    public boolean remove(Driver model) {
        if (models.contains(model)){
            models.remove(model);
            return true;
        }
        return false;
    }
}
