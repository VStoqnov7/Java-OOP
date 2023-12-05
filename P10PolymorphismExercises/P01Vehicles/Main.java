package P10PolymorphismExercises.P01Vehicles;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] command = scanner.nextLine().split("\\s+");
        double carFuelQuantity = Double.parseDouble(command[1]);
        double carFuelConsumption = Double.parseDouble(command[2]);

        Car car = new Car(carFuelQuantity,carFuelConsumption);

        command = scanner.nextLine().split("\\s+");

        double truckFuelQuantity = Double.parseDouble(command[1]);
        double truckFuelConsumption = Double.parseDouble(command[2]);

        Truck truck = new Truck(truckFuelQuantity,truckFuelConsumption);

        int n = Integer.parseInt(scanner.nextLine());

        LinkedHashMap <String, Vehicle> data = new LinkedHashMap<>();
        data.putIfAbsent("Car",car);
        data.putIfAbsent("Truck",truck);

        for (int i = 0; i < n; i++) {

            command = scanner.nextLine().split("\\s+");
            switch (command[0]){
                case "Drive":
                    if (command[1].equals("Car")){
                        data.get("Car").drive(Double.parseDouble(command[2]));
                    }else if (command[1].equals("Truck")){
                        data.get("Truck").drive(Double.parseDouble(command[2]));
                    }
                    break;
                case "Refuel":
                    if (command[1].equals("Car")){
                        data.get("Car").refuel(Double.parseDouble(command[2]));
                    }else if (command[1].equals("Truck")){
                        data.get("Truck").refuel(Double.parseDouble(command[2]));
                    }
                    break;
            }
        }
        data.values().forEach(System.out::println);
    }
}
