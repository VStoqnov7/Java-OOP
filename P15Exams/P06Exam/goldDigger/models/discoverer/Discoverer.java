package P15Exams.P06Exam.goldDigger.models.discoverer;

import P15Exams.P06Exam.goldDigger.models.museum.Museum;

public interface Discoverer {
    String getName();

    double getEnergy();

    boolean canDig();

    Museum getMuseum();

    void dig();
}
