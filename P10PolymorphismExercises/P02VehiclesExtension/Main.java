package P10PolymorphismExercises.P02VehiclesExtension;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] carInfo = scanner.nextLine().split("\\s+");
        double carFuelQuantity = Double.parseDouble(carInfo[1]);
        double carFuelConsumption = Double.parseDouble(carInfo[2]);
        double carTankCapacity = Double.parseDouble(carInfo[3]);
        Car car = new Car(carFuelQuantity, carFuelConsumption, carTankCapacity);

        String[] truckInfo = scanner.nextLine().split("\\s+");
        double truckFuelQuantity = Double.parseDouble(truckInfo[1]);
        double truckFuelConsumption = Double.parseDouble(truckInfo[2]);
        double truckTankCapacity = Double.parseDouble(truckInfo[3]);
        Truck truck = new Truck(truckFuelQuantity, truckFuelConsumption, truckTankCapacity);

        String[] busInfo = scanner.nextLine().split("\\s+");
        double busFuelQuantity = Double.parseDouble(busInfo[1]);
        double busFuelConsumption = Double.parseDouble(busInfo[2]);
        double busTankCapacity = Double.parseDouble(busInfo[3]);
        Bus bus = new Bus(busFuelQuantity, busFuelConsumption, busTankCapacity);

        int numCommands = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numCommands; i++) {
            String[] command = scanner.nextLine().split("\\s+");
            String action = command[0];
            String vehicleType = command[1];
            double distance = Double.parseDouble(command[2]);

            switch (action) {
                case "Drive":
                case "DriveEmpty":
                    switch (vehicleType) {
                        case "Car":
                            car.drive(distance);
                            break;
                        case "Truck":
                            truck.drive(distance);
                            break;
                        case "Bus":
                            if (command[0].trim().equals("DriveEmpty")) {
                                bus.drive(distance);
                            } else {
                                bus.setHasPeople(true);
                                bus.driveWhitPeople(distance);
                            }
                            break;
                    }
                    break;
                case "Refuel":
                    double fuelAmount = Double.parseDouble(command[2]);

                    try {
                        switch (vehicleType) {
                            case "Car":
                                car.refuel(fuelAmount);
                                break;
                            case "Truck":
                                truck.refuel(fuelAmount);
                                break;
                            case "Bus":
                                bus.refuel(fuelAmount);
                                break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }

        System.out.println(car);
        System.out.println(truck);
        System.out.println(bus);
    }
}