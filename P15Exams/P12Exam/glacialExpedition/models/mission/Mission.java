package P15Exams.P12Exam.glacialExpedition.models.mission;

import P15Exams.P12Exam.glacialExpedition.models.explorers.Explorer;
import P15Exams.P12Exam.glacialExpedition.models.states.State;


import java.util.Collection;

public interface Mission {
    void explore(State state, Collection<Explorer> explorers);
}
