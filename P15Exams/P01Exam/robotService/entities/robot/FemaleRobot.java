package P15Exams.P01Exam.robotService.entities.robot;

public class FemaleRobot extends BaseRobot{

    private static final int KILOGRAMS = 7;


    public FemaleRobot(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eating() {
        this.setKilograms(this.getKilograms() + 1);
    }
}
