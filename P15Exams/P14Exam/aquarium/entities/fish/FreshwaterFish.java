package P15Exams.P14Exam.aquarium.entities.fish;

public class FreshwaterFish extends BaseFish{

    private static final int size = 3;


    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(size);
    }

    @Override
    public void eat() {
        super.setSize(super.getSize() + 3);
    }
}
