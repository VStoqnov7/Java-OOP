package P03EncapsulationLab.P01SortByNameAndAge;

import java.util.ArrayList;
import java.util.Comparator;
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
            Person person = new Person(firstName,lastName,age);

            people.add(person);
        }

//        people.stream().sorted((a1,a2) -> {
//
//            int results = a1.getFirstName().compareTo(a2.getFirstName());
//
//            if (results == 0){
//
//                results = Integer.compare(a1.getAge(),a2.getAge());
//            }
//            return results;
//        }).forEach(System.out::println);


        people.stream().sorted(Comparator.comparing(Person::getFirstName).thenComparingInt(Person::getAge)).forEach(System.out::println);

    }
}
