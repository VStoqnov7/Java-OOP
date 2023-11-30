package P04EncapsulationExercises.P04PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String[] pizzaData = scanner.nextLine().split("\\s+");

        String namePizza = pizzaData[1];
        int numberToppings = Integer.parseInt(pizzaData[2]);
        Pizza pizza = new Pizza(namePizza,numberToppings);

        String[] doughData = scanner.nextLine().split("\\s+");

        String nameDough = doughData[1];
        String bakingTechniqueDough = doughData[2];
        double weightDough = Double.parseDouble(doughData[3]);

        Dough dough = new Dough(nameDough,bakingTechniqueDough,weightDough);

        pizza.setDough(dough);
        String command = scanner.nextLine();

        while (!command.equals("END")){

            String toppingType = command.split(" ")[1];
            double weightTopping = Double.parseDouble(command.split(" ")[2]);

            Topping topping = new Topping(toppingType,weightTopping);
            pizza.addTopping(topping);

            command = scanner.nextLine();
        }


        System.out.printf("%s - %.2f",pizza.getName(), pizza.getOverallCalories());






    }
}
