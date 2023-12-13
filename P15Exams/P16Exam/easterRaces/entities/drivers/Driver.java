package P15Exams.P16Exam.easterRaces.entities.drivers;

import P15Exams.P16Exam.easterRaces.entities.cars.Car;

public interface Driver {
    String getName();

    Car getCar();

    int getNumberOfWins();

    void addCar(Car car);

    void winRace();

    boolean getCanParticipate();
}
