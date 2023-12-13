package P15Exams.P17Exam.onlineShop;

import P15Exams.P17Exam.onlineShop.core.EngineImpl;
import P15Exams.P17Exam.onlineShop.core.interfaces.Engine;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
