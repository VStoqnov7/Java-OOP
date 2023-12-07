package P15Exams.P02Exam.vehicleShop.models.worker;

public class SecondShift extends BaseWorker{

    private static final int STRENGTH = 70;

    public SecondShift(String name) {
        super(name, STRENGTH);
    }

    @Override
    public void working() {
        super.setStrength(Math.max(0,super.getStrength() - 5));
    }
}
