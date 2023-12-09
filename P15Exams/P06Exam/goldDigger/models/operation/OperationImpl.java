package P15Exams.P06Exam.goldDigger.models.operation;



import P15Exams.P06Exam.goldDigger.models.discoverer.Discoverer;
import P15Exams.P06Exam.goldDigger.models.spot.Spot;

import java.util.Collection;


public class OperationImpl implements Operation {


    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        Collection<String> spotExhibits = spot.getExhibits();

        for (Discoverer discoverer : discoverers) {
            while (discoverer.canDig() && spotExhibits.iterator().hasNext()) {
                discoverer.dig();

                String currentExhibit = spotExhibits.iterator().next();
                discoverer.getMuseum().getExhibits().add(currentExhibit);
                spotExhibits.remove(currentExhibit);
            }
        }
    }
}
