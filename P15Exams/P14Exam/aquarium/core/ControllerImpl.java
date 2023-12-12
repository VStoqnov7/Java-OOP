package P15Exams.P14Exam.aquarium.core;

import P15Exams.P14Exam.aquarium.common.ConstantMessages;
import P15Exams.P14Exam.aquarium.common.ExceptionMessages;
import P15Exams.P14Exam.aquarium.entities.aquariums.Aquarium;
import P15Exams.P14Exam.aquarium.entities.aquariums.FreshwaterAquarium;
import P15Exams.P14Exam.aquarium.entities.aquariums.SaltwaterAquarium;
import P15Exams.P14Exam.aquarium.entities.decorations.Decoration;
import P15Exams.P14Exam.aquarium.entities.decorations.Ornament;
import P15Exams.P14Exam.aquarium.entities.decorations.Plant;
import P15Exams.P14Exam.aquarium.entities.fish.Fish;
import P15Exams.P14Exam.aquarium.entities.fish.FreshwaterFish;
import P15Exams.P14Exam.aquarium.entities.fish.SaltwaterFish;
import P15Exams.P14Exam.aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private DecorationRepository decorations;
    private Collection<Aquarium> aquariums;


    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        if (!(aquariumType.equals("FreshwaterAquarium") || aquariumType.equals("SaltwaterAquarium"))) {
            throw new NullPointerException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }

        Aquarium aquarium = null;
        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
        }
        aquariums.add(aquarium);


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        if (!(type.equals("Ornament") || type.equals("Plant"))) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
        }

        Decoration decoration = null;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
        }
        decorations.add(decoration);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = decorations.findByType(decorationType);
        if (decoration == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_DECORATION_FOUND, decorationType));
        }

        Aquarium aquarium = aquariums.stream().filter(aquarium1 -> aquarium1.getName().equals(aquariumName)).findFirst().orElse(null);
        aquarium.addDecoration(decoration);
        decorations.remove(decoration);


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {

        Fish fish = null;
        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }

        Aquarium aquarium = aquariums.stream().filter(aquarium1 -> aquarium1.getName().equals(aquariumName)).findFirst().orElse(null);

        if (aquarium.getClass().getSimpleName().equals("FreshwaterAquarium") && fishType.equals("FreshwaterFish")) {
            aquarium.addFish(fish);
        } else if (aquarium.getClass().getSimpleName().equals("SaltwaterAquarium") && fishType.equals("SaltwaterFish")) {
            aquarium.addFish(fish);
        } else {
            return ConstantMessages.WATER_NOT_SUITABLE;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = aquariums.stream().filter(aquarium1 -> aquarium1.getName().equals(aquariumName)).findFirst().orElse(null);
        aquarium.feed();
        int fishFeedCount = aquarium.getFish().size();


        return String.format(ConstantMessages.FISH_FED, fishFeedCount);
    }

    @Override
    public String calculateValue(String aquariumName) {

        Aquarium aquarium = aquariums.stream().filter(aquarium1 -> aquarium1.getName().equals(aquariumName)).findFirst().orElse(null);

        double aquariumFishSum = aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
        double aquariumDecorationSum = aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
        double totalSum = aquariumFishSum + aquariumDecorationSum;

        return String.format(ConstantMessages.VALUE_AQUARIUM,aquariumName,totalSum);
    }

    @Override
    public String report() {

        StringBuilder sb = new StringBuilder();

        for (Aquarium aquarium : aquariums) {
            sb.append(aquarium.getInfo());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
