package P15Exams.P16Exam.easterRaces.core;

import P15Exams.P16Exam.easterRaces.common.ExceptionMessages;
import P15Exams.P16Exam.easterRaces.common.OutputMessages;
import P15Exams.P16Exam.easterRaces.core.interfaces.Controller;
import P15Exams.P16Exam.easterRaces.entities.cars.Car;
import P15Exams.P16Exam.easterRaces.entities.cars.MuscleCar;
import P15Exams.P16Exam.easterRaces.entities.cars.SportsCar;
import P15Exams.P16Exam.easterRaces.entities.drivers.Driver;
import P15Exams.P16Exam.easterRaces.entities.drivers.DriverImpl;
import P15Exams.P16Exam.easterRaces.entities.racers.Race;
import P15Exams.P16Exam.easterRaces.entities.racers.RaceImpl;
import P15Exams.P16Exam.easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        if (driverRepository.getByName(driver) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driver));
        }
        Driver driver1 = new DriverImpl(driver);
        driverRepository.add(driver1);
        return String.format(OutputMessages.DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
        }

        Car car = null;
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
        }

        carRepository.add(car);
        return String.format(OutputMessages.CAR_CREATED, car.getClass().getSimpleName(), model);

    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }
        if (carRepository.getByName(carModel) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, carModel));
        }

        Driver driver = driverRepository.getByName(driverName);
        Car car = carRepository.getByName(carModel);
        driver.addCar(car);


        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }

        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }

        Race race = raceRepository.getByName(raceName);
        Driver driver = driverRepository.getByName(driverName);
        race.addDriver(driver);

        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }

        Race race = raceRepository.getByName(raceName);

        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, 3));
        }

        List<Driver> drivers = new ArrayList<>(race.getDrivers());
        int numberOfLAps = race.getLaps();

        List<Driver> winners = drivers.stream()
                .sorted((firstDriver, secondDriver) ->
                        (int) (secondDriver.getCar().calculateRacePoints(numberOfLAps) -
                                firstDriver.getCar().calculateRacePoints(numberOfLAps)))
                .limit(3).collect(Collectors.toList());


        raceRepository.remove(race);

        Driver firstDriver = winners.get(0);
        firstDriver.winRace();
        Driver secondDriver = winners.get(1);
        Driver thirdDriver = winners.get(2);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(OutputMessages.DRIVER_FIRST_POSITION, firstDriver.getName(), race.getName()))
                .append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_SECOND_POSITION, secondDriver.getName(), race.getName()))
                .append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_THIRD_POSITION, thirdDriver.getName(), race.getName()));

        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        if (raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }

        Race race = new RaceImpl(name, laps);

        raceRepository.add(race);

        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
