package P10PolymorphismExercises.P02VehiclesExtension;

public class Car extends Vehicle {

    private final static double SUMMER_FUEL_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption,double capacity) {
        super(fuelQuantity, fuelConsumption + SUMMER_FUEL_CONSUMPTION,capacity);
    }
}
