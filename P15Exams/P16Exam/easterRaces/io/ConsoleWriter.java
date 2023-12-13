package P15Exams.P16Exam.easterRaces.io;

import P15Exams.P16Exam.easterRaces.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
