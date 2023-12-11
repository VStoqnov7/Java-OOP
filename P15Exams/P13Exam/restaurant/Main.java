package P15Exams.P13Exam.restaurant;

import P15Exams.P13Exam.restaurant.core.ControllerImpl;
import P15Exams.P13Exam.restaurant.core.EngineImpl;
import P15Exams.P13Exam.restaurant.core.interfaces.Controller;
import P15Exams.P13Exam.restaurant.entities.drinks.interfaces.Beverages;
import P15Exams.P13Exam.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import P15Exams.P13Exam.restaurant.entities.tables.interfaces.Table;

import P15Exams.P13Exam.restaurant.io.ConsoleReader;
import P15Exams.P13Exam.restaurant.io.ConsoleWriter;
import P15Exams.P13Exam.restaurant.repositories.BeverageRepositoryImpl;
import P15Exams.P13Exam.restaurant.repositories.HealthFoodRepositoryImpl;
import P15Exams.P13Exam.restaurant.repositories.TableRepositoryImpl;
import P15Exams.P13Exam.restaurant.repositories.interfaces.*;

public class Main {
    public static void main(String[] args) {
        // TODO: Optional - Create new instances for all repositories to test your code locally.

        HealthFoodRepository<HealthyFood> healthFoodRepository = new HealthFoodRepositoryImpl();
        BeverageRepository<Beverages> beverageRepository = new BeverageRepositoryImpl();
        TableRepository<Table> tableRepository = new TableRepositoryImpl();

        Controller controller = new ControllerImpl(healthFoodRepository, beverageRepository, tableRepository);

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();

    }
}
