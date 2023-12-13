package P15Exams.P16Exam.easterRaces.entities.drivers;

import P15Exams.P16Exam.easterRaces.common.ExceptionMessages;
import P15Exams.P16Exam.easterRaces.entities.cars.Car;

public class DriverImpl implements Driver{

    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate;

    public DriverImpl(String name) {
        this.setName(name);
        this.numberOfWins = 0;
        this.car = null;
        this.canParticipate = false;
    }

    protected void setName(String name) {
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
        this.numberOfWins++;
    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }
}
