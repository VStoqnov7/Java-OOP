package P15Exams.P09Exam.fairyShop.models;

public class Happy extends BaseHelper{

    private static final int ENERGY = 100;

    public Happy(String name) {
        super(name, ENERGY);
    }
    @Override
    public void work() {
        this.setEnergy(Math.max(0,this.getEnergy() - 10));
    }
}
