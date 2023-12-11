package P15Exams.P13Exam.restaurant.io;

import P15Exams.P13Exam.restaurant.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    public void writeLine(String text) {
        System.out.println(text);
    }
}
