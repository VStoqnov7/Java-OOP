package P15Exams.P12Exam.glacialExpedition.repositories;

import P15Exams.P12Exam.glacialExpedition.models.explorers.Explorer;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExplorerRepository implements Repository<Explorer>{

    private Map<String,Explorer> explorers;

    public ExplorerRepository() {
        this.explorers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableCollection(explorers.values());
    }

    @Override
    public void add(Explorer entity) {
        explorers.putIfAbsent(entity.getName(),entity);
    }

    @Override
    public boolean remove(Explorer entity) {
        if (explorers.containsKey(entity.getName())){
            explorers.remove(entity.getName());
            return true;
        }
        return false;
    }

    @Override
    public Explorer byName(String name) {
        if (explorers.containsKey(name)){
            return explorers.get(name);
        }
        return null;
    }
}
