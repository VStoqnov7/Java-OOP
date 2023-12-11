package P15Exams.P12Exam.glacialExpedition.models.mission;

import P15Exams.P12Exam.glacialExpedition.models.explorers.Explorer;
import P15Exams.P12Exam.glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission{

    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        List<Explorer> explorerList = explorers.stream().filter(Explorer::canSearch).collect(Collectors.toList());

        List<String> states = new ArrayList<>(state.getExhibits());

        while (explorerList.iterator().hasNext() && states.iterator().hasNext()){

            Explorer explorer = explorerList.get(0);
            if (explorer.canSearch()){
                explorer.search();
                explorer.getSuitcase().getExhibits().add(states.get(0));
                states.remove(0);
            }else {
                explorerList.remove(0);
            }
        }
    }
}
