package P15Exams.P10Exam.christmasRaces.io;

import P15Exams.P10Exam.christmasRaces.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
