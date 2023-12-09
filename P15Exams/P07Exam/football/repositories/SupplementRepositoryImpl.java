package P15Exams.P07Exam.football.repositories;

import P15Exams.P07Exam.football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public class SupplementRepositoryImpl implements SupplementRepository{


    private Collection<Supplement> supplements;


    public SupplementRepositoryImpl() {
        this.supplements = new ArrayList<>();
    }

    @Override
    public void add(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public boolean remove(Supplement supplement) {
        if (supplements.contains(supplement)){
            supplements.remove(supplement);
            return true;
        }
        return false;
    }

    @Override
    public Supplement findByType(String type) {
        return supplements.stream().filter(supplement -> supplement.getClass().getSimpleName().equals(type)).findFirst().orElse(null);
    }
}
