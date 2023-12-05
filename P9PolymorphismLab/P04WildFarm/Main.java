package P9PolymorphismLab.P04WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<Animal> animals = new ArrayList<>();
        String command = scanner.nextLine();


        while (!command.equals("End")) {

            String[] dataAnimal = command.split("\\s+");
            String[] dataFood = scanner.nextLine().split("\\s+");


            Animal animal = createAnimal(dataAnimal);
            Food food = createFood(dataFood);

            animals.add(animal);


            if (animal != null && food != null) {
                animal.makeSound();
                try {
                    animal.eat(food);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            command = scanner.nextLine();
        }

        animals.forEach(System.out::println);

    }

    public static Food createFood(String[] dataFood) {
        String type = dataFood[0];
        int foodEaten = Integer.parseInt(dataFood[1]);

        if (type.equals("Meat")) {
            return new Meat(foodEaten);
        } else if (type.equals("Vegetable")) {
            return new Vegetable(foodEaten);
        }

        return null;
    }

    public static Animal createAnimal(String[] dataAnimal) {

        String type = dataAnimal[0];
        String name = dataAnimal[1];
        Double weight = Double.parseDouble(dataAnimal[2]);
        String livingRegion = dataAnimal[3];

        if (type.equals("Mouse")) {
            return new Mouse(name, type.toString(), weight, livingRegion);

        } else if (type.equals("Zebra")) {
            return new Zebra(name, type.toString(), weight, livingRegion);

        } else if (type.equals("Tiger")) {
            return new Tiger(name, type.toString(), weight, livingRegion);

        } else if (type.equals("Cat")) {
            String breed = dataAnimal[4];
            return new Cat(name, type.toString(), weight, livingRegion, breed);
        }
        return null;
    }

}
