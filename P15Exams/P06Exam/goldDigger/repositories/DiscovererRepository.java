package P15Exams.P06Exam.goldDigger.repositories;

import P15Exams.P06Exam.goldDigger.models.discoverer.Discoverer;

import java.util.*;

public class DiscovererRepository implements Repository<Discoverer>{

    private Map<String,Discoverer> discoverers;

    public DiscovererRepository() {
        this.discoverers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(discoverers.values());
    }

    @Override
    public void add(Discoverer discoverer) {
        discoverers.putIfAbsent(discoverer.getName(),discoverer);
    }

    @Override
    public boolean remove(Discoverer discoverer) {
        if (discoverers.containsKey(discoverer.getName())){
            discoverers.remove(discoverer.getName());
            return true;
        }
        return false;
    }

    @Override
    public Discoverer byName(String name) {
        if (discoverers.containsKey(name)){
            return discoverers.get(name);
        }
        return null;
    }
}
