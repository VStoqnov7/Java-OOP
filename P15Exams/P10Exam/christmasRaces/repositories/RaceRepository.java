package P15Exams.P10Exam.christmasRaces.repositories;

import P15Exams.P10Exam.christmasRaces.entities.races.Race;
import P15Exams.P10Exam.christmasRaces.repositories.interfaces.Repository;

import java.util.*;

public class RaceRepository implements Repository<Race> {

    private Map<String,Race> races;

    public RaceRepository() {
        this.races = new LinkedHashMap<>();
    }

    @Override
    public Race getByName(String name) {
        return races.get(name);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.races.values());
    }

    @Override
    public void add(Race race) {
        races.putIfAbsent(race.getName(),race);
    }

    @Override
    public boolean remove(Race race) {
        return races.remove(race.getName()) != null;
    }
}
