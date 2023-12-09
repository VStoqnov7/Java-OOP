package P15Exams.P05Exam.christmasPastryShop.io;

import P15Exams.P05Exam.christmasPastryShop.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    public void writeLine(String text) {
        System.out.println(text);
    }
}
