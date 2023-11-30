package P04EncapsulationExercises.P04PizzaCalories;

public class Topping {

    private String toppingType;
    private double weight;


    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        switch (toppingType) {
            case "Meat":
            case "Veggies":
            case "Cheese":
            case "Sauce":
                this.toppingType = toppingType;
                break;
            default:
                throw new IllegalArgumentException("Cannot place " + toppingType + " on top of your pizza.");
        }
    }

    private void setWeight(double weight) {
        if (weight >= 1 && weight <= 50) {
            this.weight = weight;
        }else {
            throw new IllegalArgumentException(this.toppingType +" weight should be in the range [1..50].");
        }
    }

    public double calculateCalories(){
        double toppingCoefficient = 0;

        switch (this.toppingType){
            case "Meat":
                toppingCoefficient = 1.2;
                break;
            case "Veggies":
                toppingCoefficient = 0.8;
                break;
            case "Cheese":
                toppingCoefficient = 1.1;
                break;
            case "Sauce":
                toppingCoefficient = 0.9;
                break;
        }


        return 2 * weight * toppingCoefficient;
    }
}
