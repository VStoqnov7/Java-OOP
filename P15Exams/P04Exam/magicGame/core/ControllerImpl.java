package P15Exams.P04Exam.magicGame.core;

import P15Exams.P04Exam.magicGame.common.ExceptionMessages;
import P15Exams.P04Exam.magicGame.common.OutputMessages;
import P15Exams.P04Exam.magicGame.models.magicians.BlackWidow;
import P15Exams.P04Exam.magicGame.models.magicians.Magician;
import P15Exams.P04Exam.magicGame.models.magicians.Wizard;
import P15Exams.P04Exam.magicGame.models.magics.BlackMagic;
import P15Exams.P04Exam.magicGame.models.magics.Magic;
import P15Exams.P04Exam.magicGame.models.magics.RedMagic;
import P15Exams.P04Exam.magicGame.models.region.Region;
import P15Exams.P04Exam.magicGame.models.region.RegionImpl;
import P15Exams.P04Exam.magicGame.repositories.interfaces.MagicRepositoryImpl;
import P15Exams.P04Exam.magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {


    private MagicRepositoryImpl magics;
    private MagicianRepositoryImpl magicians;
    private Region region;

    public ControllerImpl() {
        this.magics = new MagicRepositoryImpl();
        this.magicians = new MagicianRepositoryImpl();
        this.region = new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {

        Magic magic = null;
        if (type.equals("RedMagic")){
            magic = new RedMagic(name,bulletsCount);
        } else if (type.equals("BlackMagic")) {
            magic = new BlackMagic(name,bulletsCount);
        }

        if (magic == null){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGIC_TYPE);
        }
        magics.addMagic(magic);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_MAGIC,name);
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        Magic magic = magics.findByName(magicName);
        if (magic == null){
            throw new NullPointerException(ExceptionMessages.MAGIC_CANNOT_BE_FOUND);
        }

        Magician magician = null;
        if (type.equals("Wizard")){
            magician = new Wizard(username,health,protection,magic);
        } else if (type.equals("BlackWidow")) {
            magician = new BlackWidow(username,health,protection,magic);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_TYPE);
        }
        magicians.addMagician(magician);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN,username);
    }

    @Override
    public String startGame() {
        return region.start(magicians.getData());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        List<Magician> sorted = magicians.getData()
                .stream()
                .sorted(Comparator.comparing(Magician::getHealth)
                        .thenComparing(Magician::getUsername)
                        .thenComparing((a1,a2) -> a1.getClass().getSimpleName().compareTo(a2.getClass().getSimpleName()))).collect(Collectors.toList());
        for (Magician datum : sorted) {
            sb.append(datum.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
