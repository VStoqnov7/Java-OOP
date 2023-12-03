package P04EncapsulationExercises.P03ShoppingSpree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] nameAndMoney = scanner.nextLine().split(";");
        String[] productsAndCost = scanner.nextLine().split(";");
        List<Person> personList = new ArrayList<>();
        List<Product> productsList = new ArrayList<>();

        for (String item : nameAndMoney) {
            String[] command = item.split("=");
            String name = command[0].trim();
            double money = Double.parseDouble(command[1]);

            Person person = new Person(name, money);
            personList.add(person);
        }

        for (String item : productsAndCost) {
            String[] command = item.split("=");
            String nameProduct = command[0].trim();
            double cashProduct = Double.parseDouble(command[1]);
            Product product = new Product(nameProduct, cashProduct);
            productsList.add(product);
        }

        String command = scanner.nextLine();

        while (!command.equals("END")) {

            String[] nameAndProduct = command.split("\\s+");

            String name = nameAndProduct[0].trim();
            String product = nameAndProduct[1].trim();

            Person currentPerson = null;
            Product currentProduct = null;

            for (Person person : personList) {
                if (person.getName().equals(name)) {
                    currentPerson = person;
                    break;
                }
            }

            for (Product item : productsList) {
                if (item.getName().equals(product)) {
                    currentProduct = item;
                    break;
                }
            }
            if (currentPerson != null && currentProduct != null) {
                currentPerson.buyProduct(currentProduct);
            }
            command = scanner.nextLine();
        }

        for (Person item : personList) {
            System.out.println(item);

        }

    }
}

