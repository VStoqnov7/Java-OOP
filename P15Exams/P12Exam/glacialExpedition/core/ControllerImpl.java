package P15Exams.P12Exam.glacialExpedition.core;

import P15Exams.P12Exam.glacialExpedition.common.ConstantMessages;
import P15Exams.P12Exam.glacialExpedition.common.ExceptionMessages;
import P15Exams.P12Exam.glacialExpedition.models.explorers.AnimalExplorer;
import P15Exams.P12Exam.glacialExpedition.models.explorers.Explorer;
import P15Exams.P12Exam.glacialExpedition.models.explorers.GlacierExplorer;
import P15Exams.P12Exam.glacialExpedition.models.explorers.NaturalExplorer;
import P15Exams.P12Exam.glacialExpedition.models.mission.Mission;
import P15Exams.P12Exam.glacialExpedition.models.mission.MissionImpl;
import P15Exams.P12Exam.glacialExpedition.models.states.State;
import P15Exams.P12Exam.glacialExpedition.models.states.StateImpl;
import P15Exams.P12Exam.glacialExpedition.repositories.ExplorerRepository;
import P15Exams.P12Exam.glacialExpedition.repositories.StateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploredStatesCount;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
        this.exploredStatesCount = 0;
    }


    @Override
    public String addExplorer(String type, String explorerName) {

        Explorer explorer = null;
        if (type.equals("AnimalExplorer")) {
            explorer = new AnimalExplorer(explorerName);
        } else if (type.equals("GlacierExplorer")) {
            explorer = new GlacierExplorer(explorerName);
        } else if (type.equals("NaturalExplorer")) {
            explorer = new NaturalExplorer(explorerName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }

        explorerRepository.add(explorer);

        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {

        State state = new StateImpl(stateName);
        state.getExhibits().addAll(List.of(exhibits));
        stateRepository.add(state);

        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        explorerRepository.remove(explorer);
        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {

        List<Explorer> explorers = explorerRepository.getCollection().stream().filter(explorer -> explorer.getEnergy() > 50).collect(Collectors.toList());

        if (explorers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State state = stateRepository.byName(stateName);
        Mission mission = new MissionImpl();
        mission.explore(state, explorers);
        long count = explorerRepository.getCollection().stream()
                .filter(explorer -> !explorer.canSearch())
                .count();

        exploredStatesCount++;

        return String.format(ConstantMessages.STATE_EXPLORER, stateName, count);
    }

    @Override
    public String finalResult() {

        List<Explorer> explorers = new ArrayList<>(explorerRepository.getCollection());

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.FINAL_STATE_EXPLORED, this.exploredStatesCount));
        sb.append(System.lineSeparator());
        sb.append(ConstantMessages.FINAL_EXPLORER_INFO);
        sb.append(System.lineSeparator());

        for (Explorer explorer : explorers) {
            sb.append(String.format(ConstantMessages.FINAL_EXPLORER_NAME, explorer.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_EXPLORER_ENERGY,explorer.getEnergy()));
            sb.append(System.lineSeparator());

            StringBuilder sbNoneOrAll = new StringBuilder();
            if (explorer.getSuitcase().getExhibits().isEmpty()){
                sbNoneOrAll.append("None");
            }else {
                sbNoneOrAll.append(explorer.getSuitcase().getExhibits().stream().collect(Collectors.joining(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER)));
            }

            sb.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS,sbNoneOrAll.toString().trim()));

            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
