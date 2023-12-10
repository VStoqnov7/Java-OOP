package P15Exams.P10Exam.christmasRaces.entities.drivers;


import P15Exams.P10Exam.christmasRaces.common.ExceptionMessages;
import P15Exams.P10Exam.christmasRaces.entities.cars.Car;

public class DriverImpl implements Driver{

    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate;

    public DriverImpl(String name) {
        this.setName(name);
        this.numberOfWins = 0;
        this.canParticipate = false;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME,name,5));
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addCar(Car car) {
        if (car == null){
            throw new IllegalArgumentException(ExceptionMessages.CAR_INVALID);
        }
        this.car = car;
    }

    @Override
    public void winRace() {
        numberOfWins++;
    }

    @Override
    public boolean getCanParticipate() {
        return car != null;
    }
}
