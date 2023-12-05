package P9PolymorphismLab.P04WildFarm;

public class Tiger extends Felime {
    protected Tiger(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        //only meat
        if (food instanceof Meat){
            super.eat(food);
        }else{
            throw new IllegalArgumentException("Tigers are not eating that type of food!");
        }

    }

}
