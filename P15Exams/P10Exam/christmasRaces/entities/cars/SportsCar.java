package P15Exams.P10Exam.christmasRaces.entities.cars;


import P15Exams.P10Exam.christmasRaces.common.ExceptionMessages;

public class SportsCar extends BaseCar{


    private static final double CUBIC_CENTIMETERS = 3000;
    private static final int MIN_HORSEPOWER = 250;
    private static final int MAX_HORSEPOWER = 450;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    protected void checkHorsePower(int horsePower) {
        if (horsePower < MIN_HORSEPOWER || horsePower > MAX_HORSEPOWER){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,horsePower));
        }
    }
}
