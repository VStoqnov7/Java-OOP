package P15Exams.P01Exam.robotService.repositories;

import P15Exams.P01Exam.robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public class SupplementRepository implements Repository {

    private Collection<Supplement> supplements;

    public SupplementRepository() {
        this.supplements = new ArrayList<>();
    }

    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public boolean removeSupplement(Supplement supplement) {
        if (supplements.contains(supplement)){
            supplements.remove(supplement);
            return true;
        }
        return false;
    }

    @Override
    public Supplement findFirst(String type) {

        for (Supplement supplement : supplements) {
            if (supplement.getClass().getSimpleName().equals(type)){
                return supplement;
            }
        }
        return null;
    }
}
