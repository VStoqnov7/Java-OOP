package P15Exams.P08Exam.zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal{

    private static final double KILOGRAMS = 5.50;


    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eat() {
        setKg(this.getKg() + 5.70);
    }
}
