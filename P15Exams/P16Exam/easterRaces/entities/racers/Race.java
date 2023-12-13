package P15Exams.P16Exam.easterRaces.entities.racers;

import P15Exams.P16Exam.easterRaces.entities.drivers.Driver;

import java.util.Collection;

public interface Race {
    String getName();

    int getLaps();

    Collection<Driver> getDrivers();

    void addDriver(Driver driver);
}
