package P10PolymorphismExercises.P01Vehicles;

public class Car extends Vehicle{
    private final static double SUMMER_FUEL_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + SUMMER_FUEL_CONSUMPTION);
    }
}
