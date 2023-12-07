package P15Exams.P01Exam.robotService;

import P15Exams.P01Exam.robotService.core.Engine;
import P15Exams.P01Exam.robotService.core.EngineImpl;

public class Main {
    public static void main(String[] args) {

        Engine engine = new EngineImpl();
        engine.run();
    }
}
