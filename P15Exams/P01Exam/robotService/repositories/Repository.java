package P15Exams.P01Exam.robotService.repositories;

import P15Exams.P01Exam.robotService.entities.supplements.Supplement;

public interface Repository {

    void addSupplement(Supplement supplement);

    boolean removeSupplement(Supplement supplement);

    Supplement findFirst(String type);
}
