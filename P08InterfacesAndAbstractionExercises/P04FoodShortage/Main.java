package P08InterfacesAndAbstractionExercises.P04FoodShortage;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        LinkedHashMap<String,Buyer> data = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");

            if (input.length == 3){
                //"{name} {age}{group}"
                String name = input[0];

                int age = Integer.parseInt(input[1]);
                String group = input[2];
                
                Buyer buyer = new Rebel(name,age,group);
                
                data.putIfAbsent(name,buyer);
                
            } else if (input.length == 4) {
                //"{name} {age} {id} {birthdate}"
                String name = input[0];
                int age = Integer.parseInt(input[1]);
                String id = input[2];
                String birthday = input[3];

                Buyer buyer = new Citizen(name,age,id,birthday);
                data.putIfAbsent(name,buyer);
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("End")){

            if (data.containsKey(command)){
                data.get(command).buyFood();
            }
            command = scanner.nextLine();
        }

            System.out.println(data.values().stream().mapToInt(Buyer::getFood).sum());

    }
}
