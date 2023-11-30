package P03EncapsulationLab.P03ValidationData;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split("\\s+");

            String firstName = command[0];
            String lastName = command[1];
            int age = Integer.parseInt(command[2]);
            double salary = Double.parseDouble(command[3]);

            Person person = new Person(firstName,lastName,age,salary);

            people.add(person);
        }

        double percentage = Double.parseDouble(scanner.nextLine());

        for (Person item : people) {
            item.increaseSalary(percentage);
            System.out.println(item);

        }

    }
}
