package P15Exams.P07Exam.football.repositories;

import P15Exams.P07Exam.football.entities.supplement.Supplement;

public interface SupplementRepository {
    void add(Supplement supplement);

    boolean remove(Supplement supplement);

    Supplement findByType(String type);
}
