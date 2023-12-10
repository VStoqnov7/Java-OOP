package P15Exams.P09Exam.fairyShop.repositories;

import P15Exams.P09Exam.fairyShop.models.Present;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PresentRepository implements Repository<Present>{

    private Map<String,Present> presents;

    public PresentRepository() {
        this.presents = new LinkedHashMap<>();
    }

    @Override
    public Collection<Present> getModels() {
        return Collections.unmodifiableCollection(presents.values());
    }

    @Override
    public void add(Present present) {
        presents.putIfAbsent(present.getName(),present);
    }

    @Override
    public boolean remove(Present present) {
        if (presents.containsKey(present.getName())){
            presents.remove(present.getName());
            return true;
        }

        return false;
    }

    @Override
    public Present findByName(String name) {
        return presents.get(name);
    }
}
