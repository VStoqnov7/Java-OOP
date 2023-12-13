package P15Exams.P16Exam.easterRaces.repositories;

import P15Exams.P16Exam.easterRaces.entities.racers.Race;
import P15Exams.P16Exam.easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceRepository implements Repository<Race> {

    private Collection<Race> models;

    public RaceRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        return this.models.stream().filter(race -> race.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Race model) {
        models.add(model);
    }

    @Override
    public boolean remove(Race model) {
        if (models.contains(model)){
            models.remove(model);
            return true;
        }
        return false;
    }
}
