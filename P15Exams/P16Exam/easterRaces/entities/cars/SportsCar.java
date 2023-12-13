package P15Exams.P16Exam.easterRaces.entities.cars;

import P15Exams.P16Exam.easterRaces.common.ExceptionMessages;

public class SportsCar extends BaseCar{

    private static final double CUBIC_CENTIMETERS = 3000;
    private static final int MIN_HORSE_POWER = 250;
    private static final int MAX_HORSE_POWER = 450;

    public SportsCar(String name, int horsePower) {
        super(name, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    public void checkHorsepower(int horsePower) {
        if (horsePower < MIN_HORSE_POWER || horsePower > MAX_HORSE_POWER){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,horsePower));
        }
    }
}
