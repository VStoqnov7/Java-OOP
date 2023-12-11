package P15Exams.P12Exam.glacialExpedition;

import P15Exams.P12Exam.glacialExpedition.core.Controller;
import P15Exams.P12Exam.glacialExpedition.core.ControllerImpl;
import P15Exams.P12Exam.glacialExpedition.core.Engine;
import P15Exams.P12Exam.glacialExpedition.core.EngineImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
