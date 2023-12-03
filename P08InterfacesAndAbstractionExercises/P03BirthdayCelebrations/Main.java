package P08InterfacesAndAbstractionExercises.P03BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        List<Birthable> data = new ArrayList<>();

        while (!command.equals("End")){

            String[] input = command.split("\\s+");

            switch (input[0].trim()){
                case"Pet":
                    Birthable birthPet = new Pet(input[1],input[2]);
                    data.add(birthPet);
                    break;
                case "Robot":
                    Robot robot = new Robot(input[2],input[1]);
                    break;
                case "Citizen":
                    Birthable birthCitizen = new Citizen(input[1],Integer.parseInt(input[2]),input[3],input[4]);
                    data.add(birthCitizen);
                    break;
            }
            command = scanner.nextLine();
        }

        String dateOfBirth = scanner.nextLine();
        boolean isValid = true;

        for (Birthable birthable : data) {
            if (birthable.getBirthDate().endsWith(dateOfBirth)){
                System.out.println(birthable.getBirthDate());
                isValid = false;
            }
        }
        if (isValid){
            System.out.println("<no output>");

        }
    }
}
