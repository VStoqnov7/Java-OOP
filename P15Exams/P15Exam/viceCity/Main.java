package P15Exams.P15Exam.viceCity;

import P15Exams.P15Exam.viceCity.core.ControllerImpl;
import P15Exams.P15Exam.viceCity.core.EngineImpl;
import P15Exams.P15Exam.viceCity.core.interfaces.Controller;
import P15Exams.P15Exam.viceCity.core.interfaces.Engine;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl(); // TODO
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
