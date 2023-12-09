package P15Exams.P07Exam.football.core;


import P15Exams.P07Exam.football.common.ConstantMessages;
import P15Exams.P07Exam.football.common.ExceptionMessages;
import P15Exams.P07Exam.football.entities.field.ArtificialTurf;
import P15Exams.P07Exam.football.entities.field.Field;
import P15Exams.P07Exam.football.entities.field.NaturalGrass;
import P15Exams.P07Exam.football.entities.player.Men;
import P15Exams.P07Exam.football.entities.player.Player;
import P15Exams.P07Exam.football.entities.player.Women;
import P15Exams.P07Exam.football.entities.supplement.Liquid;
import P15Exams.P07Exam.football.entities.supplement.Powdered;
import P15Exams.P07Exam.football.entities.supplement.Supplement;
import P15Exams.P07Exam.football.repositories.SupplementRepository;
import P15Exams.P07Exam.football.repositories.SupplementRepositoryImpl;


import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private SupplementRepository supplements;
    private Collection<Field> fields;

    public ControllerImpl() {
        this.supplements = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field = null;
        if (fieldType.equals("ArtificialTurf")) {
            field = new ArtificialTurf(fieldName);
        } else if (fieldType.equals("NaturalGrass")) {
            field = new NaturalGrass(fieldName);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }
        fields.add(field);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplement1 = null;
        if (type.equals("Powdered")) {
            supplement1 = new Powdered();
        } else if (type.equals("Liquid")) {
            supplement1 = new Liquid();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }
        supplements.add(supplement1);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);

    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Field field = null;
        Supplement supplement1 = supplements.findByType(supplementType);
        for (Field field1 : fields) {
            if (field1.getName().equals(fieldName)) {
                field = field1;
                break;
            }
        }

        if (supplement1 == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }


        if (field != null) {
            field.addSupplement(supplement1);
            supplements.remove(supplement1);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Field field = null;
        for (Field field1 : fields) {
            if (field1.getName().equals(fieldName)) {
                field = field1;
                break;
            }
        }

        Player player = null;
        if (playerType.equals("Men")) {
            player = new Men(playerName, nationality, strength);
        } else if (playerType.equals("Women")) {
            player = new Women(playerName, nationality, strength);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        if (player.getClass().getSimpleName().equals("Men") && field.getClass().getSimpleName().equals("NaturalGrass")) {
            field.addPlayer(player);
        } else if (player.getClass().getSimpleName().equals("Women") && field.getClass().getSimpleName().equals("ArtificialTurf")) {
            field.addPlayer(player);
        } else {
            return ConstantMessages.FIELD_NOT_SUITABLE;
        }


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);

    }

    @Override
    public String dragPlayer(String fieldName) {

        int count = 0;
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                field.drag();
                count = field.getPlayers().size();
            }
        }

        return String.format(ConstantMessages.PLAYER_DRAG, count);
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = null;
        for (Field field1 : fields) {
            if (field1.getName().equals(fieldName)) {
                field = field1;
                break;
            }
        }

        int totalSum = 0;

        if (field != null) {
            totalSum = field.getPlayers().stream().mapToInt(Player::getStrength).sum();

        }


        return String.format(ConstantMessages.STRENGTH_FIELD, fieldName, totalSum);
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();

        for (Field field : fields) {
            sb.append(field.getInfo());
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
