package P04EncapsulationExercises.P03ShoppingSpree;

public class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        this.setName(name);
        this.setCost(cost);
    }

    private void setName(String name) {
        if (!name.trim().isEmpty()){
            this.name = name;

        }else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    private void setCost(double cost) {
        if (cost >= 0){
            this.cost = cost;
        }else {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {

        return this.name.toString();



    }
}