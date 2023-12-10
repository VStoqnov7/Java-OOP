package P15Exams.P10Exam.christmasRaces.entities.drivers;

import P15Exams.P10Exam.christmasRaces.entities.cars.Car;

public interface Driver {
    String getName();

    Car getCar();

    int getNumberOfWins();

    void addCar(Car car);

    void winRace();

    boolean getCanParticipate();
}
