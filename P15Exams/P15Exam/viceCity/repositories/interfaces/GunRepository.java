package P15Exams.P15Exam.viceCity.repositories.interfaces;

import P15Exams.P15Exam.viceCity.models.guns.Gun;

import java.util.*;

public class GunRepository implements Repository<Gun>{

    private Map<String,Gun> models;

    public GunRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Gun model) {
        this.models.putIfAbsent(model.getName(),model);
    }

    @Override
    public boolean remove(Gun model) {
        if (models.containsKey(model.getName())){
            models.remove(model.getName());
            return true;
        }
        return false;
    }

    @Override
    public Gun find(String name) {
        return this.models.get(name);
    }
}
