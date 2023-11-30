package P04EncapsulationExercises.P03ShoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;


    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();

    }

    private void setName(String name) {
        if (!name.trim().isEmpty()) {
            this.name = name;

        } else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    private void setMoney(double money) {

        if (money >= 0) {
            this.money = money;
        } else {
            throw new IllegalArgumentException("Money cannot be negative");
        }

    }

    public void buyProduct(Product product) {

        if (this.money >= product.getCost()) {
            products.add(product);
            this.money -= product.getCost();

        } else {
            throw new IllegalArgumentException(this.name + " can't afford " + product.getName());

        }
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(" - ");
        int size = products.size();
        if (products.isEmpty()){
            sb.append("Nothing bought");
        }
        for (Product item : products) {
            sb.append(item);
            if (size != 1) {
                sb.append(", ");
                size--;
            }
        }


        return sb.toString().trim();
    }
}
