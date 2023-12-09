package P15Exams.P06Exam.goldDigger;

import P15Exams.P06Exam.goldDigger.core.Controller;
import P15Exams.P06Exam.goldDigger.core.ControllerImpl;
import P15Exams.P06Exam.goldDigger.core.Engine;
import P15Exams.P06Exam.goldDigger.core.EngineImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
