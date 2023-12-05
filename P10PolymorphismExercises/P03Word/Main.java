package P10PolymorphismExercises.P03Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        List<Command> commands = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts[0].equals("exit")) {
                break;
            }

            String commandName = parts[0];
            int startIndex = Integer.parseInt(parts[1]);
            int endIndex = Integer.parseInt(parts[2]);

            commands.add(new Command(commandName, startIndex, endIndex));
        }

        TextTransform textTransform = new TextTransform(text);
        for (Command command : commands) {
            command.execute(textTransform);
        }

        System.out.println(textTransform.getText());
    }
}