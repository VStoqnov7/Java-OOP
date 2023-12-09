package P15Exams.P06Exam.goldDigger.repositories;

import P15Exams.P06Exam.goldDigger.models.spot.Spot;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpotRepository implements Repository<Spot>{

    private Map<String,Spot> spots;

    public SpotRepository() {
        this.spots = new LinkedHashMap<>();
    }

    @Override
    public Collection<Spot> getCollection() {
        return Collections.unmodifiableCollection(spots.values());
    }

    @Override
    public void add(Spot spot) {
        spots.putIfAbsent(spot.getName(),spot);
    }

    @Override
    public boolean remove(Spot spot) {
        if (spots.containsKey(spot.getName())){
            spots.remove(spot);
            return true;
        }
        return false;
    }

    @Override
    public Spot byName(String name) {
        if (spots.containsKey(name)){
            return spots.get(name);
        }
        return null;
    }
}
