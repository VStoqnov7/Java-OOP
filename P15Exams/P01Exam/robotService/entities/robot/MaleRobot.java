package P15Exams.P01Exam.robotService.entities.robot;

public class MaleRobot extends BaseRobot{

    private static final int KILOGRAMS = 9;


    public MaleRobot(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eating() {
        this.setKilograms(this.getKilograms() + 3);
    }
}
