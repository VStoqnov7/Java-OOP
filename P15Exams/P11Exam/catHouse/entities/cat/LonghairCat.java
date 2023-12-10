package P15Exams.P11Exam.catHouse.entities.cat;

public class LonghairCat extends BaseCat{

    private static final int KILOGRAMS = 8;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
        super.setKilograms(KILOGRAMS);
    }

    @Override
    public void eating() {
        super.setKilograms(this.getKilograms() + 3);
    }
}
