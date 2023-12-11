package P15Exams.P12Exam.glacialExpedition.models.explorers;

import P15Exams.P12Exam.glacialExpedition.models.suitcases.Suitcase;

public interface Explorer {
    String getName();

    double getEnergy();

    boolean canSearch();

    Suitcase getSuitcase();

    void search();
}
