package P03EncapsulationLab.P04FirstAndReserveTeam;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Team team = new Team("Manchester");

        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split("\\s+");

            String firstName = command[0];
            String lastName = command[1];
            int age = Integer.parseInt(command[2]);
            double salary = Double.parseDouble(command[3]);

            Person person = new Person(firstName,lastName,age,salary);

            team.addPlayer(person);
        }


        System.out.printf("First team have %d players%n",team.getFirstTeam().size());
        System.out.printf("Reserve team have %d players",team.getReserveTeam().size());



    }
}
