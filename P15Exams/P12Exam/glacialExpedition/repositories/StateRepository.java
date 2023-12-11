package P15Exams.P12Exam.glacialExpedition.repositories;

import P15Exams.P12Exam.glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StateRepository implements Repository<State>{

    private Map<String,State> states;

    public StateRepository() {
        this.states = new LinkedHashMap<>();
    }

    @Override
    public Collection<State> getCollection() {
        return Collections.unmodifiableCollection(states.values());
    }

    @Override
    public void add(State entity) {
        states.putIfAbsent(entity.getName(), entity);
    }

    @Override
    public boolean remove(State entity) {
        if (states.containsKey(entity.getName())){
            states.remove(entity.getName());
            return true;
        }
        return false;
    }

    @Override
    public State byName(String name) {
        if (states.containsKey(name)){
            return states.get(name);
        }
        return null;
    }
}
