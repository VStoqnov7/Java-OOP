package P15Exams.P17Exam.onlineShop.io;


import P15Exams.P17Exam.onlineShop.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
