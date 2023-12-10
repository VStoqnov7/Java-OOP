package P15Exams.P10Exam.christmasRaces;

import P15Exams.P10Exam.christmasRaces.core.ControllerImpl;
import P15Exams.P10Exam.christmasRaces.core.EngineImpl;
import P15Exams.P10Exam.christmasRaces.core.interfaces.Controller;
import P15Exams.P10Exam.christmasRaces.entities.cars.Car;
import P15Exams.P10Exam.christmasRaces.entities.drivers.Driver;
import P15Exams.P10Exam.christmasRaces.entities.races.Race;
import P15Exams.P10Exam.christmasRaces.io.ConsoleReader;
import P15Exams.P10Exam.christmasRaces.io.ConsoleWriter;
import P15Exams.P10Exam.christmasRaces.repositories.CarRepository;
import P15Exams.P10Exam.christmasRaces.repositories.DriverRepository;
import P15Exams.P10Exam.christmasRaces.repositories.RaceRepository;
import P15Exams.P10Exam.christmasRaces.repositories.interfaces.Repository;


public class Main {
    public static void main(String[] args) {
        Repository<Car> carRepository = new CarRepository();
        Repository<Race> raceRepository = new RaceRepository();
        Repository<Driver> driverRepository =  new DriverRepository();

        Controller controller = new ControllerImpl(driverRepository, carRepository, raceRepository);

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();
    }
}
