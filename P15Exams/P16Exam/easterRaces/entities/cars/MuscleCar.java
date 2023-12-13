package P15Exams.P16Exam.easterRaces.entities.cars;

import P15Exams.P16Exam.easterRaces.common.ExceptionMessages;

public class MuscleCar extends BaseCar{

    private static final double CUBIC_CENTIMETERS = 5000;
    private static final int MIN_HORSE_POWER = 400;
    private static final int MAX_HORSE_POWER = 600;

    public MuscleCar(String name, int horsePower) {
        super(name, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    public void checkHorsepower(int horsePower) {
        if (horsePower < MIN_HORSE_POWER || horsePower > MAX_HORSE_POWER){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,horsePower));
        }
    }
}
