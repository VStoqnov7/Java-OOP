package P15Exams.P09Exam.fairyShop.core;

import P15Exams.P09Exam.fairyShop.common.ConstantMessages;
import P15Exams.P09Exam.fairyShop.common.ExceptionMessages;
import P15Exams.P09Exam.fairyShop.models.*;
import P15Exams.P09Exam.fairyShop.repositories.HelperRepository;
import P15Exams.P09Exam.fairyShop.repositories.PresentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private HelperRepository helperRepository;
    private PresentRepository presentRepository;

    private int presentDone;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.presentDone = 0;
    }

    @Override
    public String addHelper(String type, String helperName) {
        if (!(type.equals("Happy") || type.equals("Sleepy"))){
            throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }
        Helper helper = null;
        switch (type){
            case "Happy":
                helper = new Happy(helperName);
            break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
        }

        helperRepository.add(helper);

        return String.format(ConstantMessages.ADDED_HELPER,type,helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {

        Helper helper = helperRepository.findByName(helperName);
        if (helper == null){
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }

        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER,power,helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {

        Present present = new PresentImpl(presentName,energyRequired);
        presentRepository.add(present);


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT,presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        Present present = presentRepository.findByName(presentName);
        List<Helper> helpers = helperRepository.getModels().stream().filter(helper -> helper.getEnergy() > 50).collect(Collectors.toList());
        if (helpers.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }

        Shop shop = new ShopImpl();

        int brokenInstruments = 0;

        for (Helper helper : helpers) {
            shop.craft(present, helper);
            brokenInstruments += helper.getInstruments().stream().filter(Instrument::isBroken).count();
            if (present.isDone()){
                break;
            }

        }

        StringBuilder sb = new StringBuilder();
        if (present.isDone()){
            sb.append(String.format(ConstantMessages.PRESENT_DONE, presentName, "done"));
            this.presentDone++;

        }else {
            sb.append(String.format(ConstantMessages.PRESENT_DONE, presentName, "not done"));
        }
        sb.append(String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS,brokenInstruments));


        return sb.toString().trim();
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d presents are done!",this.presentDone));
        sb.append(System.lineSeparator());
        sb.append("Helpers info:");
        sb.append(System.lineSeparator());
        for (Helper helper : helperRepository.getModels()) {
            sb.append(String.format("Name: %s",helper.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format("Energy: %d",helper.getEnergy()));
            sb.append(System.lineSeparator());
            sb.append(String.format("Instruments: %d not broken left",helper.getInstruments().stream().filter(instrument -> !instrument.isBroken()).count()));
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
