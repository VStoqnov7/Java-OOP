package P15Exams.P14Exam.aquarium.entities.fish;

public class SaltwaterFish extends BaseFish{

    private static final int size = 5;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(size);
    }


    @Override
    public void eat() {
        super.setSize(super.getSize() + 2);
    }
}
