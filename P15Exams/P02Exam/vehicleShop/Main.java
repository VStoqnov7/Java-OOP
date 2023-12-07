package P15Exams.P02Exam.vehicleShop;

import P15Exams.P02Exam.vehicleShop.core.Engine;
import P15Exams.P02Exam.vehicleShop.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
