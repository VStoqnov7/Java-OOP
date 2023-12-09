package P15Exams.P06Exam.goldDigger.models.operation;

import P15Exams.P06Exam.goldDigger.models.discoverer.Discoverer;
import P15Exams.P06Exam.goldDigger.models.spot.Spot;

import java.util.Collection;

public interface Operation {
    void startOperation(Spot spot, Collection<Discoverer> discoverers);

}
