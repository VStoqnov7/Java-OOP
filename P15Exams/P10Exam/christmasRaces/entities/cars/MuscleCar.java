package P15Exams.P10Exam.christmasRaces.entities.cars;


import P15Exams.P10Exam.christmasRaces.common.ExceptionMessages;

public class MuscleCar extends BaseCar{

    private static final double CUBIC_CENTIMETERS = 5000;
    private static final int MIN_HORSEPOWER = 400;
    private static final int MAX_HORSEPOWER = 600;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    protected void checkHorsePower(int horsePower) {
        if (horsePower < MIN_HORSEPOWER || horsePower > MAX_HORSEPOWER){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,horsePower));
        }
    }
}
