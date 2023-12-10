package P15Exams.P10Exam.christmasRaces.entities.races;

import P15Exams.P10Exam.christmasRaces.entities.drivers.Driver;

import java.util.Collection;

public interface Race {
    String getName();

    int getLaps();

    Collection<Driver> getDrivers();

    void addDriver(Driver driver);
}
