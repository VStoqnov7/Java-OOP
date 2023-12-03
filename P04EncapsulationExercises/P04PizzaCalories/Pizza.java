package P04EncapsulationExercises.P04PizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name , int numberOfToppings) {
        this.setName(name);
        this.setToppings(numberOfToppings);

    }
    private void setName(String name) {
        if (name.trim().length() >= 1 && name.trim().length() <= 15){
            this.name = name;

        }else {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }


    }

    private void setToppings(int numberOfToppings) {
        if (numberOfToppings >= 0 && numberOfToppings <= 10){
            this.toppings = new ArrayList<>(numberOfToppings);
        }else {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }

    }

    public void setDough(Dough dough){
        this.dough = dough;

    }

    public String getName(){
        return this.name;
    }

    public void addTopping(Topping topping){
        this.toppings.add(topping);
    }

    public double getOverallCalories(){

        return this.dough.calculateCalories() + this.toppings.stream().mapToDouble(Topping::calculateCalories).sum();
    }
}
