package P15Exams.P06Exam.goldDigger.core;

import P15Exams.P06Exam.goldDigger.common.ConstantMessages;
import P15Exams.P06Exam.goldDigger.common.ExceptionMessages;
import P15Exams.P06Exam.goldDigger.models.discoverer.Anthropologist;
import P15Exams.P06Exam.goldDigger.models.discoverer.Archaeologist;
import P15Exams.P06Exam.goldDigger.models.discoverer.Discoverer;
import P15Exams.P06Exam.goldDigger.models.discoverer.Geologist;
import P15Exams.P06Exam.goldDigger.models.operation.Operation;
import P15Exams.P06Exam.goldDigger.models.operation.OperationImpl;
import P15Exams.P06Exam.goldDigger.models.spot.Spot;
import P15Exams.P06Exam.goldDigger.models.spot.SpotImpl;
import P15Exams.P06Exam.goldDigger.repositories.DiscovererRepository;
import P15Exams.P06Exam.goldDigger.repositories.SpotRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private DiscovererRepository discovererRepository;
    private SpotRepository spotRepository;
    private int spotCount;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
        this.spotCount = 0;
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer = null;
        if (kind.equals("Anthropologist")) {
            discoverer = new Anthropologist(discovererName);
        } else if (kind.equals("Archaeologist")) {
            discoverer = new Archaeologist(discovererName);
        } else if (kind.equals("Geologist")) {
            discoverer = new Geologist(discovererName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_INVALID_KIND);
        }
        discovererRepository.add(discoverer);

        return String.format(ConstantMessages.DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);


        spot.getExhibits().addAll(List.of(exhibits));

        spotRepository.add(spot);
        return String.format(ConstantMessages.SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.byName(discovererName);
        if (discoverer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DISCOVERER_DOES_NOT_EXIST, discovererName));
        }
        discovererRepository.remove(discoverer);

        return String.format(ConstantMessages.DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> discoverers = discovererRepository.getCollection().stream().filter(discoverer -> discoverer.getEnergy() > 45).collect(Collectors.toList());

        if (discoverers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        Spot spot = spotRepository.byName(spotName);
        Operation operation = new OperationImpl();
        operation.startOperation(spot, discoverers);

        long excluded = discoverers.stream().filter(d -> d.getEnergy() == 0).count();
        this.spotCount++;
        return String.format(ConstantMessages.INSPECT_SPOT, spotName, excluded);
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.FINAL_SPOT_INSPECT, this.spotCount));
        sb.append(System.lineSeparator());
        sb.append(ConstantMessages.FINAL_DISCOVERER_INFO);

        Collection<Discoverer> discoverers = discovererRepository.getCollection();
        for(Discoverer discoverer : discoverers){
            sb.append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_DISCOVERER_NAME, discoverer.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_DISCOVERER_ENERGY, discoverer.getEnergy()));
            sb.append(System.lineSeparator());
            if(discoverer.getMuseum().getExhibits().isEmpty()){
                sb.append(String.format(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS, "None"));

            }else{
                sb.append(String.format(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS,
                        String.join(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER, discoverer.getMuseum().getExhibits())));
            }
        }
        return sb.toString();
    }
}
