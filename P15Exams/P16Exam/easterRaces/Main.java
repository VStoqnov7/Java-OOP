package P15Exams.P16Exam.easterRaces;

import P15Exams.P16Exam.easterRaces.core.ControllerImpl;
import P15Exams.P16Exam.easterRaces.core.EngineImpl;
import P15Exams.P16Exam.easterRaces.core.interfaces.Controller;
import P15Exams.P16Exam.easterRaces.entities.cars.Car;
import P15Exams.P16Exam.easterRaces.entities.drivers.Driver;
import P15Exams.P16Exam.easterRaces.entities.racers.Race;
import P15Exams.P16Exam.easterRaces.io.ConsoleReader;
import P15Exams.P16Exam.easterRaces.io.ConsoleWriter;
import P15Exams.P16Exam.easterRaces.repositories.CarRepository;
import P15Exams.P16Exam.easterRaces.repositories.DriverRepository;
import P15Exams.P16Exam.easterRaces.repositories.RaceRepository;
import P15Exams.P16Exam.easterRaces.repositories.interfaces.Repository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
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
