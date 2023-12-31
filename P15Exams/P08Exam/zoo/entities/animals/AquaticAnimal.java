package P15Exams.P08Exam.zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal{

    private static final double KILOGRAMS = 2.50;

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eat() {
        setKg(this.getKg() + 7.50);
    }
}
