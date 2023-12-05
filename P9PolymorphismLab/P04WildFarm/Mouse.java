package P9PolymorphismLab.P04WildFarm;

public class Mouse extends Mammal{
    protected Mouse(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }
    @Override
    public void makeSound() {

        System.out.println("SQUEEEAAAK!");

    }
    @Override
    public void eat(Food food) {
        //only vegetables
        if (food instanceof Vegetable){
            super.eat(food);
        }else{
            throw new IllegalArgumentException("Mice are not eating that type of food!");
        }
    }
}
