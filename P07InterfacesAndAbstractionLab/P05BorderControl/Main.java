package P07InterfacesAndAbstractionLab.P05BorderControl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        List<Identifiable> identifiables = new ArrayList<>();

        String input = scanner.nextLine();

        while (!input.equals("End")) {

            String[] tokens = input.split("\\s+");

            Identifiable identifiable = tokens.length == 2
                    ? new Robot(tokens[0], tokens[1])
                    : new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);

            identifiables.add(identifiable);

            input = scanner.nextLine();
        }

        String fakeIdPostfix = scanner.nextLine();

        identifiables
                .stream()
                .map(Identifiable::getId)
                .filter(i -> i.endsWith(fakeIdPostfix))
                .forEach(System.out::println);

    }
}