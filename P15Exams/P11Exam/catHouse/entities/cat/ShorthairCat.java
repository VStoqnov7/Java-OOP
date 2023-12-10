package P15Exams.P11Exam.catHouse.entities.cat;

public class ShorthairCat extends BaseCat{

    private static final int KILOGRAMS = 7;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        super.setKilograms(KILOGRAMS);
    }

    @Override
    public void eating() {
        super.setKilograms(this.getKilograms() + 1);
    }
}
