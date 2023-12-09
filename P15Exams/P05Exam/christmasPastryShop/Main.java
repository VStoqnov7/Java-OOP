package P15Exams.P05Exam.christmasPastryShop;


import P15Exams.P05Exam.christmasPastryShop.core.ControllerImpl;
import P15Exams.P05Exam.christmasPastryShop.core.EngineImpl;
import P15Exams.P05Exam.christmasPastryShop.core.interfaces.Controller;
import P15Exams.P05Exam.christmasPastryShop.entities.booths.interfaces.Booth;
import P15Exams.P05Exam.christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import P15Exams.P05Exam.christmasPastryShop.entities.drinks.interfaces.Cocktail;
import P15Exams.P05Exam.christmasPastryShop.io.ConsoleReader;
import P15Exams.P05Exam.christmasPastryShop.io.ConsoleWriter;
import P15Exams.P05Exam.christmasPastryShop.repositories.BoothRepositoryImpl;
import P15Exams.P05Exam.christmasPastryShop.repositories.CocktailRepositoryImpl;
import P15Exams.P05Exam.christmasPastryShop.repositories.DelicacyRepositoryImpl;
import P15Exams.P05Exam.christmasPastryShop.repositories.interfaces.BoothRepository;
import P15Exams.P05Exam.christmasPastryShop.repositories.interfaces.CocktailRepository;
import P15Exams.P05Exam.christmasPastryShop.repositories.interfaces.DelicacyRepository;

public class Main {
    public static void main(String[] args) {

        String a = " ";
        int a1 = a.length();
        DelicacyRepository<Delicacy> delicacyRepository = new DelicacyRepositoryImpl(); // TODO: new DelicacyRepositoryImpl<>();
        CocktailRepository<Cocktail> cocktailRepository = new CocktailRepositoryImpl(); // TODO: new CocktailRepositoryImpl<>();
        BoothRepository<Booth> boothRepository = new BoothRepositoryImpl(); // TODO: new BoothRepositoryImpl<>();

        Controller controller = new ControllerImpl(delicacyRepository, cocktailRepository, boothRepository); // TODO: new ControllerImpl(delicacyRepository, cocktailRepository, boothRepository);

        // TODO:OPTIONAL

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();

    }
}
